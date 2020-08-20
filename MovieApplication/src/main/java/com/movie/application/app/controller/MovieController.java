package com.movie.application.app.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.application.app.exception.MovieIdNotFoundException;
import com.movie.application.app.exception.MovieNotFoundException;
import com.movie.application.app.model.Movie;
import com.movie.application.app.service.impl.MovieService;

@RestController
@RequestMapping(value = "/api")
public class MovieController {

	@Autowired
	private MovieService movieService;

	/*
	 * PostMapping For Creating Movie
	 */
	@PostMapping(value = "/create")
	public String createMovie(@RequestBody Movie movie) {
		try {
			if (Optional.ofNullable(movie).isPresent()) {
				return movieService.createMovie(movie);
			} else {
				throw new MovieNotFoundException();
			}
		} catch (MovieNotFoundException e) {
			e.printStackTrace();
			return "Movie Object is null...!!!";
		}
	}

	/*
	 * Delete Mapping For Delete Movie
	 */
	@DeleteMapping(value = "/delete/{movieId}")
	public void deleteMovieById(@PathVariable String movieId) {
		try {
			if (Optional.ofNullable(movieId).isPresent() && StringUtils.isNumeric(movieId)
					&& !Long.valueOf(movieId).equals(0)) {
				movieService.deleteMovie(Long.valueOf(movieId));
			} else {
				throw new MovieIdNotFoundException();
			}
		} catch (MovieIdNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Put Mapping for Update Movie
	 */
	@PutMapping(value = "/update")
	public String updateMovie(@RequestBody Movie movie) {
		try {
			if (Optional.ofNullable(movie).isPresent()) {
				return movieService.updateMovie(movie);
			} else {
				throw new MovieNotFoundException();
			}
		} catch (MovieNotFoundException e) {
			e.printStackTrace();
			return "Movie Object is null...!!!";
		}
	}

	/*
	 * Get Mapping For getting list of movies.
	 */
	@GetMapping(value = "/get/movies")
	public List<Movie> getListOfMovies() {
		try {
		List<Movie> movie = movieService.getListOfMovies();
		if (Optional.ofNullable(movie).isPresent()) {
			return movie;
		} else {
			throw new MovieNotFoundException();
		}
		}catch (MovieNotFoundException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	/*
	 * Get movie by movie_Id.
	 */
	@GetMapping(value = "/get/movies/{movieId}")
	public String getMovieById(@PathVariable String movieId) {
		try {
			if (Optional.ofNullable(movieId).isPresent() && StringUtils.isNumeric(movieId)
					&& !Long.valueOf(movieId).equals(0)) {
				return movieService.getMovieById(Long.valueOf(movieId));
			} else {
				throw new MovieIdNotFoundException();
			}
		} catch (MovieIdNotFoundException e) {
			e.printStackTrace();
			return "Movie Id either null or invalid...!!";
		}
	}
}
