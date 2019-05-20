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
import com.briller.acess.dto.Account;
import com.briller.acess.dto.DashboardData;
import com.briller.acess.dto.RequestParamDashboard;
import com.briller.acess.service.IDashboardService;

@Component
public class DashboardServiceImpl implements IDashboardService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private DashboardRepository dashboardRepository;

	@Autowired
	private AccountRepository accountRepository;

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

	public List<LinkedHashMap<String, Object>> getDashboardData() {
		List<LinkedHashMap<String, Object>> accountDetailsList = new ArrayList<>();
		
		log.info("Inside getTeamRelationshipHealthForDashboard post  method -- service Implementation");
		List<Account> accountDashBoardDataList = accountRepository.findAll();

		for (Account account : accountDashBoardDataList) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			
			map.put("account_id", account.getAccountId());
			map.put("account_name",account.getAccountName());
			
			map.put("relationships", account.getAccountCsatSummary().getRelationships());
			map.put("escalations", account.getAccountCsatSummary().getEscalations());
			map.put("totalInteractions", account.getAccountCsatSummary().getTotalInteractions());
			map.put("negativeInteractions", account.getAccountCsatSummary().getNegativeInteractions());
			map.put("csat", account.getAccountCsatSummary().getCsat());
			
			map.put("margin", account.getCrmAccountData().getMargin());
			map.put("revenue",account.getCrmAccountData().getRevenue());
			accountDetailsList.add(map);
		}
		
		return accountDetailsList;

	}

	@Override
	public void getTeamRelationshipHealthForDashboard() {
		// TODO Auto-generated method stub

	}

}
