package com.hanogi.batch.servicesImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanogi.batch.entity.Employee;
import com.hanogi.batch.entity.EmployeeHierarchy;
import com.hanogi.batch.entity.business.BusinessAccount;
import com.hanogi.batch.entity.business.BusinessDivision;
import com.hanogi.batch.entity.business.BusinessGroup;
import com.hanogi.batch.entity.business.BusinessTeam;
import com.hanogi.batch.entity.business.BusinessUnit;
import com.hanogi.batch.repositry.BusinessAccountRepo;
import com.hanogi.batch.repositry.BusinessDivisionRepo;
import com.hanogi.batch.repositry.BusinessGroupRepo;
import com.hanogi.batch.repositry.BusinessTeamRepo;
import com.hanogi.batch.repositry.BusinessUnitRepo;
import com.hanogi.batch.repositry.EmailRepositry;
import com.hanogi.batch.repositry.EmployeeHierarchyRepo;
import com.hanogi.batch.repositry.EmployeeRepositry;
import com.hanogi.batch.response.Response;
import com.hanogi.batch.services.IBusniessHierarchyService;
import com.hanogi.batch.utility.Request;

@Service
public class BusniessHierarchyService implements IBusniessHierarchyService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private BusinessDivisionRepo bDivisionRepo;

	@Autowired
	private BusinessAccountRepo bAccountRepo;

	@Autowired
	private BusinessGroupRepo bGroupRepo;

	@Autowired
	private BusinessTeamRepo teamRepo;

	@Autowired
	private BusinessUnitRepo bUnitRepo;

	@Autowired
	private EmployeeHierarchyRepo empHierarchyRepo;

	@Autowired
	private EmployeeRepositry employeeRepo;

	@Autowired
	private EmailRepositry emailRepo;

	@Override
	public Map<String, List<Map<String, Object>>> getCompleteHierarchy() {
		Map<String, List<Map<String, Object>>> hierarchyMap = new HashMap<String, List<Map<String, Object>>>();

		try {
			List<BusinessDivision> allBusinessDivisions = bDivisionRepo.findAll();

			if (allBusinessDivisions != null) {
				List<Map<String, Object>> divisionList = new ArrayList<Map<String, Object>>();
				for (BusinessDivision businessDivision : allBusinessDivisions) {
					Map<String, Object> divisionMap = new HashMap<String, Object>();

					divisionMap.put("id", businessDivision.getDivisionId());
					divisionMap.put("code", businessDivision.getDivisionCode());
					divisionMap.put("name", businessDivision.getDivisionName());
					divisionMap.put("desc", businessDivision.getDivisionDesc());

					BusinessUnit businessUnit = businessDivision.getbUnit();
					divisionMap.put("parent_unit", (businessUnit != null) ? businessUnit.getBusinessUnitName() : null);
					divisionMap.put("parent_unit_id", (businessUnit != null) ? businessUnit.getBusinessUnitId() : null);

					divisionList.add(divisionMap);
				}

				hierarchyMap.put("division", divisionList);

			}

			List<BusinessAccount> businessAccountsList = bAccountRepo.findAll();

			if (businessAccountsList != null) {
				List<Map<String, Object>> accountList = new ArrayList<Map<String, Object>>();

				for (BusinessAccount businessAccount : businessAccountsList) {
					Map<String, Object> accountMap = new HashMap<String, Object>();

					accountMap.put("id", businessAccount.getAccountId());
					accountMap.put("code", businessAccount.getAccountCode());
					accountMap.put("name", businessAccount.getAccountName());
					accountMap.put("desc", businessAccount.getAccountDesc());

					BusinessDivision businessDivision = businessAccount.getbDivision();
					accountMap.put("parent_unit",
							(businessDivision != null) ? businessDivision.getDivisionName() : null);
					accountMap.put("parent_unit_id",
							(businessDivision != null) ? businessDivision.getDivisionId() : null);

					accountList.add(accountMap);

				}

				hierarchyMap.put("account", accountList);
			}

			List<BusinessGroup> businessGroupsList = bGroupRepo.findAll();

			if (businessGroupsList != null) {
				List<Map<String, Object>> bgroupList = new ArrayList<Map<String, Object>>();

				for (BusinessGroup businessGroup : businessGroupsList) {
					Map<String, Object> bGroupMap = new HashMap<String, Object>();

					bGroupMap.put("id", businessGroup.getGroupId());
					bGroupMap.put("code", businessGroup.getGroupCode());
					bGroupMap.put("name", businessGroup.getGroupName());
					bGroupMap.put("desc", businessGroup.getGroupDesc());

					BusinessAccount account = businessGroup.getBusinessAccount();
					bGroupMap.put("parent_unit", (account != null) ? account.getAccountName() : null);
					bGroupMap.put("parent_unit_id", (account != null) ? account.getAccountId() : null);

					bgroupList.add(bGroupMap);

				}

				hierarchyMap.put("group", bgroupList);
			}

			List<BusinessTeam> businessteamsList = teamRepo.findAll();

			if (businessteamsList != null) {
				List<Map<String, Object>> teamList = new ArrayList<Map<String, Object>>();

				for (BusinessTeam bTeam : businessteamsList) {
					Map<String, Object> bTeamMap = new HashMap<String, Object>();

					bTeamMap.put("id", bTeam.getTeamId());
					bTeamMap.put("code", bTeam.getTeamCode());
					bTeamMap.put("name", bTeam.getTeamName());
					bTeamMap.put("desc", bTeam.getTeamDesc());

					BusinessGroup group = bTeam.getBusinessGroup();
					bTeamMap.put("parent_unit", (group != null) ? group.getGroupName() : null);
					bTeamMap.put("parent_unit_id", (group != null) ? group.getGroupId() : null);

					teamList.add(bTeamMap);

				}

				hierarchyMap.put("team", teamList);
			}

			List<BusinessUnit> businessUnitList = bUnitRepo.findAll();

			if (businessUnitList != null) {
				List<Map<String, Object>> bUnitList = new ArrayList<Map<String, Object>>();

				for (BusinessUnit bUnit : businessUnitList) {
					Map<String, Object> bUnitMap = new HashMap<String, Object>();

					bUnitMap.put("id", bUnit.getBusinessUnitId());
					bUnitMap.put("code", bUnit.getBusinessUnitCode());
					bUnitMap.put("name", bUnit.getBusinessUnitName());
					bUnitMap.put("desc", bUnit.getBusinessUnitDesc());

					bUnitList.add(bUnitMap);

				}

				hierarchyMap.put("unit", bUnitList);
			}
			return hierarchyMap;
		} catch (Exception e) {
			log.error("Error in getCompleteHierarchy:" + e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public Response saveBusinessDivision(Request request) {
		Response response = new Response();

		try {
			if (request != null) {
				if (request.getRequestType().equalsIgnoreCase("saveOrupdatehierarchy")) {

					Map<String, Object> requestParam = request.getRequestParam();

					if (requestParam != null) {

						String newName = (String) requestParam.get("name");
						String code = (String) requestParam.get("code");
						String desc = (String) requestParam.get("desc");
						String lastName = (String) requestParam.get("last_name");
						Integer parentId = null;
						try {
							parentId = (Integer) requestParam.get("parent_unit_id");
						} catch (Exception e) {
							response.setMsg("Parent_Id value type is mismatched");
							response.setResponse(null);
							return response;
						}

						BusinessDivision bDivision = bDivisionRepo.findByDivisionName(lastName);

						if (bDivision == null) {
							BusinessDivision businessDivision = new BusinessDivision();
							businessDivision.setDivisionCode(code);
							businessDivision.setDivisionDesc(desc);
							businessDivision.setDivisionName(newName);

							bUnitRepo.findById(parentId).map(bUnit -> {
								businessDivision.setbUnit(bUnit);
								return bDivisionRepo.save(businessDivision);
							}).orElseThrow(() -> new Exception("Error while saving the business division."));

							response.setMsg("Success");
							response.setResponse("Created new division:" + newName + "successfully");
						} else {
							bDivision.setDivisionCode(code);
							bDivision.setDivisionDesc(desc);
							bDivision.setDivisionName(newName);

							bUnitRepo.findById(parentId).map(bUnit -> {
								bDivision.setbUnit(bUnit);
								return bDivisionRepo.save(bDivision);
							}).orElseThrow(() -> new Exception("Error while saving the business division."));

							response.setMsg("Success");
							response.setResponse("Update division details.");
						}

					} else {
						response.setMsg("required parameter is missing.");
					}
				} else {
					response.setMsg("request is not matched");
				}
			} else {
				response.setMsg("request can not be null");
			}
			return response;
		} catch (Exception e) {
			log.error("Error in saveBusinessDivision:" + e.getMessage());
			response.setMsg("Internal Server Error.");
			response.setResponse(null);
			return response;
		}

	}

	@Override
	@Transactional
	public Response deleteBusinessDivision(Integer id) {
		log.info("deleting business division");
		Response response = new Response();

		try {
			bDivisionRepo.deleteById(id);
			response.setMsg("Deleted successfully.");
			response.setResponse("");
			return response;
		} catch (Exception e) {
			log.error("Error while deleting business division from the unit:" + e.getMessage());
			response.setMsg("Unable to delete dew to Internal server error.");
			response.setResponse(null);
			return response;
		}
	}

	@Override
	@Transactional
	public Response deleteBusinessGroup(Integer id) {
		log.info("deleting business group");
		Response response = new Response();

		try {
			bGroupRepo.deleteById(id);
			response.setMsg("Deleted successfully.");
			response.setResponse("");
			return response;
		} catch (Exception e) {
			log.error("Error while deleting business group from the account:" + e.getMessage());
			response.setMsg("Unable to delete dew to Internal server error.");
			response.setResponse(null);
			return response;
		}
	}

	@Override
	@Transactional
	public Response deleteBusinessAccount(Integer id) {
		log.info("deleting business account");
		Response response = new Response();

		try {
			bAccountRepo.deleteById(id);
			response.setMsg("Deleted successfully.");
			response.setResponse("");
			return response;
		} catch (Exception e) {
			log.error("Error while deleting business account from the division:" + e.getMessage());
			response.setMsg("Unable to delete dew to Internal server error.");
			response.setResponse(null);
			return response;
		}
	}

	@Override
	@Transactional
	public Response deleteBusinessTeam(Integer id) {
		log.info("deleting business team");
		Response response = new Response();

		try {
			teamRepo.deleteById(id);
			response.setMsg("Deleted successfully.");
			response.setResponse("");
			return response;
		} catch (Exception e) {
			log.error("Error while deleting business team from the group:" + e.getMessage());
			response.setMsg("Unable to delete dew to Internal server error.");
			response.setResponse(null);
			return response;
		}
	}

	@Override
	public Response saveBusinessAccount(Request request) {

		Response response = new Response();

		try {
			if (request != null) {
				if (request.getRequestType().equalsIgnoreCase("saveOrupdatehierarchy")) {

					Map<String, Object> requestParam = request.getRequestParam();

					if (requestParam != null) {

						String newName = (String) requestParam.get("name");
						String code = (String) requestParam.get("code");
						String desc = (String) requestParam.get("desc");
						String lastName = (String) requestParam.get("last_name");
						Integer parentId = (Integer) requestParam.get("parent_unit_id");

						BusinessAccount bAccount = bAccountRepo.findByAccountName(lastName);

						if (bAccount == null) {
							BusinessAccount businessAccount = new BusinessAccount();
							businessAccount.setAccountCode(code);
							businessAccount.setAccountDesc(desc);
							businessAccount.setAccountName(newName);
							businessAccount.setStatus("1");
							BusinessAccount savedBusinessAccount = bDivisionRepo.findById(parentId).map(bDiv -> {
								businessAccount.setbDivision(bDiv);
								return bAccountRepo.save(businessAccount);
							}).orElseThrow(() -> new Exception("Error while saving the business Account."));

							response.setMsg("Success");
							response.setResponse(savedBusinessAccount.getAccountId());
						} else {
							bAccount.setAccountCode(code);
							bAccount.setAccountDesc(desc);
							bAccount.setAccountName(newName);

							bDivisionRepo.findById(parentId).map(bDiv -> {
								bAccount.setbDivision(bDiv);
								return bAccountRepo.save(bAccount);
							}).orElseThrow(() -> new Exception("Error while saving the business Account."));

							response.setMsg("Success");
							response.setResponse("Updated successfully");
						}

					} else {
						response.setMsg("required parameter is missing.");
					}
				} else {
					response.setMsg("request is not matched");
				}
			} else {
				response.setMsg("request can not be null");
			}
			return response;
		} catch (Exception e) {
			log.error("Error in saveBusinessAccount:" + e.getMessage());
			response.setMsg("Internal Server Error.");
			response.setResponse(null);
			return response;
		}

	}

	@Override
	public Response saveBusinessGroup(Request request) {

		Response response = new Response();

		try {
			if (request != null) {
				if (request.getRequestType().equalsIgnoreCase("saveOrupdatehierarchy")) {

					Map<String, Object> requestParam = request.getRequestParam();

					if (requestParam != null) {

						String newName = (String) requestParam.get("name");
						String code = (String) requestParam.get("code");
						String desc = (String) requestParam.get("desc");
						String lastName = (String) requestParam.get("last_name");
						Integer parentId = (Integer) requestParam.get("parent_unit_id");

						BusinessGroup bGroup = bGroupRepo.findByGroupName(lastName);

						if (bGroup == null) {
							BusinessGroup businessroup = new BusinessGroup();
							businessroup.setGroupCode(code);
							businessroup.setGroupDesc(desc);
							businessroup.setGroupName(newName);

							BusinessGroup savedBusinessGroup = bAccountRepo.findById(parentId).map(bAccount -> {
								businessroup.setBusinessAccount(bAccount);
								return bGroupRepo.save(businessroup);
							}).orElseThrow(() -> new Exception("Error while saving the business Account."));

							response.setMsg("Success");
							response.setResponse(savedBusinessGroup.getGroupId());
						} else {
							bGroup.setGroupCode(code);
							bGroup.setGroupDesc(desc);
							bGroup.setGroupName(newName);

							BusinessGroup businessGroup = bAccountRepo.findById(parentId).map(bAccount -> {
								bGroup.setBusinessAccount(bAccount);
								return bGroupRepo.save(bGroup);
							}).orElseThrow(() -> new Exception("Error while saving the business Account."));

							response.setMsg("Success");
							response.setResponse("Update Account details.");
						}

					} else {
						response.setMsg("required parameter is missing.");
					}
				} else {
					response.setMsg("request is not matched");
				}
			} else {
				response.setMsg("request can not be null");
			}
			return response;
		} catch (Exception e) {
			log.error("Error in saveBusinessAccount:" + e.getMessage());
			response.setMsg("Internal Server Error.");
			response.setResponse(null);
			return response;
		}

	}

	@Override
	public Response saveBusinessTeam(Request request) {

		Response response = new Response();

		try {
			if (request != null) {
				if (request.getRequestType().equalsIgnoreCase("saveOrupdatehierarchy")) {

					Map<String, Object> requestParam = request.getRequestParam();

					if (requestParam != null) {

						String newName = (String) requestParam.get("name");
						String code = (String) requestParam.get("code");
						String desc = (String) requestParam.get("desc");
						String lastName = (String) requestParam.get("last_name");
						Integer parentId = (Integer) requestParam.get("parent_unit_id");

						BusinessTeam bTeam = teamRepo.findByTeamName(lastName);

						if (bTeam == null) {
							BusinessTeam businessTeam = new BusinessTeam();
							businessTeam.setTeamCode(code);
							businessTeam.setTeamDesc(desc);
							businessTeam.setTeamName(newName);

							BusinessTeam savedBusinessTeam = bGroupRepo.findById(parentId).map(bGroup -> {
								businessTeam.setBusinessGroup(bGroup);
								return teamRepo.save(businessTeam);
							}).orElseThrow(() -> new Exception("Error while saving the business team."));
							response.setMsg("Success");
							response.setResponse(savedBusinessTeam.getTeamId());
						} else {
							bTeam.setTeamCode(code);
							bTeam.setTeamDesc(desc);
							bTeam.setTeamName(newName);

							bGroupRepo.findById(parentId).map(bGroup -> {
								bTeam.setBusinessGroup(bGroup);
								return teamRepo.save(bTeam);
							}).orElseThrow(() -> new Exception("Error while saving the business Team."));

							response.setMsg("Success");
							response.setResponse("Update Team details.");
						}

					} else {
						response.setMsg("required parameter is missing.");
					}
				} else {
					response.setMsg("request is not matched");
				}
			} else {
				response.setMsg("request can not be null");
			}
			return response;
		} catch (Exception e) {
			log.error("Error in saveBusinessTeam:" + e.getMessage());
			response.setMsg("Internal Server Error.");
			response.setResponse(null);
			return response;
		}

	}

	@Override
	public Response saveBusinessUnit(Request request) {

		Response response = new Response();

		try {
			if (request != null) {
				if (request.getRequestType().equalsIgnoreCase("saveOrupdatehierarchy")) {

					Map<String, Object> requestParam = request.getRequestParam();

					if (requestParam != null) {

						String newName = (String) requestParam.get("name");
						String code = (String) requestParam.get("code");
						String desc = (String) requestParam.get("desc");
						String lastName = (String) requestParam.get("last_name");
						// Integer parentId = (Integer) requestParam.get("parent_unit_id");

						BusinessUnit bUnit = bUnitRepo.findByBusinessUnitName(lastName);

						if (bUnit == null) {
							BusinessUnit businessUnit = new BusinessUnit();
							businessUnit.setBusinessUnitCode(code);
							businessUnit.setBusinessUnitDesc(desc);
							businessUnit.setBusinessUnitName(newName);

							BusinessUnit savedUnit = bUnitRepo.save(businessUnit);

							if (savedUnit != null) {
								response.setMsg("Success");
								response.setResponse(savedUnit.getBusinessUnitId());
							} else {
								response.setMsg("Error while saving details.");
								response.setResponse(null);
							}
						} else {
							bUnit.setBusinessUnitCode(code);
							bUnit.setBusinessUnitDesc(desc);
							bUnit.setBusinessUnitName(newName);

							BusinessUnit savedUnit = bUnitRepo.save(bUnit);

							if (savedUnit != null) {
								response.setMsg("Success");
								response.setResponse("Update Business unit details.");
							} else {
								response.setMsg("Error while updating details.");
								response.setResponse(null);
							}
						}

					} else {
						response.setMsg("required parameter is missing.");
					}
				} else {
					response.setMsg("request is not matched");
				}
			} else {
				response.setMsg("request can not be null");
			}
			return response;
		} catch (Exception e) {
			log.error("Error in saveBusinessUnit:" + e.getMessage());
			response.setMsg("Internal Server Error.");
			response.setResponse(null);
			return response;
		}

	}

	@Override
	public Response getEmpHierarchy() {
		Response response = new Response();

		try {
			List<Map<String, Object>> empHierarchyList = new ArrayList<>();

			List<EmployeeHierarchy> completeEmployeeHierarcy = empHierarchyRepo.findAll();

			for (EmployeeHierarchy employeeHierarchy : completeEmployeeHierarcy) {
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("id", employeeHierarchy.getEmployeeId());

				Employee employee = employeeRepo.findById(employeeHierarchy.getEmployeeId()).get();

				String mName = employee.getMiddleName().isEmpty() ? " " : employee.getMiddleName() + " ";

				String name = employee.getFirstName() + " " + mName + employee.getLastName();

				map.put("name", name.toUpperCase());
				Integer managerId = employeeHierarchy.getManagerId();
				map.put("managerId", managerId);
				if (managerId != null) {
					Employee employeeManager = employeeRepo.findById(managerId).get();

					String mangerMName = employeeManager.getMiddleName().isEmpty() ? " "
							: employeeManager.getMiddleName() + " ";

					String mangerName = employeeManager.getFirstName() + " " + mangerMName + employeeManager.getLastName();
					
					map.put("managerName", mangerName);
				}else {
					map.put("managerName", "");
				}

				map.put("mailId", "testmail@mail.com");

				empHierarchyList.add(map);

			}

			if (empHierarchyList.size() != 0) {
				response.setMsg("Success");
				response.setResponse(empHierarchyList);
			} else {
				response.setMsg("No records found.");
				response.setResponse(null);
			}
			return response;
		} catch (Exception e) {
			return response;
		}

	}

}
