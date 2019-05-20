package com.hanogi.batch.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@Column(name = "account_id")
	private int accountId;

	@Column(name = "account_name")
	private String AccountName;

	@Column(name = "account_code")
	private String accountCode;

	@Column(name = "account_desc")
	private String accountDesc;

	@Column(name = "STATUS")
	private String status;

	@Version
	private Integer versionNum;

	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "account_id") private AccountCsatSummary
	 * accountCsatSummary;
	 */
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "Account_Csat_Summary") private CrmAccountData
	 * crmAccountData;
	 */
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return AccountName;
	}

	/*
	 * public AccountCsatSummary getAccountCsatSummary() { return
	 * accountCsatSummary; }
	 * 
	 * public void setAccountCsatSummary(AccountCsatSummary accountCsatSummary) {
	 * this.accountCsatSummary = accountCsatSummary; }
	 */

	public void setAccountName(String accountName) {
		AccountName = accountName;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountDesc() {
		return accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
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
