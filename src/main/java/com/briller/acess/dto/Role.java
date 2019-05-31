package com.briller.acess.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.briller.acess.configs.audit.AuditFields;
@Entity
@Table(name = "ROLE")
public class Role extends  AuditFields<String>  {
	
	@Id
	@Column(name = "ROLE_ID")
	private Integer roleId;
	
	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "ROLE_CODE")
	private String roleCode;
	
	@Column(name = "ROLE_DESC")
	private String roleDesc;
	
	@Column(name = "STATUS")
	private String status;
	
	@Version
	private Integer versionNum;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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
