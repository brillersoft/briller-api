package com.briller.acess.response;

import java.util.List;

public class ResponseData {
	
	private String client;
	
	private String email;

	private String designation;
	
	private String name;
	
	private String imageUrl;
	
	private String initials;
	
	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	private List<Relationship> relList;
	
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Relationship> getRelationship() {
		return relList;
	}

	public void setRelationship(List<Relationship> relList) {
		this.relList = relList;
	}

}
