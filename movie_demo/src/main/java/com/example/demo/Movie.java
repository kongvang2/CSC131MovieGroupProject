package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties({"id", "director"}) 
class Movie{
	
	private String id;
	@JsonProperty("Title")
	private String title;
	@JsonProperty("Director")
	private String director;
	@JsonProperty("Year")
	private int year;
	
	public Movie(String id, String title, String director, int year){
		this.id=id;
		this.title=title;
		this.director=director;
		this.year=year;
	}
	
	//default constructor
	public Movie(){
		this("null", "null", "null", -1);
	}
	
	/******* setters *******/
	public void setId(String id){ this.id=id; }
	
	public void setTitle(String title){
		this.title=title;
	}
	
	public void setDirector(String director){
		this.director=director;
	}
	
	public void setYear(int year){
		this.year=year;
	}
	
	/****** getters *****/
	public String getId() { return this.id; }
	
	public String getTitle(){
		return this.title;
	}
	
	public String getDirector(){
		return this.director;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public String toString(){
		return this.id + ". " + this.title + ", " + this.director + ", " + this.year;
	}
}