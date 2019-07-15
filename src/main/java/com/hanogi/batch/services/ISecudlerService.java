package com.hanogi.batch.services;

import java.util.List;
import java.util.Map;

import com.hanogi.batch.response.Response;
import com.hanogi.batch.utility.Request;

public interface ISecudlerService {

	// public boolean saveNewScheduler(SchedulerJobInfo schedulerJobInfo);

	public Integer saveNewSchedulerBatchDetails(Map<String, String> dateRange);

	public Map<String, Object> getConfigOptions();

	public List<Map<String, String>> getAllServerConfigs();

	public List<Map<String, Object>> getAllSchedulers();

	public Boolean saveConfigOptions(Request request);

	public Boolean scheduleNewJobs(Request scheduleJobRequest);

	public Boolean saveMailIdList(Request scheduleJobRequest);

	public Boolean updateConfigOptions(Request configRequest);

	public Object getWorldCountry();

	public Object getCities();

	public Boolean saveNewAccount(Request request);

	public Object getBatchStatus();

	public Response saveEntity(Request request);

	public Response updateEntityDetail(Request request);

	public List<Map<String, Object>> getAllEntities();

	public Response saveNewEmployee(Request request);

	public Response saveNewClient(Request request);

	public Response deleteServerConfigOptions(Integer id);

}
