package com.briller.acess.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.briller.acess.dto.RequestParamDashboard;
import com.briller.acess.response.Response;
import com.briller.acess.service.IDashboardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@Api(value = "Rest Services for briller")
public class DashboardController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IDashboardService iDashboardService;

	@RequestMapping("/getDashboardData/{loggedInUser}")
	@ApiOperation(value = "Running Dashboard data job for a particular Email-Id", response = String.class, consumes = "emailId of logged in user")
	public String getDashboardData(@PathVariable String loggedInUser) throws Exception {

		log.info("Inside getDashboardData" + loggedInUser);
		return loggedInUser;

	}

	@RequestMapping("/getDashboardData")
	@ApiOperation(value = "Running Dashboard data job no argument", response = String.class)
	public String getDashboardData() throws Exception {

		log.info("Inside getDashboardData no argument method");

		return "Default method";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/getDashboardData")
	@ApiOperation(value = "Running Dashboard data job no argument", response = String.class)
	public ResponseEntity<?> getDashboardData(@RequestBody RequestParamDashboard requestParam)
			throws Exception {

		log.info("Inside getDashboardData post  method" + requestParam.toString());
		Response response=iDashboardService.getDashboardData();
		return new  ResponseEntity<>(response, response.getResponse() != null ? HttpStatus.OK : HttpStatus.CONFLICT);
		
//		return "[{\"account_name\":\"CITI\",\"account_id\":2,\"relationships\":4,\"escalations\":1,\"total_interactions\":25,\"negative_interactions\":10,\"csat\":60,\"margin\":23,\"revenue\":12475678},"
//		+ "{\"account_name\":\"RBC\",\"account_id\":1,\"relationships\":25,\"escalations\":4,\"total_interactions\":50,\"negative_interactions\":4,\"csat\":80,\"margin\":32,\"revenue\":2345567}]";
	}


	@RequestMapping(method = RequestMethod.POST, value = "/getTeamRelationshipHealthForDashboard")
	@ApiOperation(value = "Running Dashboard data job no argument", response = String.class)
	public ResponseEntity<?> getTeamRelationshipHealthForDashboard(@RequestBody RequestParamDashboard requestParam)
			throws Exception {

		log.info("Inside getTeamRelationshipHealthForDashboard post  method" + requestParam.toString());
		
		Response response=iDashboardService.getTeamRelationshipHealthForDashboard(requestParam);
		return new ResponseEntity<>(response, response.getResponse() != null ? HttpStatus.OK : HttpStatus.CONFLICT);
		
//		return " [\r\n" + " {\r\n" + "   \"name\": \"Allison Johnson\",\r\n" + "   \"role\": \"Project Lead\",\r\n"
//				+ "   \"score\": 0.98,\r\n" + "   \"relationships\": 2\r\n" + " },\r\n" + " {\r\n"
//				+ "   \"name\": \"Andrew Hewins\",\r\n" + "   \"role\": \"Sales Associate\",\r\n"
//				+ "   \"score\": 0.88,\r\n" + "  \" relationships\": 4\r\n" + " }]";

	}

}
