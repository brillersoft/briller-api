package com.hanogi.batch.servicesImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hanogi.batch.constants.DeploymentTypes;
import com.hanogi.batch.constants.EmailServiceProviders;
import com.hanogi.batch.entity.AddressDetails;
import com.hanogi.batch.entity.BatchRunDetails;
import com.hanogi.batch.entity.Client;
import com.hanogi.batch.entity.Continent;
import com.hanogi.batch.entity.Email;
import com.hanogi.batch.entity.EmailDomainDetails;
import com.hanogi.batch.entity.Employee;
import com.hanogi.batch.entity.EmployeeHierarchy;
import com.hanogi.batch.entity.ExecutionStatus;
import com.hanogi.batch.entity.GeoLocation;
import com.hanogi.batch.entity.OrganizationDetails;
import com.hanogi.batch.entity.SchedulerJobInfo;
import com.hanogi.batch.entity.WorldCountry;
import com.hanogi.batch.repositry.AddressDetailsRepo;
import com.hanogi.batch.repositry.BatchRunDetailsRepo;
import com.hanogi.batch.repositry.ClientRepositry;
import com.hanogi.batch.repositry.ContinentRepo;
import com.hanogi.batch.repositry.DistrictRepo;
import com.hanogi.batch.repositry.EmailDomainDetailsRepo;
import com.hanogi.batch.repositry.EmailRepositry;
import com.hanogi.batch.repositry.EmployeeHierarchyRepo;
import com.hanogi.batch.repositry.EmployeeRepositry;
import com.hanogi.batch.repositry.GeoLocationRepo;
import com.hanogi.batch.repositry.OrganizationDetailsRepo;
import com.hanogi.batch.repositry.SchedulerJobInfoRepo;
import com.hanogi.batch.repositry.WorldCityRepo;
import com.hanogi.batch.repositry.WorldCountryRepo;
import com.hanogi.batch.response.Response;
import com.hanogi.batch.services.ISecudlerService;
import com.hanogi.batch.utility.Request;

