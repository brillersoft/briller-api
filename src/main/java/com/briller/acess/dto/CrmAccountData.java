package com.briller.acess.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "CRM_ACCOUNT_DATA")
public class CrmAccountData {

	@Id
	@Column(name = "crm_data_id")
	private int crmDataId;

	
	@Column(name = "account_id")
	private int accountId;

	@Column(name = "REVENUE")
	private double revenue;

	@Column(name = "MARGIN")
	private int margin;

	@Column(name = "STATUS")
	private String status;

	@Version
	private Integer versionNum;

	public int getCrmDataId() {
		return crmDataId;
	}

	public void setCrmDataId(int crmDataId) {
		this.crmDataId = crmDataId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
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
