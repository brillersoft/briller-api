package com.briller.acess.service;

import java.util.Map;

import com.briller.acess.response.Response;

public interface IClientDashboardService {

	Response getClientDashboardData(Map<String,String> request);
}
