package com.briller.acess.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ClientDashController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(method = RequestMethod.POST, value = "/getClientDashboardData")
	public String getClientDashboardData(@RequestBody Map<String, String> request) throws Exception {

		log.info("Inside getClientDashboardData" + request);

		// return iClientDashboardService.getClientDashboardData(request);

		return "{Response:\r\n" + "[\r\n" + "	{\r\n" + "	client: \"BOA\",\r\n"
				+ "	email: \"abhi@briller.com\",\r\n" + "	designation: \"\",\r\n" + "	name: \"Abhishek\",\r\n"
				+ "	imageURL: \"\",\r\n" + "		relationships: [\r\n" + "				{\r\n"
				+ "					name: \"ce1\",\r\n" + "					imageURL: \"\",\r\n"
				+ "					initals :\"\", \r\n" + "					numConversations: 5,\r\n"
				+ "					numEscalations: 2,\r\n" + "					score: 0.68,\r\n"
				+ "					employeeTones:{\r\n" + "								anger: \"12%\",\r\n"
				+ "								fear:\"8%\",\r\n"
				+ "								sadness:\"10%\",\r\n"
				+ "								joy: \"55%\",\r\n"
				+ "								confidence: \"15%\"\r\n" + "					},\r\n"
				+ "					customerTones:{\r\n" + "									anger: \"2%\",\r\n"
				+ "									fear:\"14%\",\r\n"
				+ "									sadness:\"15%\",\r\n"
				+ "									joy: \"78%\",\r\n"
				+ "									confidence: \"55%\"\r\n" + "					}\r\n"
				+ "				},\r\n" + "				{\r\n" + "					name: \"ce2\",\r\n"
				+ "					imageURL: \"\",\r\n" + "					initals :\"\", \r\n"
				+ "					numConversations: 10,\r\n" + "					numEscalations: 0,\r\n"
				+ "					score: 0.96,\r\n" + "					employeeTones:{\r\n"
				+ "									anger: \"43%\",\r\n"
				+ "									fear:\"32%\",\r\n"
				+ "									sadness:\"5%\",\r\n"
				+ "									joy: \"17%\",\r\n"
				+ "									confidence: \"32%\"\r\n"
				+ "								  },\r\n" + "					customerTones:{\r\n"
				+ "									anger: \"43%\",\r\n"
				+ "									fear:\"2%\",\r\n"
				+ "									sadness:\"10%\",\r\n"
				+ "									joy: \"22%\",\r\n"
				+ "									confidence: \"87%\"\r\n" + "					}\r\n"
				+ "				}\r\n" + "		]\r\n" + "	},\r\n" + "	{\r\n" + "	client: \"BOA\",\r\n"
				+ "	designation: \"\",\r\n" + "	email: \"mayank@briller.com\",\r\n" + "	name: \"Mayank\",\r\n"
				+ "	imageURL: \"\",\r\n" + "			relationships: [\r\n" + "				{\r\n"
				+ "				name: \"ce2\",\r\n" + "				imageURL: \"\",\r\n"
				+ "				numConversations: 7,\r\n" + "				numEscalations: 1,\r\n"
				+ "				score: 0.76,\r\n" + "					employeeTones:\r\n"
				+ "								  {\r\n" + "								anger: \"12%\",\r\n"
				+ "								fear:\"8%\",\r\n"
				+ "								sadness:\"10%\",\r\n"
				+ "								joy: \"55%\",\r\n"
				+ "								confidence: \"15%\"\r\n" + "								  },\r\n"
				+ "					customerTones:\r\n" + "								  {\r\n"
				+ "								anger: \"12%\",\r\n"
				+ "								fear:\"8%\",\r\n"
				+ "								sadness:\"10%\",\r\n"
				+ "								joy: \"55%\",\r\n"
				+ "								confidence: \"15%\"\r\n" + "								  }\r\n"
				+ "				},\r\n" + "				{\r\n" + "				name: \"ce3\",\r\n"
				+ "				imageURL: \"\",\r\n" + "				numConversations: 20,\r\n"
				+ "				numEscalations: 6,\r\n" + "				score: 0.50,\r\n"
				+ "					employeeTones:\r\n" + "								  {\r\n"
				+ "								anger: \"12%\",\r\n"
				+ "								fear:\"8%\",\r\n"
				+ "								sadness:\"10%\",\r\n"
				+ "								joy: \"55%\",\r\n"
				+ "								confidence: \"15%\"\r\n" + "								  },\r\n"
				+ "					customerTones:\r\n" + "								  {\r\n"
				+ "								anger: \"12%\",\r\n"
				+ "								fear:\"8%\",\r\n"
				+ "								sadness:\"10%\",\r\n"
				+ "								joy: \"55%\",\r\n"
				+ "								confidence: \"15%\"\r\n" + "								  }\r\n"
				+ "				}\r\n" + "			]\r\n" + "	}\r\n" + "]}";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/getMessagesStats")
	public String getMessagesStats(@RequestBody Map<String, String> request) throws Exception {

		return "{\r\n" + "	Response:{\r\n" + "				outbound: 57,\r\n" + "				inbound: 45,\r\n"
				+ "				avgResponseTime: \"65m\"\r\n" + "	}\r\n" + "}";
	}

}
