package com.hanogi.batch.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.hanogi.batch.audits.AuditFields;

@Entity
@Table(name = "business_unit")
public class BusinessUnit extends AuditFields<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_unit_id")
	private Integer businessUnitId;

	@Column(name = "business_unit_name")
	private String businessUnitName;

	@Column(name = "business_unit_code")
	private String businessUnitCode;

	@Column(name = "business_unit_desc")
	private String businessUnitDesc;

	@Version
	private Integer versionNum;

	public Integer getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(Integer businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getBusinessUnitCode() {
		return businessUnitCode;
	}

	public void setBusinessUnitCode(String businessUnitCode) {
		this.businessUnitCode = businessUnitCode;
	}

	public String getBusinessUnitDesc() {
		return businessUnitDesc;
	}

	public void setBusinessUnitDesc(String businessUnitDesc) {
		this.businessUnitDesc = businessUnitDesc;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

}
