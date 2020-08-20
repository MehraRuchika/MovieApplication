package com.movie.application.app.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.application.app.dao.MovieDao;
import com.movie.application.app.exception.MovieIdNotFoundException;
import com.movie.application.app.model.Movie;

@Service
public class MovieService {

	@Autowired
	private MovieDao movieDao;

	/*
	 * Create Movie and check rating should be between 0.5 to 5
	 * 
	 * @param movie
	 * 
	 * @return string
	 */
	public String createMovie(Movie movie) {
		if (movie.getRating() >= 0.5 && movie.getRating() <= 5) {
			movieDao.save(movie);
			return "Movie is Saved...!!!";
		} else {
			return "Ratting Should be between 0.5 to 5";
		}
	}

	/*
	 * Delete movie by given ID
	 * 
	 * @param movie id *
	 */
	public void deleteMovie(Long movieId) {
		movieDao.deleteById(movieId);
	}

	/*
	 * Get Movie details by movie id
	 * 
	 * @param movie id *
	 * 
	 * @return string
	 */
	public String getMovieById(Long movieId) {
		try {
			Optional<Movie> optional = movieDao.findById(movieId);
			if (Optional.ofNullable(optional).isPresent()) {
				return optional.get().toString();
			} else {
				throw new MovieIdNotFoundException();
			}
		} catch (MovieIdNotFoundException e) {
			e.printStackTrace();
			return "Movie enttity is not exist with id number";

		}

	}

	/*
	 * Get List of All Movies from database.
	 *
	 * @return List Of Movies
	 */
	public List<Movie> getListOfMovies() {
		List<Movie> listOfMoives = (List<Movie>) movieDao.findAll();
		return !listOfMoives.isEmpty() ? listOfMoives : Collections.emptyList();
	}

	/*
	 * Update Movie and other detail
	 * 
	 * @param movie 
	 * 
	 * @return string
	 */
	public String updateMovie(Movie movie) {
		if (movie.getRating() >= 0.5 && movie.getRating() <= 5) {
			movieDao.save(movie);
			return "Movie is Saved...!!!";
		} else {
			return "Ratting Should be between 0.5 to 5";
		}

	}

}
