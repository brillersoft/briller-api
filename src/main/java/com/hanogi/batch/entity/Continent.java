package com.hanogi.batch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.hanogi.batch.audits.AuditFields;

@Entity
@Table(name = "Continent")
public class Continent extends AuditFields<String> {
	
	@Id
	@Column(name = "CONTINENT_ID")
	private int continentId;
	
	@Column(name = "CONTINENT_NAME")
	private String continentName;

	@Column(name = "CONTINENT_CODE")
	private String continentCode;

	@Column(name = "STATUS")
	private String status;

	@Version
	private Integer versionNum;

	public int getContinentId() {
		return continentId;
	}

	public void setContinentId(int continentId) {
		this.continentId = continentId;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	public String getContinentCode() {
		return continentCode;
	}

	public void setContinentCode(String continentCode) {
		this.continentCode = continentCode;
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
