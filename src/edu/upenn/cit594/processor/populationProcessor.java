package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.TXTPopulationReader;

public class populationProcessor {
	
	// Display total population for all ZIP codes
	
	public static int totalPopulation () {
		
		int totalPop = 0;
		
		PopulationReader pr = new TXTPopulationReader("population.txt");
		pr.read();
				
		Set<String> ZipCodes = Population.getZipCodes();	
		if (ZipCodes.isEmpty()) {return 0;}

		
		for (String zip : ZipCodes) {
			totalPop += Population.getPopulation(zip);
		}
		
		return totalPop;
	}
	
	public static void main(String[] args) {
		
		int totalPop = totalPopulation();
		
		System.out.println(totalPop);
	}
}
