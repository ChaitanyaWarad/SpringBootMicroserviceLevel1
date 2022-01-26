package com.javatpoint.model;

import java.util.List;

public class UserRating {

	private List<Rating> userRatting;

	public UserRating() {
	}

	public UserRating(List<Rating> userRatting) {
		super();
		this.userRatting = userRatting;
	}

	public List<Rating> getUserRatting() {
		return userRatting;
	}

	public void setUserRatting(List<Rating> userRatting) {
		this.userRatting = userRatting;
	}

}
