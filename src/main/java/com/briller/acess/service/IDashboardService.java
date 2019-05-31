package com.briller.acess.service;

import com.briller.acess.dto.RequestParamDashboard;
import com.briller.acess.response.Response;


public interface IDashboardService {

	void getDashBoardMenuItems(String loggedinUser);

	Response getDashboardData();
	
	Response getTeamRelationshipHealthForDashboard(RequestParamDashboard requestParam);
	
}
