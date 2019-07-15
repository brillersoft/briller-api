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
@Table(name = "EMPLOYEE")
public class Employee extends AuditFields<String> {

@Id
@Column(name = "EMPLOYEE_ID")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer employeeId;

@Column(name = "FIRST_NAME", nullable = false)
private String firstName;

@Column(name = "LAST_NAME", nullable = false)
private String lastName;

@Column(name = "MIDDLE_NAME")
private String middleName;

@Column(name = "STATUS")
private String status;

@Version
private Integer versionNum;

public Integer getEmployeeId() {
	return employeeId;
}

public void setEmployeeId(Integer employeeId) {
	this.employeeId = employeeId;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getMiddleName() {
	return middleName;
}

public void setMiddleName(String middleName) {
	this.middleName = middleName;
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
