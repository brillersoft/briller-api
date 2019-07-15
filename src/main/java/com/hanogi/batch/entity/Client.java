package com.hanogi.batch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.hanogi.batch.audits.AuditFields;

@Entity
@Table(name = "CLIENT")
public class Client extends AuditFields<String> {

	@Id
	@Column(name = "client_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "ACCOUNT_ID")
	private String accountId;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "hierarchy_level")
	private String hierarchyLevel;

	@Version
	private Integer versionNum;

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	public String getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(String hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}

}
