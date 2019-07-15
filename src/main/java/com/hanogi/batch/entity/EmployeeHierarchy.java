package com.hanogi.batch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.hanogi.batch.audits.AuditFields;
import com.hanogi.batch.utility.HierarchyLevel;

@Entity
@Table(name = "employee_hirerachy")
public class EmployeeHierarchy extends AuditFields<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer hierarchyId;

	private Integer employeeId;

	@Column(name = "reports_to_id")
	private Integer managerId;

	@Version
	private Integer versionNum;
	
	//@Enumerated(EnumType.STRING)
	private String hierarchyLevel; 

	private String status = "1";

	public Integer getHierarchyId() {
		return hierarchyId;
	}

	public void setHierarchyId(Integer hierarchyId) {
		this.hierarchyId = hierarchyId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(String hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}

}
