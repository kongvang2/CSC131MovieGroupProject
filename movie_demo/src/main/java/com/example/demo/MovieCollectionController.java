package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


@CrossOrigin(origins = "*")
@RequestMapping("/moviecollection")
@RestController
public class MovieCollectionController { //implements CommandLineRunner
	
	private MovieCollection movieCollection = new MovieCollection();
	
	@PostConstruct
	public void init() throws Exception {
		CsvToJson.run(); //convert csv to json file
		movieCollection = initializeMovieCollection();
		
	}
	
	public MovieCollection initializeMovieCollection() {
		ObjectMapper mapper = new ObjectMapper();
		
		MovieCollection movieCollection = new MovieCollection();
		// ignore all null fields globally
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		int id = 1;
		try {
			
			Movie[] movieArr = mapper.readValue(new File("Kaggle_2020_the_oscar_award.json"), Movie[].class);
			
			for(Movie aMovie : movieArr) {
				//System.out.println(aMovie);
				aMovie.setId(String.valueOf(id));
				movieCollection.addNewMovie(aMovie.getId(),aMovie);
				id++;
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
			@RequestParam(required = false, value="title", defaultValue="null") String film,
			@RequestParam(required = false, value="year", defaultValue="null") String year){
		MovieCollection searchResult = new MovieCollection();
		
		List<Movie> movieValue = new ArrayList<>(movieCollection.getMovies().values());
		
		Iterator<Movie> movie_itr = movieValue.iterator();
		boolean doAddMovie = true;
		while (movie_itr.hasNext()) {
			doAddMovie = true; //will assume movie matches all search parameters
			Movie aMovie = movie_itr.next();
			//System.out.print(aMovie.toString());
			//the following search conditions will need to better organized once test/check search work
			//maybe move them into SearchHelper.class
			
			if (!film.equals("null")) {
				if ( !(aMovie.getFilm().toLowerCase().contains(film.toLowerCase())) ) {
					doAddMovie = false; //flag
				} //else {/*do nothing */} 
			}
			if (!year.equals("null")) {
				if ( !(aMovie.getYearCeremony().equals(year)) ) {
					doAddMovie = false; //flag
				} //else {/*Do Nothing*/}
			}

			if (doAddMovie) {
				searchResult.addNewMovie(aMovie.getId(),aMovie);
			}
			
			//SearchHelper.getSearchParameters(titleSearch, year);
			doAddMovie=true;
		}
		return searchResult.getMovies();
		
	}
	
}
