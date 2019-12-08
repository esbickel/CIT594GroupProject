package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.TXTPopulationReader;

public class populationProcessor {
	String populationFile;
	
	public populationProcessor() {
		
	}
	
	public populationProcessor(String populationFileName) {
		this.populationFile=populationFileName;
	}
	// Display total population for all ZIP codes
	
	public static void totalPopulation (HashMap<Integer,Integer> population) {
		
		int totalPop = 0;
		for (Map.Entry<Integer,Integer> entry : population.entrySet())  {
			totalPop=totalPop+entry.getValue();
		 }
		
		System.out.println(totalPop); 
	}
}