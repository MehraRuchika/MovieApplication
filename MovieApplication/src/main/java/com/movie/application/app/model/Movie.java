package com.movie.application.app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Table(name = "Movie")
@Getter @Setter @NoArgsConstructor @ToString
public class Movie implements Serializable {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_Id")
	@NonNull
	private Long movieId;

	@Column(name = "title")
	private String title;

	@Column(name = "category")
	private String category;

	@Column(name = "rating")
	private float rating;

}
