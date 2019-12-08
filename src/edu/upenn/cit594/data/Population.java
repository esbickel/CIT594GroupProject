package edu.upenn.cit594.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.upenn.cit594.logging.Logger;

public class Population {
	
	private static HashMap<String, Integer> populations;
	
	// Constructor
	private Population() {
		populations = new HashMap <String, Integer>();
	}
	
	//Instance
	private static Population instance = new Population();
	
	
	//Get methods
	public void add(String zip, String pop) {
		populations.put(zip, Integer.parseInt(pop));
	}
	
	public static Integer getPopulation(String Zip) {
		return populations.get(Zip);
	}
	
	public static Set<String> getZipCodes() {
		return(populations.keySet());
	}
	
	public static Population getInstance() {
		return instance;
	}
	
	public static HashMap<String, Integer> get(){
		return populations;
	}
	
	
	
	/*
	int population;
	int zipCode;
	
	public Population() {
		
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	*/
	
	
}
