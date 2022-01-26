package com.javatpoint.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@RequestMapping("/test")
	public String test() {
		return "Application is working fine !!!";
	}

	@RequestMapping("/{movieid}")
	public Movie getMovieInfo(@PathVariable("movieid") String movieid) {
		return new Movie("1", "robert", "its is a good movie");
	}

}
