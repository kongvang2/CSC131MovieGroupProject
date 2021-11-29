package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
 
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.Movie;
import com.fasterxml.jackson.annotation.JsonInclude;


@SpringBootApplication
public class MovieDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieDemoApplication.class, args);
	}
	/*
	 * 
	 * Testing
	@Override
	public void run(String[] args) throws IOException{
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		// ignore all null fields globally
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		try {
		    //Movie aMovie = mapper.readValue(new File("sample2.json"), Movie.class);
		    //System.out.println(aMovie);
			MovieCollection movieCollection = new MovieCollection();
			List<Movie> movieList = mapper.readValue(new File("sample2.json"), new TypeReference<List<Movie>>() {});
			System.out.println("Movie List -> " + movieList);
			
			Movie[] movieArr = mapper.readValue(new File("sample2.json"), Movie[].class);
			System.out.println("Movie Array -> ");
			for(Movie aMovie : movieArr) {
				System.out.println(aMovie);
				movieCollection.addNewMovie(aMovie);
			}
			
			System.out.println(movieCollection.getMovies());

		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
	}*/
}
