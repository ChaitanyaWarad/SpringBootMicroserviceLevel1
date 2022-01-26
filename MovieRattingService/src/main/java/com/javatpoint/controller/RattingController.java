package com.javatpoint.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.model.Rating;
import com.javatpoint.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RattingController {

	@RequestMapping("/test")
	public String test() {
		return "Application is working fine !!!";
	}

	@RequestMapping("/{movieId}")
	public Rating getMovieRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getuserRatings(@PathVariable("userId") String movieId) {
		List<Rating> rattings = Arrays.asList(
				new Rating("1234", 4),
				new Rating("5678", 3), 
				new Rating("1234", 4));
		UserRating userRating=new UserRating();
		userRating.setUserRatting(rattings);
		return userRating;
	}
	

}
