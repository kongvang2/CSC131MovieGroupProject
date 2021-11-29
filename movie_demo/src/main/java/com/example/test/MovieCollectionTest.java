package com.example.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
 
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;

public class MovieCollectionTest {
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException{
		//create a JSONParser object
//		JSONParser jsonParser = new JSONParser();
		

		/*
		try {
			//Parse the contents of the JSON file
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("sample.json"));
			String id = 
		}
		*/
		
		ObjectMapper mapper = new ObjectMapper();
		
		// ignore all null fields globally
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		try {
		    //Movie aMovie = mapper.readValue(new File("sample2.json"), Movie.class);
		    //System.out.println(aMovie);
			
			List<Movie> movieList = mapper.readValue(new File("sample2.json"), new TypeReference<List<Movie>>() {});
			System.out.println("Movie List -> " + movieList);
			
			Movie[] movieArr = mapper.readValue(new File("sample2.json"), Movie[].class);
			System.out.println("Movie Array -> ");
			for(Movie aMovie : movieArr) {
				System.out.println(aMovie);
			}

		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}


