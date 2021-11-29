package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"director", "yearCeremony", "yearFilm"}) 
class Movie{
	
	private String id;
	@JsonProperty("year_film")
	private String year_film;
	@JsonProperty("year_ceremony")
	private String year_ceremony;
	@JsonProperty("ceremony")
	private String ceremony;
	@JsonProperty("category")
	private String category;
	@JsonProperty("name")
	private String name;
	@JsonProperty("film")
	private String film;
	@JsonProperty("winner")
	private boolean winner;
	
	public Movie(String id, String year_film, String year_ceremony, String ceremony,
			String category, String name, String film, boolean winner){
		this.id=id;
		this.year_film=year_film;
		this.year_ceremony=year_ceremony;
		this.ceremony=ceremony;
		this.category=category;
		this.name=name;
		this.film=film;
		this.winner=winner;
	}
	
	//default constructor
	public Movie(){
		this("null", "null", "null", "null", "null", "null", "null", false);
	}
	
	/******* setters *******/
	public void setId(String id){ this.id=id; }
	
	public void setYearFilm(String year_film){
		this.year_film=year_film;
	}
	public void setYearCeremony(String year_ceremony){
		this.year_ceremony=year_ceremony;
	}
	public void setCeremony(String ceremony){
		this.ceremony=ceremony;
	}
	public void setCategory(String category) {this.category=category;}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setFilm(String film){
		this.film=film;
	}
	
	public void setWinner(boolean winner){
		this.winner=winner;
	}
	
	
	
	/****** getters *****/
	public String getId() { return this.id; }
	
	public String getYearFilm(){
		return this.year_film;
	}
	
	public String getYearCeremony(){
		return this.year_ceremony;
	}
	
	public String getCeremony(){
		return this.ceremony;
	}
	
	public String getCategory() {return this.category;}
	
	public String getName(){
		return this.name;
	}
	public String getFilm(){
		return this.film;
	}
	
	public boolean getWinner(){
		return this.winner;
	}
	
	
	
	public String toString(){
		return this.id + ". " + this.year_film + ", " + this.year_ceremony + ", " + this.ceremony 
				+ ", "+ this.category + ", " + this.name + ", " + this.film + ", " + this.winner;
	}
}