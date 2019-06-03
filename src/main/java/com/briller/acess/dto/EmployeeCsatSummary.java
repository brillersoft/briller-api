package com.briller.acess.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.briller.acess.configs.audit.AuditFields;

@Entity
@Table(name = "employee_csat_summary")
public class EmployeeCsatSummary extends AuditFields<String>  {
	

	@Column(name = "NUM_OF_INTERACTIONS")
	private Integer num_of_interactions;
	
	@Column(name = "NUM_OF_EMAILS")
	private Integer num_of_emails;
	
	

	@Override
	public String toString() {
		return "EmployeeCsatSummary [num_of_interactions=" + num_of_interactions + ", num_of_emails=" + num_of_emails
				+ ", negative_interactions=" + negative_interactions + ", employeeId=" + employeeId + ", clientId="
				+ clientId + ", accountId=" + accountId + ", csat=" + csat + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((csat == null) ? 0 : csat.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((negative_interactions == null) ? 0 : negative_interactions.hashCode());
		result = prime * result + ((num_of_emails == null) ? 0 : num_of_emails.hashCode());
		result = prime * result + ((num_of_interactions == null) ? 0 : num_of_interactions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeCsatSummary other = (EmployeeCsatSummary) obj;
		if (accountId != other.accountId)
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (csat == null) {
			if (other.csat != null)
				return false;
		} else if (!csat.equals(other.csat))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (negative_interactions == null) {
			if (other.negative_interactions != null)
				return false;
		} else if (!negative_interactions.equals(other.negative_interactions))
			return false;
		if (num_of_emails == null) {
			if (other.num_of_emails != null)
				return false;
		} else if (!num_of_emails.equals(other.num_of_emails))
			return false;
		if (num_of_interactions == null) {
			if (other.num_of_interactions != null)
				return false;
		} else if (!num_of_interactions.equals(other.num_of_interactions))
			return false;
		return true;
	}

	@Column(name = "NEGATIVE_INTERACTIONS")
	private Integer negative_interactions;
	
	@Column(name = "EMPLOYEE_ID")
	private Integer employeeId;
	
	@Id
	@Column(name = "CLIENT_ID")
	private Integer clientId;

	@Column(name = "ACCOUNT_ID")
	private int accountId;

	@Column(name = "CSAT")
	private String csat;
	
	
	@Version
	private Integer versionNum;
	
	
	public Integer getNum_of_interactions() {
		return num_of_interactions;
	}

	public void setNum_of_interactions(Integer num_of_interactions) {
		this.num_of_interactions = num_of_interactions;
	}

	public Integer getNum_of_emails() {
		return num_of_emails;
	}

	public void setNum_of_emails(Integer num_of_emails) {
		this.num_of_emails = num_of_emails;
	}

	public Integer getNegative_interactions() {
		return negative_interactions;
	}

	public void setNegative_interactions(Integer negative_interactions) {
		this.negative_interactions = negative_interactions;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getCsat() {
		return csat;
	}

	public void setCsat(String csat) {
		this.csat = csat;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
}
