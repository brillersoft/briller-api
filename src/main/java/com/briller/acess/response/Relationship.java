package com.briller.acess.response;

public class Relationship {

	private String name;
	private String imageUrl;

	private int numEscalations;

	private int numConversations;

	private double score;

	private String initials;

	private RelationalTone toneMapping;

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
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

	public int getNumEscalations() {
		return numEscalations;
	}

	public void setNumEscalations(int numEscalations) {
		this.numEscalations = numEscalations;
	}

	public int getNumConversations() {
		return numConversations;
	}

	public void setNumConversations(int numConversations) {
		this.numConversations = numConversations;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public RelationalTone getToneMapping() {
		return toneMapping;
	}

	public void setToneMapping(RelationalTone toneMapping) {
		this.toneMapping = toneMapping;
	}

}
