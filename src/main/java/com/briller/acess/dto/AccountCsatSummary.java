package com.briller.acess.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_CSAT_SUMMARY")
public class AccountCsatSummary {

	@Id
	@Column(name = "account_id")
	private Integer accountId;

	@Column(name = "realtionships")
	private int relationships;

	@Column(name = "ESCALATIONS")
	private int escalations;

	@Column(name = "total_interactions")
	private int totalInteractions;

	@Column(name = "negative_interactions")
	private int negativeInteractions;

	@Column(name = "CSAT")
	private double csat;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getRelationships() {
		return relationships;
	}

	public void setRelationships(int relationships) {
		this.relationships = relationships;
	}

	public int getEscaltions() {
		return escalations;
	}

	public void setEscaltions(int escalations) {
		this.escalations = escalations;
	}

	public int getTotalInteractions() {
		return totalInteractions;
	}

	public void setTotalInteractions(int totalInteractions) {
		this.totalInteractions = totalInteractions;
	}

	public int getEscalations() {
		return escalations;
	}

	public void setEscalations(int escalations) {
		this.escalations = escalations;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public int getNegativeInteractions() {
		return negativeInteractions;
	}

	public void setNegativeInteractions(int negativeInteractions) {
		this.negativeInteractions = negativeInteractions;
	}

	public double getCsat() {
		return csat;
	}

	public void setCsat(double csat) {
		this.csat = csat;
	}

}
