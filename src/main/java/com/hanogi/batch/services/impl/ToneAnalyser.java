package com.hanogi.batch.services.impl;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.hanogi.batch.dto.AggregatedTone;
import com.hanogi.batch.repositories.AggregatedToneRepositry;
import com.hanogi.batch.services.IToneAnalyser;

@Service
public class ToneAnalyser implements IToneAnalyser {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${analyser.type}")
	private String analyserType;

	@Value("${analyser.name}")
	private String analyserName;

	@Value("${analyser.url}")
	private String analyserUrl;

	@Value("${authToken}")
	private String authToken;

	@Autowired
	private AggregatedToneRepositry AggregatedToneRepositry;

	@PostConstruct
	public void createToneAnalyserInstance() {
		logger.info("Tone analyser properties analyserType:" + analyserType);
		logger.info("Tone analyser properties analyserName:" + analyserName);
	}

	@Override
	public String analyseTone(String messageBody) {
		switch (analyserName) {
		case "custom":
			getToneFromCustom(messageBody);
			break;

		default:
			break;
		}
		return messageBody;
	}

	private String getToneFromCustom(String messageBody) {

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

		// setting message data body
		body.add("text", messageBody);

		HttpHeaders headers = new HttpHeaders();

		// creating headers and setting authentication token
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		headers.add("Authorization", "Bearer " + authToken);

		HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);

		RestTemplate restTemplate = new RestTemplate();

		// Sending post request to rest end point of tone analyzer service
		ResponseEntity<String> res = restTemplate.exchange(analyserUrl, HttpMethod.POST, httpEntity, String.class);

		String responseBody = res.getBody();
		//boolean saveToneToDb = saveToneToDb(responseBody);

	/*	if (saveToneToDb) {
			logger.info("SAved");
		} else {
			logger.error("Failed");
		}*/
		return responseBody;
	}

	private boolean saveToneToDb(String toneMsg) {
		try {
			// Inserting the tone response to the DB
			AggregatedTone aggregatedTone = new AggregatedTone();

			aggregatedTone.setAggregatedTone(toneMsg);
			aggregatedTone.setStatus("1");

			return (AggregatedToneRepositry.save(aggregatedTone) != null);
		} catch (Exception e) {
			logger.error("Error:" + e.getMessage() + "while saving the analyser tone to DB");
			return false;
		}
	}

}
