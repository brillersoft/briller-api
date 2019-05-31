package com.briller.acess.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.briller.acess.dashboard.repositories.AccountRepository;
import com.briller.acess.dashboard.repositories.DashboardRepository;
import com.briller.acess.dashboard.repositories.EmployeeCsatSummaryRepo;
import com.briller.acess.dashboard.repositories.EmployeeRepo;
import com.briller.acess.dashboard.repositories.EmployeeRoleMappingRepo;
import com.briller.acess.dashboard.repositories.RoleRepo;
import com.briller.acess.dto.Account;
import com.briller.acess.dto.DashboardData;
import com.briller.acess.dto.Employee;
import com.briller.acess.dto.EmployeeCsatSummary;
import com.briller.acess.dto.RequestParamDashboard;
import com.briller.acess.response.Response;
import com.briller.acess.service.IDashboardService;
import com.briller.acess.teamRelationshipHealth.TeamRelationshipHealth;
import com.briller.acess.teamRelationshipHealth.TeamRelationshipHealthDashboardData;

@Component
public class DashboardServiceImpl implements IDashboardService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private DashboardRepository dashboardRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private EmployeeCsatSummaryRepo employeeCsatSummaryRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeRoleMappingRepo employeeRoleMappingRepo;

	@Autowired
	private RoleRepo roleRepo;

	public DashboardServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getDashBoardMenuItems(String loggedinUser) {
		// TODO Auto-generated method stub

	}

	public List<DashboardData> clientOveriewData(RequestParamDashboard paramsForDashboard) {

		List<DashboardData> dashboardData = new ArrayList<DashboardData>();
		dashboardRepository.findAll().forEach(dashboardData::add);
		return dashboardData;

	}

	public Response getDashboardData() {
		Response response=new Response();
		List<LinkedHashMap<String, Object>> accountDetailsList = new ArrayList<>();

		log.info("Inside get Dashboard Data post  method -- service Implementation");
		List<Account> accountDashBoardDataList = accountRepository.findAll();

		for (Account account : accountDashBoardDataList) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();

			map.put("account_id", account.getAccountId());
			map.put("account_name", account.getAccountName());

			map.put("relationships", account.getAccountCsatSummary().getRelationships());
			map.put("escalations", account.getAccountCsatSummary().getEscalations());
			map.put("totalInteractions", account.getAccountCsatSummary().getTotalInteractions());
			map.put("negativeInteractions", account.getAccountCsatSummary().getNegativeInteractions());
			map.put("csat", account.getAccountCsatSummary().getCsat());
			map.put("margin",account.getCrmAccountData().getMargin());
			map.put("revenue",account.getCrmAccountData().getRevenue());
			
			accountDetailsList.add(map);
		}
		response.setMsg("OK");
		response.setResponse(accountDetailsList);
		return response;

	}

	@Override
	public Response getTeamRelationshipHealthForDashboard(RequestParamDashboard requestParam) {
		// TODO Auto-generated method stub
		Response response=null;
		if(requestParam!=null)
		{
			response = new Response();
			log.info("Inside get getTeamRelationshipHealthForDashboard Data post  method -- service Implementation");
			Account account = accountRepository.findByAccountName(requestParam.getClient().toUpperCase());
			
			if(account!=null)
			{
				TeamRelationshipHealthDashboardData teamRelationshipData = new TeamRelationshipHealthDashboardData();
				List<TeamRelationshipHealth> teamList = new ArrayList<>();

				//List<EmployeeCsatSummary> clientEmployees = employeeCsatSummaryRepo.findByAccountIdDistinctEmployee(account.getAccountId());
				//System.out.println(clientEmployees);
				List<Integer> Employees = employeeCsatSummaryRepo.findDistinctEmployees(account.getAccountId());
				for (int i : Employees) {
					Employee emp = employeeRepo.findByEmployeeId(i);

					TeamRelationshipHealth teamData = new TeamRelationshipHealth();
					teamData.setName(emp.getFirstName() + " " + emp.getLastName());

					// Mapping by RoleId in Employee_role_Mapping Table
					int roleId = employeeRoleMappingRepo.findByEmployeeId(i).getRole().getRoleId();

					teamData.setRole(roleRepo.findByRoleId(roleId).getRoleName());
					teamData.setRelationships(account.getAccountCsatSummary().getRelationships());

					// Score value hardCoded to 0.82
					teamData.setScore(0.82);
					teamList.add(teamData);
				}
				teamRelationshipData.setTeamRelationshipHealth(teamList);
				response.setMsg("OK");
				response.setResponse(teamRelationshipData);				
			}
			else
			{
				response.setMsg("No such Client exists");
			}


		}
		return response;
		
	}


}
