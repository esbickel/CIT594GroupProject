package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.TXTPopulationReader;

public class populationProcessor {
	
	// Display total population for all ZIP codes
	
	public static void totalPopulation () {
		
		int totalPop = 0;
		
		PopulationReader pr = new TXTPopulationReader("population.txt");
		pr.read();
				
		Set<Integer> ZipCodes = Population.getZipCodes();	
		if (ZipCodes.isEmpty()) {return;}

		
		for (Integer zip : ZipCodes) {
			totalPop += Population.getPopulation(zip);
		}
		
		System.out.println(totalPop); 
	}
}