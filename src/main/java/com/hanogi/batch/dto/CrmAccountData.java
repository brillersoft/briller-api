package com.hanogi.batch.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "CRM_ACCOUNT_DATA")
public class CrmAccountData {

	@Column(name = "crm_data_id")
	private int crmDataId;

	@Id
	@Column(name = "account_id")
	private int accountId;

	@Column(name = "REVENUE")
	private String revenue;

	@Column(name = "MARGIN")
	private String margin;

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

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
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
