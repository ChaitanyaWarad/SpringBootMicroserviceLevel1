package com.javatpoint.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.javatpoint.model.CatalogItem;
import com.javatpoint.model.Movie;
import com.javatpoint.model.Rating;
import com.javatpoint.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/test")
	public String test() {
		return "Application is working fine !!!";
	}

	// using webclient
	@RequestMapping("user/{userid}")
	public List<CatalogItem> getCatalogWebClient(@PathVariable("userid") String userid) {

		List<Rating> rattings = Arrays.asList(new Rating("1234", 4), new Rating("5678", 3), new Rating("1234", 4));

		Movie movie = webClientBuilder.build().get().uri("http://localhost:9092/movies/movieid").retrieve()
				.bodyToMono(Movie.class).block();
		System.out.println(movie);

		List<CatalogItem> list = rattings.stream()
				.map(ratings -> new CatalogItem(movie.getName(), "sandalwood samuggling", ratings.getRating()))
				.collect(Collectors.toList());
		return list;

	}

	// using resttemplate
	@RequestMapping("/{restuserid}")
	public List<CatalogItem> getCatalogRest(@PathVariable("restuserid") String restuserid) {
		
		
		UserRating userRating = restTemplate.getForObject("http://localhost:9093/ratingsdata/users/" + restuserid,
				UserRating.class);
		System.out.println(userRating);

		return userRating.getUserRatting().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:9092/movies/" + rating.getMovieId(),
					Movie.class);
			return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
		}).collect(Collectors.toList());

	}

}