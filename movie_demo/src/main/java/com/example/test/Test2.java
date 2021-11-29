package com.example.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Test2 {
	
	/*public static void main(String[] args) {
		JSONParser jsonP = new JSONParser();
		
		try(FileReader reader = new FileReader("sample2.json")){
			//Read JSON file
			Object obj = jsonP.parse(reader);
			JSONArray movieList = (JSONArrat) obj;
			System.out.println(movieList);
			//iterate over movieList array
			movieList.forEach(movie -> parseMovieObject((JSONObject)movie));
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e){
			e.printStackTrace();
		}
	}
	
	private static void parseMovieObject(JSONObject movie) {
		JSONObject movieObject = (JSONObject) movie.get();
	}*/
}
