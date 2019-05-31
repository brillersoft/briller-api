package com.briller.acess.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briller.acess.dashboard.repositories.AccountCsatRepo;
import com.briller.acess.dashboard.repositories.AccountRepository;
import com.briller.acess.dashboard.repositories.ClientRepo;
import com.briller.acess.dashboard.repositories.EmployeeCsatSummaryRepo;
import com.briller.acess.dashboard.repositories.EmployeeEmailMappingRepositry;
import com.briller.acess.dashboard.repositories.EmployeeRepo;
import com.briller.acess.dashboard.repositories.EmployeeRoleMappingRepo;
import com.briller.acess.dashboard.repositories.RoleRepo;
import com.briller.acess.dto.Account;
import com.briller.acess.dto.Client;
import com.briller.acess.dto.Employee;
import com.briller.acess.dto.EmployeeCsatSummary;
import com.briller.acess.response.RelationalTone;
import com.briller.acess.response.Relationship;
import com.briller.acess.response.Response;
import com.briller.acess.response.ResponseData;
import com.briller.acess.response.Tones;
import com.briller.acess.service.IClientDashboardService;
import com.briller.acess.utility.ToneTypes;

@Service
public class ClientDashboardServiceImpl implements IClientDashboardService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeEmailMappingRepositry employeeEmailMappingRepositry;

	@Autowired
	private EmployeeCsatSummaryRepo employeeCsatSummaryRepo;

	@Autowired
	private ClientRepo clientRepo;

	@Autowired
	private AccountCsatRepo accountCsatRepo;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private EmployeeRoleMappingRepo employeeRoleMappingRepo;

	@Autowired
	private RoleRepo roleRepo;

	public Response getClientDashboardData(Map<String, String> request) {
		Response response = new Response();
		try {
			if (request != null) {

				log.info("Inside get getClientDashboardData Data post  method -- service Implementation");

				String client = request.get("client");

				// check Client not null
				if (client != null) {
					log.info("Client value not null - proceeding further ");
					Account account = accountRepository.findByAccountName(client);
					if (account != null) {
						// * Same Client Name Employees * //
						List<EmployeeCsatSummary> clientEmployees = employeeCsatSummaryRepo
								.findByAccountId(account.getAccountId());
						if (clientEmployees != null) {
							List<ResponseData> listOfResponseData = new ArrayList<>();

							for (EmployeeCsatSummary e : clientEmployees) {
								int roleId = employeeRoleMappingRepo.findByEmployeeId(e.getEmployeeId()).getRole()
										.getRoleId();
								ResponseData responseData = new ResponseData();
								responseData.setClient(client);
								responseData.setEmail(employeeEmailMappingRepositry.getEmailId(e.getEmployeeId()));
								Employee em = employeeRepo.findByEmployeeId(e.getEmployeeId());
								responseData.setName(em.getFirstName());
								responseData.setDesignation(roleRepo.getRoleName(roleId));
								responseData.setImageUrl("");
								responseData.setInitials(em.getFirstName().charAt(0) + "" + em.getLastName().charAt(0));

								List<EmployeeCsatSummary> employeeCsatData = employeeCsatSummaryRepo
										.findByEmployeeId(e.getEmployeeId());
								List<Relationship> listOfRelationships = new ArrayList<>();

								for (EmployeeCsatSummary emp : employeeCsatData) {

									Relationship relationship = new Relationship();
									int clientId = emp.getClientId();
									Client clientDetail = clientRepo.findByClientId(clientId);
									relationship.setName(clientDetail.getFirstName());
									relationship.setImageUrl("");
									relationship.setInitials(clientDetail.getFirstName().charAt(0) + ""
											+ (clientDetail.getLastName().isEmpty() ? ""
													: clientDetail.getLastName().charAt(0)));
									relationship.setNumConversations(emp.getNum_of_interactions());
									relationship.setNumEscalations(
											accountCsatRepo.findByAccountId(emp.getAccountId()).getEscalations());
									// Score HardCoded to 0.82
									relationship.setScore(0.82);

									// EMP TONE
									Map<ToneTypes, Double> empToneMap = new HashMap<ToneTypes, Double>();
									empToneMap.put(ToneTypes.anger, 0.12);
									empToneMap.put(ToneTypes.fear, 0.10);
									empToneMap.put(ToneTypes.sadness, 0.05);
									empToneMap.put(ToneTypes.joy, 0.80);
									empToneMap.put(ToneTypes.confidence, 0.75);

									Tones empTones = new Tones();
									empTones.setTones(empToneMap);

									RelationalTone relationalTone = new RelationalTone();
									relationalTone.setEmployeeTone(empTones);

									// CLIENT TONE
									Map<ToneTypes, Double> clientToneMap = new HashMap<ToneTypes, Double>();
									clientToneMap.put(ToneTypes.anger, 0.22);
									clientToneMap.put(ToneTypes.fear, 0.03);
									clientToneMap.put(ToneTypes.sadness, 0.30);
									clientToneMap.put(ToneTypes.joy, 0.50);
									clientToneMap.put(ToneTypes.confidence, 0.60);

									Tones clientTones = new Tones();
									clientTones.setTones(clientToneMap);

									relationalTone.setCustomerTone(clientTones);

									relationship.setToneMapping(relationalTone);
									listOfRelationships.add(relationship);

								}
								listOfResponseData.add(responseData);
								responseData.setRelationship(listOfRelationships);

							}
							response.setMsg("ok");
							response.setResponse(listOfResponseData);
						} else {
							response.setMsg("No Employees Under this Client found !");
							log.info("No Employees Under this Client found ! ");
						}
					} else {
						response.setMsg("No such Account found : " + client);
					}

				} else {
					response.setMsg("Client is null ");
				}

			}

			return response;

		} catch (Exception e) {
			// TODO: handle exception
			response.setMsg("Error");
			response.setResponse(null);
			log.error("error msg : " + e.getMessage());

			return response;
		}
	}
}