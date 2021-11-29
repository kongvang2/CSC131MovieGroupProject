package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*")
@RequestMapping("/moviecollection")
@RestController
public class MovieCollectionController { //implements CommandLineRunner
	
	private MovieCollection movieCollection = new MovieCollection();
	
	@PostConstruct
	public void init() {
		movieCollection = initializeMovieCollection();
		
	}
	
	public MovieCollection initializeMovieCollection() {
		ObjectMapper mapper = new ObjectMapper();
		
		MovieCollection movieCollection = new MovieCollection();
		// ignore all null fields globally
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		try {
			
			Movie[] movieArr = mapper.readValue(new File("sample2.json"), Movie[].class);
			//System.out.println("Movie Array -> ");
			for(Movie aMovie : movieArr) {
				//System.out.println(aMovie);
				movieCollection.addNewMovie(aMovie);
			}
			
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return movieCollection;
		
	}
	
	@GetMapping(path = "/movies", produces = "application/json")
	public HashMap<String,Movie> getMovieCollection() {
		return movieCollection.getMovies();
	}
	
	/* Working / Don't modify
	@GetMapping(path = "/movies/search", produces = "application/json")
	public HashMap<String,Movie> search(
			@RequestParam(required = false, value="title", defaultValue="null") String titleSearch,
			@RequestParam(required = false, value="year", defaultValue="0") int year){
		MovieCollection searchResult = new MovieCollection();
		
		List<Movie> movieValue = new ArrayList<>(movieCollection.getMovies().values());
		
		Iterator<Movie> movie_itr = movieValue.iterator();
		
		while (movie_itr.hasNext()) {
			Movie aMovie = movie_itr.next();
			//System.out.print(aMovie.toString());
			//the following search conditions will need to better organized once test/check search work
			//maybe move them into SearchHelper.class
			if (!titleSearch.equals("null")) {
				if (aMovie.getTitle().toLowerCase().contains(titleSearch.toLowerCase())) {
					searchResult.addNewMovie(aMovie);
				}
			}
			
		}
		
		return searchResult.getMovies();
		
	}
	*/
	
	/* Testing */
	@GetMapping(path = "/movies/search", produces = "application/json")
	public HashMap<String,Movie> search(
			@RequestParam(required = false, value="title", defaultValue="null") String titleSearch,
			@RequestParam(required = false, value="year", defaultValue="-1") int year){
		MovieCollection searchResult = new MovieCollection();
		
		List<Movie> movieValue = new ArrayList<>(movieCollection.getMovies().values());
		
		Iterator<Movie> movie_itr = movieValue.iterator();
		
		while (movie_itr.hasNext()) {
			boolean doAddMovie = true; //will assume movie matches all search parameters
			Movie aMovie = movie_itr.next();
			//System.out.print(aMovie.toString());
			//the following search conditions will need to better organized once test/check search work
			//maybe move them into SearchHelper.class
			
			if (!titleSearch.equals("null")) {
				if ( !(aMovie.getTitle().toLowerCase().contains(titleSearch.toLowerCase())) ) {
					doAddMovie = false; //flag
				} else {/*do nothin */}
			}
			if (year != -1) {
				if ( !(aMovie.getYear() == year) ) {
					doAddMovie = false; //flag
				} else {/*Do Nothing*/}
			}

			if (doAddMovie) {
				searchResult.addNewMovie(aMovie);
			}
			
			//SearchHelper.getSearchParameters(titleSearch, year);
			
		}
		
		return searchResult.getMovies();
		
	}
	
}
