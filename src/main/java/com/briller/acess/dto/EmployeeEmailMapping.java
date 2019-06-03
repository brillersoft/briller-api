package com.briller.acess.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.briller.acess.configs.audit.AuditFields;

/**
 * @author abhishek.gupta02
 *
 */

@Entity
@Table(name = "EMPLOYEE_EMAILID_MAPPING")
public class EmployeeEmailMapping extends  AuditFields<String> {
	
	@Column(name = "EMPLOYEE_ID")
	private String employeeId;
	
	@Id
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Version
	private Integer versionNum;

	@Column(name = "ANALYSE_TONE")
	private String analyseTone;

	private String status;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAnalyseTone() {
		return analyseTone;
	}

	public void setAnalyseTone(String analyseTone) {
		this.analyseTone = analyseTone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

}
