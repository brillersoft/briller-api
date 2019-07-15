package com.hanogi.batch.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hanogi.batch.response.Response;
import com.hanogi.batch.services.IBusniessHierarchyService;
import com.hanogi.batch.utility.Request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("/hierarchy")
@CrossOrigin
@Api(value = "Business Hierarchy Api Services")
public class BusniessHierarchyController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBusniessHierarchyService bhService;

	@GetMapping("/getCompleteHierarchy")
	public ResponseEntity<?> getCompleteHierarchy() {
		Map<String, List<Map<String, Object>>> completeHierarchy = bhService.getCompleteHierarchy();
		return new ResponseEntity<>(completeHierarchy,
				(completeHierarchy != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}

	@PostMapping("/saveBusinessDivision")
	@ApiOperation(value = "Add/Update division in the unit", response = ResponseEntity.class, consumes = "Will consume new division.")
	public ResponseEntity<?> saveBusinessDivision(@RequestBody Request request) {
		log.info("Inside saveBusinessDivision" + request);

		Response response = bhService.saveBusinessDivision(request);

		return new ResponseEntity<>(response, (response.getResponse() != null) ? HttpStatus.OK : HttpStatus.CONFLICT);
	}

	@PostMapping("/saveBusinessAccount")
	@ApiOperation(value = "Add/Update division in the unit", response = ResponseEntity.class, consumes = "Will consume new Account.")
	public ResponseEntity<?> saveBusinessAccount(@RequestBody Request request) {
		log.info("Inside saveBusinessAccount" + request);

		Response response = bhService.saveBusinessAccount(request);

		return new ResponseEntity<>(response, (response.getResponse() != null) ? HttpStatus.OK : HttpStatus.CONFLICT);
	}

	@PostMapping("/saveBusinessGroup")
	@ApiOperation(value = "Add/Update Group in the unit", response = ResponseEntity.class, consumes = "Will consume new Group.")
	public ResponseEntity<?> saveBusinessGroup(@RequestBody Request request) {
		log.info("Inside saveBusinessGroup" + request);

		Response response = bhService.saveBusinessGroup(request);

		return new ResponseEntity<>(response, (response.getResponse() != null) ? HttpStatus.OK : HttpStatus.CONFLICT);
	}
	
	@PostMapping("/saveBusinessUnit")
	@ApiOperation(value = "Add new business unit", response = ResponseEntity.class, consumes = "Will business unit details.")
	public ResponseEntity<?> saveBusinessUnit(@RequestBody Request request) {
		log.info("Inside saveBusinessUnit" + request);
		Response response = bhService.saveBusinessUnit(request);
		return new ResponseEntity<>(response, (response.getResponse() != null) ? HttpStatus.OK : HttpStatus.CONFLICT);
	}

	@PostMapping("/saveBusinessTeam")
	@ApiOperation(value = "Add/Update Team in the unit", response = ResponseEntity.class, consumes = "Will consume new Team.")
	public ResponseEntity<?> saveBusinessTeam(@RequestBody Request request) {
		log.info("Inside saveBusinessTeam" + request);

		Response response = bhService.saveBusinessTeam(request);

		return new ResponseEntity<>(response, (response.getResponse() != null) ? HttpStatus.OK : HttpStatus.CONFLICT);
	}

	@GetMapping("/deleteBusinessDivision/{id}")
	@ApiOperation(value = "Delete existing division in the unit", response = ResponseEntity.class, consumes = "Will consume new division id to delete.")
	public ResponseEntity<?> deleteBusinessDivision(@PathVariable("id") Integer id) {
		log.info("Inside deleteBusinessDivision" + id);

		Response response = bhService.deleteBusinessDivision(id);

		return new ResponseEntity<>(response, (response.getResponse() != null) ? HttpStatus.OK : HttpStatus.CONFLICT);
	}

	@GetMapping("/deleteBusinessGroup/{id}")
	@ApiOperation(value = "Delete existing group in the account", response = ResponseEntity.class, consumes = "Will consume new group id to delete.")
	public ResponseEntity<?> deleteBusinessGroup(@PathVariable("id") Integer id) {
		log.info("Inside deleteBusinessGroup" + id);

		Response response = bhService.deleteBusinessGroup(id);

		return new ResponseEntity<>(response, (response.getResponse() != null) ? HttpStatus.OK : HttpStatus.CONFLICT);
	}

	@GetMapping("/deleteBusinessAccount/{id}")
	@ApiOperation(value = "Delete existing account in the division", response = ResponseEntity.class, consumes = "Will consume new account id to delete.")
	public ResponseEntity<?> deleteBusinessAccount(@PathVariable("id") Integer id) {
		log.info("Inside deleteBusinessAccount" + id);

		Response response = bhService.deleteBusinessAccount(id);

		return new ResponseEntity<>(response, (response.getResponse() != null) ? HttpStatus.OK : HttpStatus.CONFLICT);
	}
	
	@GetMapping("/deleteBusinessTeam/{id}")
	@ApiOperation(value = "Delete existing team in the group", response = ResponseEntity.class, consumes = "Will consume new team id to delete.")
	public ResponseEntity<?> deleteBusinessTeam(@PathVariable("id") Integer id) {
		log.info("Inside deleteBusinessTeam" + id);

		Response response = bhService.deleteBusinessTeam(id);

		return new ResponseEntity<>(response, (response.getResponse() != null) ? HttpStatus.OK : HttpStatus.CONFLICT);
	}
	
	@GetMapping("/getEmpHierarchy")
	@ApiOperation(value = "retrive emp hierarchy", response = ResponseEntity.class)
	public ResponseEntity<?> getEmpHierarchy() {
		log.info("Inside getEmpHierarchy");

		Response response = bhService.getEmpHierarchy();

		return new ResponseEntity<>(response, (response.getResponse() != null) ? HttpStatus.OK : HttpStatus.CONFLICT);
	}
}
