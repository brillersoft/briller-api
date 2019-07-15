package com.hanogi.batch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.hanogi.batch.audits.AuditFields;

@Entity
@Table(name = "LEGAL_ENTITY")
public class OrganizationDetails extends AuditFields<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LEGAL_ENTITY_ID")
	private int legalEntityId;
	
	@Column(name = "ADDRESS_ID")
	private int addressId;
	
	@Column(name = "TYPE_ID")
	private int typeId;

	@Column(name = "ENTITY_NAME")
	private String entityName;

	@Column(name = "ENTITY_CODE")
	private String entityCode; 
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Version
	private Integer versionNum;
	
	@Column(name = "STATUS")
	private String status;
	
	public int getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(int legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	
	
	

}
