package com.hanogi.batch.services;

import java.util.List;
import java.util.Map;

import com.hanogi.batch.response.Response;
import com.hanogi.batch.utility.Request;

public interface IBusniessHierarchyService {

	public Map<String,List<Map<String, Object>>> getCompleteHierarchy();
	
	public Response saveBusinessDivision(Request request);

	public Response deleteBusinessDivision(Integer id);

	public Response saveBusinessAccount(Request request);

	public Response saveBusinessGroup(Request request);

	public Response saveBusinessTeam(Request request);

	public Response deleteBusinessGroup(Integer id);

	public Response deleteBusinessAccount(Integer id);

	public Response deleteBusinessTeam(Integer id);

	public Response saveBusinessUnit(Request request);

	public Response getEmpHierarchy();
}
