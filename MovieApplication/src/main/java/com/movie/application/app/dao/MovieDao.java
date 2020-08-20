package com.movie.application.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.movie.application.app.model.Movie;
/*
 * Interface of Movie Dao Extends CrudRepository.
 * CrudRepository it's spring data interface,
 * Provide several methods for interacting with database
 */
public interface MovieDao extends CrudRepository<Movie, Long> {

}
