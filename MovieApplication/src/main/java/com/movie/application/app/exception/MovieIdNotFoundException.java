package com.movie.application.app.exception;

public class MovieIdNotFoundException extends Exception  {
	
	private static final long serialVersionUID = -3226894879307522254L;

	public MovieIdNotFoundException(String message) {
		super(message);
	}
	
	public MovieIdNotFoundException() {
		super();
	}
	
}
