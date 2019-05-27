package com.briller.acess.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.briller.acess.configs.audit.AuditFields;

@Entity
@Table(name = "AGGREGATED_TONE")
public class AggregatedTone extends AuditFields<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AGGREGATED_TONE_ID")
	private Integer aggregatedToneId;

	@Column(name = "aggregated_tone", columnDefinition = "TEXT")
	private String aggregatedTone;

	private String status;

	@Version
	private Integer versionNum;

	public Integer getAggregatedToneId() {
		return aggregatedToneId;
	}

	public void setAggregatedToneId(Integer aggregatedToneId) {
		this.aggregatedToneId = aggregatedToneId;
	}

	public String getAggregatedTone() {
		return aggregatedTone;
	}

	public void setAggregatedTone(String aggregatedTone) {
		this.aggregatedTone = aggregatedTone;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aggregatedTone == null) ? 0 : aggregatedTone.hashCode());
		result = prime * result + ((aggregatedToneId == null) ? 0 : aggregatedToneId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "AggregatedTone [aggregatedToneId=" + aggregatedToneId + ", aggregatedTone=" + aggregatedTone
				+ ", status=" + status + ", versionNum=" + versionNum + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AggregatedTone other = (AggregatedTone) obj;
		if (aggregatedTone == null) {
			if (other.aggregatedTone != null)
				return false;
		} else if (!aggregatedTone.equals(other.aggregatedTone))
			return false;
		if (aggregatedToneId == null) {
			if (other.aggregatedToneId != null)
				return false;
		} else if (!aggregatedToneId.equals(other.aggregatedToneId))
			return false;
		return true;
	}

}
