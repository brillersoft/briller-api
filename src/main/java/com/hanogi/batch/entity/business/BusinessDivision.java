package com.hanogi.batch.entity.business;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanogi.batch.audits.AuditFields;

@Entity
@Table(name = "division")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BusinessDivision extends AuditFields<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "division_id")
	private Integer divisionId;

	@Column(name = "division_name")
	private String divisionName;

	@Column(name = "division_code")
	private String divisionCode;

	@Column(name = "division_desc")
	private String divisionDesc;

	@Version
	private Integer versionNum;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "b_unit_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private BusinessUnit bUnit;

	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getDivisionDesc() {
		return divisionDesc;
	}

	public void setDivisionDesc(String divisionDesc) {
		this.divisionDesc = divisionDesc;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public BusinessUnit getbUnit() {
		return bUnit;
	}

	public void setbUnit(BusinessUnit bUnit) {
		this.bUnit = bUnit;
	}

}
