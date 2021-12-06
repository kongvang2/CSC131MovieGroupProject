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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.OmdbApi.OmdbController;
import com.example.OmdbApi.OmdbWebServiceClient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


@CrossOrigin(origins = "*")
@RequestMapping("/moviecollection")
@RestController
public class MovieCollectionController { 
	
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
	
	@GetMapping(path = "/movies/{category}/{year}/winner")
	public Movie getCategoryYearWinner(@PathVariable String category, @PathVariable String year) {
		Movie returnMovie = new Movie();
		returnMovie.setFilm("Not FOUND!");
		
		List<Movie> movieValue = new ArrayList<>(movieCollection.getMovies().values());
		
		Iterator<Movie> movie_itr = movieValue.iterator();
		boolean found = false;
		while (movie_itr.hasNext()) {
			found = false; //will assume movie does not match all search parameters
			Movie aMovie = movie_itr.next();		
			
			if ( (aMovie.getYearCeremony().equals(year)) ) {
				if ( (aMovie.getCategory().toLowerCase().contains(category.toLowerCase())) ) {
					if ( aMovie.getWinner() ) {
						found = true;
					}
					
				}
			}
			
			if (found) {
				return aMovie;
			}

		}
		//at this point search criteria was not met
		return returnMovie;
		
	}
	
	
	
	@GetMapping(path = "/movies/search", produces = "application/json")
	public HashMap<String,Movie> search(
			@RequestParam(required = false, value="title", defaultValue="null") String film,
			@RequestParam(required = false, value="year", defaultValue="null") String year,
			@RequestParam(required = false, value="category", defaultValue="null") String category){
		MovieCollection searchResult = new MovieCollection();
		
		List<Movie> movieValue = new ArrayList<>(movieCollection.getMovies().values());
		
		Iterator<Movie> movie_itr = movieValue.iterator();
		boolean doAddMovie = true;
		while (movie_itr.hasNext()) {
			doAddMovie = true; //will assume movie matches all search parameters
			Movie aMovie = movie_itr.next();

			
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
			
			if (!category.equals("null")) {
				if ( !(aMovie.getCategory().equalsIgnoreCase(category)) ) {
					doAddMovie = false;
				}
			}
			

			if (doAddMovie) {
				searchResult.addNewMovie(aMovie.getId(),aMovie);
			}

			doAddMovie=true;
		}
		return searchResult.getMovies();
		
	}

	//Jason: Addition to project, returns a list of titles, even if partially searched. Need to filter to just show the title + poster link
	//NOTE: fe56eabc is the API key needed to use OMDB, do not change
	@RequestMapping("/movies/{title}")
    public @ResponseBody String getResponse(@PathVariable(value="title") String titles) {
        String response = OmdbWebServiceClient.searchMovieByTitle(titles, "fe57eabc");
        OmdbController movieList = new OmdbController(response);
        return movieList.getJsonResponse();
    }
	
}
