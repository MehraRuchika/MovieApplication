package com.movie.application.app.service.impl.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.movie.application.app.dao.MovieDao;
import com.movie.application.app.model.Movie;
import com.movie.application.app.service.impl.MovieService;

public class MovieServiceTest {

	@InjectMocks
	private MovieService movieService;

	@Mock
	private Movie movie;

	@Mock
	MovieDao movieDao;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		movie = new Movie();
		movie.setCategory("category");
		movie.setRating(2);
		movie.setTitle("Title");
	}

	@After
	public void tearDown() throws Exception {
		movie = null;
	}
	
	/*
	 * Create Movie with Details.
	 * 
	 */
	@Test
	public void testCreateMovie() {
		when(movieDao.save(movie)).thenReturn(movie);
		String result = movieService.createMovie(movie);
		assertNotNull(result);
		assertEquals(result, "Movie is Saved...!!!");

	}

	/*
	 * Create Movie With Zero rating.
	 * 
	 */
	@Test
	public void testCreateMovieWithZeroRating() {
		movie.setRating(0);
		when(movieDao.save(movie)).thenReturn(movie);
		String result = movieService.createMovie(movie);
		assertNotNull(result);
		assertEquals(result, "Ratting Should be between 0.5 to 5");
	}
	
	/*
	 * Get Movie Details By Id.
	 * 
	 */
	@Test
	public void testGetMovieById() {
		try {
			 Optional<Movie> optional = Optional.of(movie);
			when(movieDao.findById(Long.valueOf(1))).thenReturn(optional);
			String movie = movieService.getMovieById(Long.valueOf(1));
			assertNotNull(movie);
			assertNotSame(movie, "Movie is not present with '" + Long.valueOf(1) + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Update Movie Details By Null.
	 * 
	 */
	@Test
	public void testGetMovieByNull() {
		try {
			when(movieDao.findById(Long.valueOf(1))).thenReturn(null, null);
			String movie = movieService.getMovieById(Long.valueOf(1));
			assertNotNull(movie);
			assertEquals(movie,  "Movie enttity is not exist with id number");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Delete Movie By Given Id.
	 * 
	 */
	@Test
	public void testDeleteMovie() {
		movieService.deleteMovie(Long.valueOf(1));
	}
	
	/*
	 * Get List Of Movies.
	 * 
	 */
	@Test
	public void testGetListOfMovies() {
		List<Movie> listOfMoives = movieService.getListOfMovies();
		assertNotNull(listOfMoives);
		assertEquals(listOfMoives, Collections.emptyList());
	}
	
	/*
	 * Update Movie and details.
	 * 
	 */
	@Test
	public void testUpdateMovie() {
		when(movieDao.save(movie)).thenReturn(movie);
		String result = movieService.updateMovie(movie);
		assertNotNull(result);
	}

	/*
	 * Update Movie With Zero rating.
	 * 
	 */
	@Test
	public void testUpdateMovieWithZeroRating() {
		movie.setRating(0);
		when(movieDao.save(movie)).thenReturn(movie);
		String result = movieService.updateMovie(movie);
		assertNotNull(result);
		assertEquals(result, "Ratting Should be between 0.5 to 5");

	}
}
