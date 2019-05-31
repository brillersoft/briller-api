package com.briller.acess.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.briller.acess.configs.audit.AuditFields;

@Entity
@Table(name = "EMPLOYEE_ROLE_MAPPING")
public class EmployeeRoleMapping extends AuditFields<String> {
	
	@Id
	@Column(name = "EMPLOYEE_ID")
	private Integer employeeId;
	
	//@Column(name = "ROLE_ID")
//	private Integer roleId;
	
	@OneToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;

	@Column(name = "STATUS")
	private String status;
	
	@Version
	@Column(name = "version_num")
	private Integer versionNum;
	
//	public Integer getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(Integer roleId) {
//		this.roleId = roleId;
//	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role= role;
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
