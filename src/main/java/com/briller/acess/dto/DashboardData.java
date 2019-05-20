package com.briller.acess.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DashboardData {

	public DashboardData() {
		// TODO Auto-generated constructor stub
	}

	private String account_name;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account_id == null) ? 0 : account_id.hashCode());
		result = prime * result + ((account_name == null) ? 0 : account_name.hashCode());
		result = prime * result + ((csat == null) ? 0 : csat.hashCode());
		result = prime * result + ((escalations == null) ? 0 : escalations.hashCode());
		result = prime * result + ((margin == null) ? 0 : margin.hashCode());
		result = prime * result + ((negative_interactions == null) ? 0 : negative_interactions.hashCode());
		result = prime * result + ((realtionships == null) ? 0 : realtionships.hashCode());
		result = prime * result + ((revenue == null) ? 0 : revenue.hashCode());
		result = prime * result + ((total_interactions == null) ? 0 : total_interactions.hashCode());
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
		DashboardData other = (DashboardData) obj;
		if (account_id == null) {
			if (other.account_id != null)
				return false;
		} else if (!account_id.equals(other.account_id))
			return false;
		if (account_name == null) {
			if (other.account_name != null)
				return false;
		} else if (!account_name.equals(other.account_name))
			return false;
		if (csat == null) {
			if (other.csat != null)
				return false;
		} else if (!csat.equals(other.csat))
			return false;
		if (escalations == null) {
			if (other.escalations != null)
				return false;
		} else if (!escalations.equals(other.escalations))
			return false;
		if (margin == null) {
			if (other.margin != null)
				return false;
		} else if (!margin.equals(other.margin))
			return false;
		if (negative_interactions == null) {
			if (other.negative_interactions != null)
				return false;
		} else if (!negative_interactions.equals(other.negative_interactions))
			return false;
		if (realtionships == null) {
			if (other.realtionships != null)
				return false;
		} else if (!realtionships.equals(other.realtionships))
			return false;
		if (revenue == null) {
			if (other.revenue != null)
				return false;
		} else if (!revenue.equals(other.revenue))
			return false;
		if (total_interactions == null) {
			if (other.total_interactions != null)
				return false;
		} else if (!total_interactions.equals(other.total_interactions))
			return false;
		return true;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getRealtionships() {
		return realtionships;
	}

	public void setRealtionships(String realtionships) {
		this.realtionships = realtionships;
	}

	public String getEscalations() {
		return escalations;
	}

	public void setEscalations(String escalations) {
		this.escalations = escalations;
	}

	public String getTotal_interactions() {
		return total_interactions;
	}

	public void setTotal_interactions(String total_interactions) {
		this.total_interactions = total_interactions;
	}

	public String getNegative_interactions() {
		return negative_interactions;
	}

	public void setNegative_interactions(String negative_interactions) {
		this.negative_interactions = negative_interactions;
	}

	public String getCsat() {
		return csat;
	}

	public void setCsat(String csat) {
		this.csat = csat;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public DashboardData(String account_name, String account_id, String realtionships, String escalations,
			String total_interactions, String negative_interactions, String csat, String margin, String revenue) {
		super();
		this.account_name = account_name;
		this.account_id = account_id;
		this.realtionships = realtionships;
		this.escalations = escalations;
		this.total_interactions = total_interactions;
		this.negative_interactions = negative_interactions;
		this.csat = csat;
		this.margin = margin;
		this.revenue = revenue;
	}

	@Override
	public String toString() {
		return "DashboardData [account_name=" + account_name + ", account_id=" + account_id + ", realtionships="
				+ realtionships + ", escalations=" + escalations + ", total_interactions=" + total_interactions
				+ ", negative_interactions=" + negative_interactions + ", csat=" + csat + ", margin=" + margin
				+ ", revenue=" + revenue + "]";
	}

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	@Id
	private String account_id;
	private String realtionships;
	private String escalations;
	private String total_interactions;
	private String negative_interactions;
	private String csat;
	private String margin;
	private String revenue;

}
