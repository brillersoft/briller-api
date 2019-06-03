package com.briller.acess.response;

import java.util.Map;

import com.briller.acess.utility.ToneTypes;

public class Tones {
	private Map<ToneTypes, Double> tones;

	public Map<ToneTypes, Double> getTones() {
		return tones;
	}

	public void setTones(Map<ToneTypes, Double> tones) {
		this.tones = tones;
	}

}