@Service
public class SecudlerService implements ISecudlerService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	SchedulerJobInfoRepo schedulerJobInfoRepo;

	@Autowired
	EmailDomainDetailsRepo emailDomainDetailsRepo;

	@Autowired
	BatchRunDetailsRepo batchRunDetailsRepo;

	@Autowired
	EmailRepositry emailRepo;

	@Autowired
	private WorldCountryRepo worldRepo;

	@Autowired
	private WorldCityRepo cityRepo;

	@Autowired
	private AddressDetailsRepo addressDetailsRepo;

	@Autowired
	private GeoLocationRepo geoLocationRepo;

	@Autowired
	private OrganizationDetailsRepo organizationDetailsRepo;

	@Autowired
	private DistrictRepo districtRepo;

	@Autowired
	private ContinentRepo continentRepo;

	@Autowired
	private EmployeeRepositry employeeRepo;

	@Autowired
	private ClientRepositry clientRepo;

	@Autowired
	private EmployeeHierarchyRepo empHierarchyRepo;

	public boolean saveNewScheduler(SchedulerJobInfo schedulerJobInfo) {
		try {
			return (schedulerJobInfoRepo.save(schedulerJobInfo) != null);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Integer saveNewSchedulerBatchDetails(Map<String, String> dateRange) {
		Integer batchId = null;
		if (dateRange != null) {
			try {
				batchId = saveBatchRunDetails(dateRange);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
		}

		return batchId;
	}

	private Integer saveBatchRunDetails(Map<String, String> dateRange) {
		Integer batchId = null;
		BatchRunDetails batchRunDetails = new BatchRunDetails();

		if (dateRange != null) {
			try {

				batchRunDetails.setStatus("1");

				batchRunDetails.setBatchExecutionStatus(null);

				batchRunDetails.setBatchStatusDetails(createStatusJson());

				batchRunDetails.setFromDate(new SimpleDateFormat("DD-MM-YYYY").parse(dateRange.get("From_Date")));

				batchRunDetails.setToDate(new SimpleDateFormat("DD-MM-YYYY").parse(dateRange.get("End_Date")));
				batchRunDetails.setBatchRunDate("");
				batchRunDetails.setBatchStatusId(new ExecutionStatus());

				BatchRunDetails isBatchSaved = batchRunDetailsRepo.save(batchRunDetails);

				batchId = (isBatchSaved != null) ? isBatchSaved.getBatchRunId() : null;

			} catch (Exception e) {
				System.out.println("Error while saving batch:" + e.getMessage());
			}
		} else {

		}

		return batchId;
	}

	private String createStatusJson() {
		JsonObject statusJson = new JsonObject();

		return null;
	}

	public DeploymentTypes[] getAllDeploymentTypes() {
		return DeploymentTypes.values();

	}

	public EmailServiceProviders[] getAllServiceProviders() {
		return EmailServiceProviders.values();
	}

	@Override
	public Map<String, Object> getConfigOptions() {

		Map<String, Object> configOptions = new HashMap<String, Object>();

		configOptions.put("Domain_Types", getAllDeploymentTypes());

		configOptions.put("Service_Providers", getAllServiceProviders());

		return configOptions;
	}

	private int saveGeoLocation(Map<String, Integer> geoInfoMap) {
		log.info("Inside saveGeoLocation" + geoInfoMap);
		GeoLocation geoLocation = new GeoLocation();
		try {
			geoLocation.setCountryId(geoInfoMap.get("countryId"));
			geoLocation.setCityId(geoInfoMap.get("cityId"));
			geoLocation.setStatus("1");
			geoLocation.setContinentId(geoInfoMap.get("continentId"));
			geoLocation.setDistrictId(geoInfoMap.get("districtId"));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return geoLocationRepo.save(geoLocation).getLocationId();

	}

	@Transactional
	private int addressSave(Map<String, Object> requestParam) {
		log.info("Inside addressSave" + requestParam);
		Integer addId = null;
		AddressDetails addressDetails = new AddressDetails();
		try {
			addressDetails.setAddressDetails((String) requestParam.get("Address"));
			addressDetails.setStatus("1");

			Map<String, String> addressMap = (Map) requestParam.get("Address_Details");

			String district = addressMap.get("District");

			// Saving Geo-Location
			Map<String, Integer> geoInfoMap = new HashMap<>();
			WorldCountry country = worldRepo.findByCountryName(addressMap.get("Country"));
			geoInfoMap.put("countryId", new Integer(country.getCountryId()));
			Continent c = continentRepo.findByContinentName(country.getContinent());
			geoInfoMap.put("continentId", c.getContinentId());

			// TODO make column countryId and get all Data from City object
			geoInfoMap.put("cityId", cityRepo.findByName(addressMap.get("City")).getCityId());
			geoInfoMap.put("districtId", districtRepo.findByDistrictName(district).getDistrictId());

			// Location ID Returned
			int locationId = saveGeoLocation(geoInfoMap);
			addressDetails.setLocationId(locationId);
			addressDetails.setAddressDetails(addressMap.get("Address"));
			addressDetails.setZipCode((String) addressMap.get("Zip"));
			addId = addressDetailsRepo.save(addressDetails).getAddressId();
			return addId;
		} catch (Exception e) {
			log.error(e.getMessage());
			return addId;
		}

	}

	@Override
	public Response saveEntity(Request request) {
		log.info("Inside saveEntity" + request);
		Response response = new Response();
		try {
			if (request != null) {
				Map<String, Object> requestParam = request.getRequestParam();
				Integer orgMatch = organizationDetailsRepo.checkDuplicate((String) requestParam.get("Org_Name"),
						(String) requestParam.get("Org_Code"));

				if (orgMatch == 0) {
					if (requestParam != null) {
						Integer addressId = addressSave(requestParam);
						if (addressId != null) {
							response.setResponse(organizationDetailSave(requestParam, addressId));
							response.setMsg("Successfully saved");
						}
					}
				} else {
					response.setMsg("Org Name or code already exists");
				}
			}
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setMsg("Failed While Saving");
			return response;
		}
	}

	@Override
	@Transactional
	public Response updateEntityDetail(Request request) {
		Response response = new Response();
		log.info("Inside updateEntityDetail" + request);
		try {
			if (request != null) {

				Map<String, Object> requestParam = request.getRequestParam();
				Integer orgMatch = organizationDetailsRepo.checkDuplicate((String) requestParam.get("Org_Name"),
						(String) requestParam.get("Org_Code"));
				if (orgMatch == 0) {
					Integer entityId = new Integer((String) requestParam.get("Entity_Id"));
					response.setResponse(entityId);
					OrganizationDetails details = organizationDetailsRepo.findByLegalEntityId(entityId);
					AddressDetails addressDetail = addressDetailsRepo.findById(details.getAddressId()).get();
					addressDetail.setStatus("0");
					GeoLocation geoLocation = geoLocationRepo.findById(addressDetail.getLocationId()).get();
					geoLocation.setStatus("0");

					geoLocationRepo.save(geoLocation);
					addressDetailsRepo.save(addressDetail);
					Integer addId = addressSave(requestParam);
					if (addId != null) {
						details.setAddressId(addId);
						details.setEntityCode((String) requestParam.get("Org_Code"));
						details.setDescription((String) requestParam.get("Org_Desc"));
						details.setEntityName((String) requestParam.get("Org_Name"));
						response.setMsg(organizationDetailsRepo.save(details) != null ? "Entity Address Updated"
								: "Failed while updating");
					}
				} else {
					response.setMsg("Org Name or Code already exists");
				}

			}
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			System.out.println(e.getMessage());
			return response;
		}

	}

	private Integer organizationDetailSave(Map<String, Object> requestParam, int addressId) {
		log.info("Inside organizationDetailSave" + requestParam);
		Integer legalEntityId = null;
		OrganizationDetails organizationDetails = new OrganizationDetails();
		try {
			organizationDetails.setEntityName((String) requestParam.get("Org_Name"));

			/// TODO Description default set to Type_Id =1 - "IT Infra Retail"
			organizationDetails.setTypeId(1);
			organizationDetails.setAddressId(addressId);
			organizationDetails.setEntityCode((String) requestParam.get("Org_Code"));

			// For new entry status must be '1'
			organizationDetails.setStatus("1");
			organizationDetails.setDescription((String) requestParam.get("Org_Desc"));
			legalEntityId = organizationDetailsRepo.save(organizationDetails).getLegalEntityId();
			return legalEntityId;
		} catch (Exception e) {
			log.error(e.getMessage());
			return legalEntityId;
		}

	}

	@Override
	public Boolean saveConfigOptions(Request request) {
		Boolean isSaved = false;

		if (request != null) {
			List<String> allDomainsName = emailDomainDetailsRepo.getAllDomainsName();

			EmailDomainDetails domainDetails = new EmailDomainDetails();

			Map<String, Object> requestParam = request.getRequestParam();

			if (requestParam != null) {

				String domainName = (String) requestParam.get("Email_Domain_Name");

				if (!allDomainsName.contains(domainName)) {

					try {

						domainDetails.setEmailDomainName(domainName);

						JsonObject serverConfigProperties = new JsonObject();

						serverConfigProperties.addProperty("adminUserName", (String) requestParam.get("User_Name"));

						serverConfigProperties.addProperty("adminPassword", (String) requestParam.get("Pwd"));

						serverConfigProperties.addProperty("exchangeServerURL",
								(String) requestParam.get("Server_Url"));

						serverConfigProperties.addProperty("exchangeVersion",
								(String) requestParam.get("Exchange_version"));

						String domainType = (String) requestParam.get("Domain_Type");

						if (domainType.equalsIgnoreCase("OnCloud")) {

							serverConfigProperties.addProperty("clientId", (String) requestParam.get("Client_Id"));

							serverConfigProperties.addProperty("secreatKey", (String) requestParam.get("Secreat_Key"));

							serverConfigProperties.addProperty("graphApiUrl",
									(String) requestParam.get("Graph_Api_Url"));
						}

						domainDetails.setEmailServerConfig(serverConfigProperties.toString());

						// Must be Values From given list
						domainDetails.setEmailServiceProvider((String) requestParam.get("Service_Provider"));
						domainDetails.setServerDeploymentType(domainType);

						// For new entry status must be '1'
						domainDetails.setStatus("1");

						isSaved = (emailDomainDetailsRepo.save(domainDetails) != null);
					} catch (Exception e) {
						// TO_DO Apply logging
						return isSaved;
					}

				} else {
					// Domain name already exists
				}
			} else {
				// TO_DO Apply logging
			}

		} else {
			// TO_DO Apply logging
		}
		return isSaved;
	}

	@Override
	public List<Map<String, String>> getAllServerConfigs() {
		JsonParser parser = new JsonParser();

		List<Map<String, String>> allServerDetails = new ArrayList<>();

		Iterable<EmailDomainDetails> allSavedServerConfigs = emailDomainDetailsRepo.findAll();

		for (EmailDomainDetails emailDomainDetails : allSavedServerConfigs) {
			try {
				Map<String, String> serverConfigsValue = new HashMap<String, String>();

				ObjectMapper oMapper = new ObjectMapper();

				// String emailServerConfig = emailDomainDetails.getEmailServerConfig();

				serverConfigsValue = oMapper.convertValue(emailDomainDetails, Map.class);

				String emailServerConfig = serverConfigsValue.remove("emailServerConfig");

				serverConfigsValue.remove("versionNum");

				JsonObject serverConfigs = parser.parse(emailServerConfig).getAsJsonObject();

				// Map adminData = oMapper.convertValue(serverConfigs, Map.class);
				HashMap adminData = new Gson().fromJson(emailServerConfig, HashMap.class);

				serverConfigsValue.put("userName", (String) adminData.get("adminUserName"));
				serverConfigsValue.put("exchangeServerURL", (String) adminData.get("exchangeServerURL"));
				serverConfigsValue.put("exchangeVersion", (String) adminData.get("exchangeVersion"));

				allServerDetails.add(serverConfigsValue);
			} catch (Exception e) {
				// TO_Do add logging
			}

		}
		return allServerDetails;

	}

	@Override
	public List<Map<String, Object>> getAllSchedulers() {
		Iterable<SchedulerJobInfo> allScheduledJobs = schedulerJobInfoRepo.findAll();

		List<Map<String, Object>> allSchedulers = new ArrayList<>();

		for (SchedulerJobInfo schedulerJobInfo : allScheduledJobs) {
			Map<String, Object> schedulerDetails = new HashMap<String, Object>();

			schedulerDetails.put("Job_Name", schedulerJobInfo.getJobName());
			schedulerDetails.put("Job_group", schedulerJobInfo.getJobGroup());
			schedulerDetails.put("Is_Cron_Job", schedulerJobInfo.getCronJob());
			schedulerDetails.put("Cron_Expres", schedulerJobInfo.getCronExpression());
			schedulerDetails.put("Is_Active", schedulerJobInfo.getIsActive());
			schedulerDetails.put("Batch_Id", schedulerJobInfo.getBatchId());

			allSchedulers.add(schedulerDetails);
		}

		return allSchedulers;
	}

	@Override
	public Boolean scheduleNewJobs(Request scheduleJobRequest) {
		boolean isSaved = false;
		SchedulerJobInfo jobInfo = new SchedulerJobInfo();

		Map<String, String> dateRange = new HashMap<String, String>();

		if (scheduleJobRequest != null) {
			Map<String, Object> requestParam = scheduleJobRequest.getRequestParam();
			if (requestParam != null) {
				jobInfo.setIsActive(Boolean.TRUE);
				jobInfo.setJobGroup((String) requestParam.get("Job_Group"));
				jobInfo.setJobName((String) requestParam.get("Job_Name"));
				jobInfo.setBatchId(new Integer((String) requestParam.get("Batch_Id")));

				Boolean isCronJob = new Boolean((String) requestParam.get("Is_Cron_Job"));
				jobInfo.setCronJob(isCronJob);
				String startDate = (String) requestParam.get("From_date");
				String endDate = (String) requestParam.get("End_date");
				dateRange.put("From_Date", startDate);
				dateRange.put("End_Date", endDate);

				Integer batchId = saveNewSchedulerBatchDetails(dateRange);

				// Use this batch_id to save
				if (isCronJob) {
					jobInfo.setCronExpression((String) requestParam.get("Cron_Expres"));

				} else {
					String repeatTime = (String) requestParam.get("Repeat_Time");
					if (repeatTime != null) {
						jobInfo.setRepeatTime(new Long((String) requestParam.get("Repeat_Time")));
					}

				}

				// Integer saveBatchRunDetails = saveBatchRunDetails(dateRange);
				isSaved = saveNewScheduler(jobInfo);
			} else {

			}
		} else {

		}
		return isSaved;
	}

	@Override
	public Boolean saveMailIdList(Request addMailIdListRequest) {
		Boolean isSaved = false;
		if (addMailIdListRequest != null) {
			Map<String, Object> requestParam = addMailIdListRequest.getRequestParam();

			if (requestParam != null) {
				Email email = new Email();

				email.setEmailId((String) requestParam.get("Email_Id"));
				email.setStatus("1");

				isSaved = (emailRepo.save(email) != null);
			} else {

			}
		} else {

		}
		return isSaved;
	}

	@Override
	public Boolean updateConfigOptions(Request configRequest) {

		Boolean isSaved = false;

		if (configRequest != null) {
			List<String> allDomainsName = emailDomainDetailsRepo.getAllDomainsName();

			EmailDomainDetails domainDetails = new EmailDomainDetails();

			Map<String, Object> requestParam = configRequest.getRequestParam();

			if (requestParam != null) {
				String id = (String) requestParam.get("id");

				Optional<EmailDomainDetails> domainById = emailDomainDetailsRepo.findById(new Integer(id));

				boolean idExists = domainById.isPresent();

				if (idExists) {

					EmailDomainDetails emailDomainDetails = domainById.get();
					String domainName = (String) requestParam.get("Email_Domain_Name");

					if (!allDomainsName.contains(domainName)) {

						try {

							emailDomainDetails.setEmailDomainName(domainName);

							JsonObject serverConfigProperties = new JsonObject();

							serverConfigProperties.addProperty("adminUserName", (String) requestParam.get("User_Name"));

							serverConfigProperties.addProperty("adminPassword", (String) requestParam.get("Pwd"));

							serverConfigProperties.addProperty("exchangeServerURL",
									(String) requestParam.get("Server_Url"));

							serverConfigProperties.addProperty("exchangeVersion",
									(String) requestParam.get("Exchange_version"));

							String domainType = (String) requestParam.get("Domain_Type");

							if (domainType.equalsIgnoreCase("OnCloud")) {

								serverConfigProperties.addProperty("clientId", (String) requestParam.get("Client_Id"));

								serverConfigProperties.addProperty("secreatKey",
										(String) requestParam.get("Secreat_Key"));

								serverConfigProperties.addProperty("graphApiUrl",
										(String) requestParam.get("Graph_Api_Url"));
							}

							emailDomainDetails.setEmailServerConfig(serverConfigProperties.toString());

							// Must be Values From given list
							emailDomainDetails.setEmailServiceProvider((String) requestParam.get("Service_Provider"));
							emailDomainDetails.setServerDeploymentType(domainType);

							// For new entry status must be '1'
							emailDomainDetails.setStatus("1");

							isSaved = (emailDomainDetailsRepo.save(domainDetails) != null);
						} catch (Exception e) {
							// TO_DO Apply logging
							return isSaved;
						}

					} else {
						// Domain name already exists
					}

				} else {

				}
			} else {
				// TO_DO Apply logging
			}

		} else {
			// TO_DO Apply logging
		}
		return isSaved;

	}

	@Override
	public List<String> getWorldCountry() {

		try {
			return worldRepo.getCountryNames();
		} catch (Exception e) {
			System.err.println("Error:" + e.getMessage());
			return null;
		}

	}

	@Override
	public Object getCities() {
		try {
			return cityRepo.getCities();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Boolean saveNewAccount(Request request) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object getBatchStatus() {
		List<Map<String, String>> allBatchStatusList = new ArrayList<Map<String, String>>();

		for (int i = 0; i < 3; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("batchId", (i + 1) + "");
			map.put("totalMailsProceed", "35");
			map.put("timeTaken", "12.7");
			map.put("failedMail", "3");
			map.put("runOn", "2019-01-09 00:00:00");
			map.put("nextTrigger", "2019-01-09 00:00:00");
			allBatchStatusList.add(map);
		}
		return allBatchStatusList;
	}

	@Override
	public List<Map<String, Object>> getAllEntities() {
		List<Map<String, Object>> entityDetailsList = new ArrayList<>();
		try {
			List<OrganizationDetails> allOrgDetails = organizationDetailsRepo.findAll();

			for (OrganizationDetails organizationDetails : allOrgDetails) {
				Map<String, Object> detailsMap = new HashMap();

				detailsMap.put("Entity_Id", organizationDetails.getLegalEntityId());
				detailsMap.put("Org_Name", organizationDetails.getEntityName());
				detailsMap.put("Description", organizationDetails.getDescription());
				detailsMap.put("Entity_Code", organizationDetails.getEntityCode());

				Integer addressId = organizationDetails.getAddressId();

				AddressDetails addressDetails = addressDetailsRepo.findById(addressId).get();

				Map<String, String> addressMap = new HashMap<>();

				addressMap.put("Address", addressDetails.getAddressDetails());

				// To_Do
				addressMap.put("Address_Type", "Internal");
				GeoLocation geoLocation = geoLocationRepo.findById(addressDetails.getLocationId()).get();
				addressMap.put("City", cityRepo.findById(geoLocation.getCityId()).get().getName());
				addressMap.put("Country", worldRepo.findById(geoLocation.getCountryId()).get().getCountryName());
				addressMap.put("Zip", addressDetails.getZipCode());

				detailsMap.put("Address_Details", addressMap);

				entityDetailsList.add(detailsMap);
			}
			return entityDetailsList;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	@Transactional
	public Response saveNewEmployee(Request request) {
		Response response = new Response();

		try {
			if (request != null) {
				Map<String, Object> requestParam = request.getRequestParam();
				if (requestParam != null) {

					String firstName = (String) requestParam.get("fName");
					String middleName = (String) requestParam.get("mName");
					String lastName = (String) requestParam.get("lName");
					String mail_id = (String) requestParam.get("emailId");
					String mail_type = (String) requestParam.get("mailType");
					Integer managerId = (Integer) requestParam.get("manager_id");
					String level = (String) requestParam.get("level");

					Employee employee = new Employee();

					try {
						employee.setFirstName(firstName);
						employee.setMiddleName(middleName);
						employee.setLastName(lastName);
					} catch (Exception e) {
						throw new Exception("Name ");
					}
					employee.setStatus("1");

					Employee savedEmployee = employeeRepo.save(employee);

					if (savedEmployee != null) {
						Integer employeeId = savedEmployee.getEmployeeId();

						Email email = new Email();
						email.setEmailidDomainType(mail_type);
						email.setEmailId(mail_id);
						email.setStatus("1");

						if ((emailRepo.save(email) != null)) {
							EmployeeHierarchy employeeHierarchy = new EmployeeHierarchy();
							employeeHierarchy.setEmployeeId(employeeId);
							employeeHierarchy.setManagerId(managerId);
							employeeHierarchy.setHierarchyLevel(level);

							String msg = (empHierarchyRepo.save(employeeHierarchy) != null) ? "Saved Successfully"
									: "Error while saving manager details";
							response.setMsg(msg);
							response.setResponse(savedEmployee.getEmployeeId());
						} else {
							response.setMsg("Error while saving emp mailId");
						}

					} else {
						response.setMsg("Employee not saved successfully.");
					}

				} else {
					response.setMsg("request map can not be null");
				}

			} else {
				response.setMsg("request can not be null");
			}

			return response;
		} catch (Exception e) {
			response.setMsg("Internal Server Error.");
			response.setResponse(null);
			return response;
		}

	}

	@Override
	public Response saveNewClient(Request request) {
		Response response = new Response();

		try {
			if (request != null) {
				Map<String, Object> requestParam = request.getRequestParam();

				if (requestParam != null) {

					String firstName = (String) requestParam.get("fName");
					String middleName = (String) requestParam.get("mName");
					String lastName = (String) requestParam.get("lName");
					String mail_id = (String) requestParam.get("emailId");
					Integer accountId = (Integer) requestParam.get("accountId");
					String level = (String) requestParam.get("level");
					
					Client client = new Client();
					client.setAccountId(accountId + "");
					client.setFirstName(firstName);
					client.setMiddleName(middleName);
					client.setLastName(lastName);
					client.setHierarchyLevel(level);
					client.setStatus("1");

					if (clientRepo.save(client) != null) {
						Email email = new Email();
						email.setEmailidDomainType("External");
						email.setEmailId(mail_id);
						email.setStatus("1");

						if (emailRepo.save(email) != null) {
							response.setMsg("Saved Successfully");
							response.setResponse("");
						} else {
							response.setMsg("Unable to save emailId.");
						}
					} else {
						response.setMsg("Unable to save client details.");
					}
				} else {
					response.setMsg("request map can not be null");
				}
			} else {
				response.setMsg("request can not be null");
			}
			return response;
		} catch (Exception e) {
			log.error("Error while saving client details:" + e.getMessage());
			response.setMsg("Internal Server Error");
			response.setResponse(null);
			return response;
		}

	}

	/*
	 * @Override
	 * 
	 * @Transactional public Response deleteServerConfigOptions(Integer id) {
	 * log.info("Inside deleteServerConfigOptions service");
	 * 
	 * Response response = new Response();
	 * 
	 * try {
	 * 
	 * Optional<EmailDomainDetails> emailDomainDetail =
	 * emailDomainDetailsRepo.findById(id);
	 * 
	 * if (emailDomainDetail.isPresent()) { List<Email> emailForTheDomain =
	 * emailRepo.findByEmailDomailDetails(id); Setting status 0 to represent
	 * in-active domain. List<Email> mails= new ArrayList<>();
	 * emailForTheDomain.stream().map(email -> {
	 * 
	 * email.setStatus("0"); mails.add(email); return emailRepo.saveAll(mails); });
	 * 
	 * EmailDomainDetails details = emailDomainDetail.get(); details.setStatus("0");
	 * emailDomainDetailsRepo.save(details);
	 * 
	 * response.setMsg("Server details for this id deleted successfully.");
	 * response.setResponse(""); } else {
	 * response.setMsg("No domain details found for such id:" + id);
	 * response.setResponse(""); } return response; } catch (Exception e) {
	 * response.setMsg("unable to delete server config details.");
	 * response.setResponse(null); return response; }
	 * 
	 * }
	 */

	@Override
	@Transactional
	public Response deleteServerConfigOptions(Integer id) {
		log.info("Inside deleteServerConfigOptions service");

		Response response = new Response();

		try {

			Optional<EmailDomainDetails> emailDomainDetail = emailDomainDetailsRepo.findById(id);

			if (emailDomainDetail.isPresent()) {
				List<Email> emailsForTheDomain = emailRepo.findByEmailDomailDetails(id);

				List<Email> updatedEmailsForTheDomain = new ArrayList<>();

				emailsForTheDomain.forEach(email -> {
					email.setStatus("0");
					email.getObjEmailDomainDetails().setStatus("0");
					updatedEmailsForTheDomain.add(email);
				});

				Boolean isUpdated = emailRepo.saveAll(updatedEmailsForTheDomain) != null;

				log.info("Emails status updated" + isUpdated + " for emailsDomailId:" + id);
			} else {
				response.setMsg("No domain details found for such id:" + id);
				response.setResponse("");
			}
			return response;
		} catch (Exception e) {
			response.setMsg("unable to delete server config details.");
			response.setResponse(null);
			return response;
		}

	}

}
