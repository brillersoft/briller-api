package com.briller.acess.service;

import java.util.LinkedHashMap;
import java.util.List;


public interface IDashboardService {

	void getDashBoardMenuItems(String loggedinUser);

	void getTeamRelationshipHealthForDashboard();

	List<LinkedHashMap<String, Object>> getDashboardData();

}
