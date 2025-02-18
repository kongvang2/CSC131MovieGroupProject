package com.example.demo;

import java.util.HashMap;


public class MovieCollection{
	private MovieCollection movie = null;
	private HashMap<String,Movie> movieMap = null;
	
	public MovieCollection() {
		movieMap = new HashMap<String,Movie>();
	}
	
	public void addNewMovie(String id, Movie movie) {
		movieMap.put(id, movie);
	}
	
	public HashMap<String,Movie> getMovies(){
		return movieMap;
	}
	
	public Movie getMovieById(String id) {
		return movieMap.get(id);
	}
	
	
}