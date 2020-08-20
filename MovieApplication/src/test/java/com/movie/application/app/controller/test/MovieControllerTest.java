package com.movie.application.app.controller.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.movie.application.app.controller.MovieController;
import com.movie.application.app.dao.MovieDao;
import com.movie.application.app.exception.MovieNotFoundException;
import com.movie.application.app.model.Movie;
import com.movie.application.app.service.impl.MovieService;

public class MovieControllerTest {

	@InjectMocks
	private MovieController movieController;

	@Mock
	private MovieService movieService;

	@Mock
	private Movie movie;

	@Mock
	MovieDao movieDao;

	@Before
	public void setUpBeforeClass() throws Exception {
		MockitoAnnotations.initMocks(this);
		movie = Mockito.mock(Movie.class);
		movie = new Movie();
		movie.setCategory("category");
		movie.setRating(2);
		movie.setTitle("Title");
	}

	@After
	public void tearDownAfterClass() throws Exception {
		movie = null;
	}

	/*
	 * Get LIst of Movies.
	 * 
	 */
	@Test
	public void testGetListOfMovies() {
		List<Movie> listOfMovie = new ArrayList<Movie>();
		listOfMovie.add(movie);
		when(movieService.getListOfMovies()).thenReturn(listOfMovie);
		listOfMovie = movieController.getListOfMovies();
		assertNotNull(listOfMovie);
		assertNotSame(listOfMovie, Collections.emptyList());
	}

	/*
	 * Create Movie in DataBase.
	 * 
	 */
	@Test
	public void testCreateMovie() {
		when(movieService.createMovie(movie)).thenReturn("Movie is Saved...!!!");
		String result = movieController.createMovie(movie);
		assertNotNull(result);
		assertEquals(result, "Movie is Saved...!!!");
	}

	/*
	 * Create Movie With Null object.
	 * 
	 */
	@Test
	public void testCreateMovieWithNull() {
		when(movieService.createMovie(null)).thenReturn("Movie not saved...!!!");
		movieController.createMovie(null);
		assertNotNull(movie);
	}

	/*
	 * Update Movie details.
	 * 
	 */
	@Test
	public void testUpdateMovie() {
		when(movieService.updateMovie(movie)).thenReturn("Movie Not Updated...!!!");
		String result = movieController.updateMovie(movie);
		assertNotNull(movie);
		assertEquals(result, "Movie Not Updated...!!!");
	}

	/*
	 * Update Movie With Null Object.
	 * 
	 */
	@Test
	public void testUpdateMovieWithNull() {
		when(movieService.updateMovie(null)).thenReturn("Movie Not Updated...!!!");
		movieController.updateMovie(null);
		assertNotNull(movie);
	}
	/*
	 * Delete Movie By given Id.
	 * 
	 */
	@Test
	public void testDeleteMovieWithId() {
		try {
			Method method;
			method = MovieController.class.getDeclaredMethod("deleteMovieById", String.class);
			method.setAccessible(true);
			method.invoke(movieController, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Delete Movie by null.
	 * 
	 */

	@Test
	public void testDeleteMovieWithNullId() {
		Method method;
		try {
			method = MovieController.class.getDeclaredMethod("deleteMovieById", String.class);
			method.setAccessible(true);
			method.invoke(movieController, "null");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Get Movie Details by Id.
	 * 
	 */
	@Test
	public void testGetMovieById() {
		try {
			when(movieService.getMovieById(Long.valueOf(1))).thenReturn("Movie is Saved...!!!");
			String movie = movieController.getMovieById("1");
			assertNotNull(movie);
			assertNotSame(movie, "Movie is not present with '" + Long.valueOf(1) + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Get Movie Details By Null.
	 * 
	 */
	@Test
	public void testGetMovieByNull() {
		when(movieService.getMovieById(null)).thenReturn("Movie enttity is not exist with id number");
		String movie = movieController.getMovieById(null);
		assertNotNull(movie);
	}

}
