package com.briller.acess.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.briller.acess.dashboard.repositories.DashboardRepository;
import com.briller.acess.dto.DashboardData;
import com.briller.acess.dto.RequestParamDashboard;
import com.briller.acess.service.IDashboardService;

@Component
public class DashboardServiceImpl implements IDashboardService {

	@Autowired
	private DashboardRepository dashboardRepository ;
	
	public DashboardServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getDashBoardMenuItems(String loggedinUser) {
		// TODO Auto-generated method stub
		
	}
	
	public List<DashboardData> clientOveriewData(RequestParamDashboard paramsForDashboard){
		
		List<DashboardData>  dashboardData = new ArrayList<DashboardData>();
		  dashboardRepository.findAll().forEach(dashboardData :: add);
		  return dashboardData;
		 
	}

}
