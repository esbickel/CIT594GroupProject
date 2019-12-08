package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Properties;
import edu.upenn.cit594.datamanagement.CSVPropertiesReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.datamanagement.TXTPopulationReader;
import edu.upenn.cit594.logging.Logger;

public class propertiesProcessor {
		
	public double PropertyAverage(Strategy strategy, ArrayList<Properties> properties,HashMap<Integer,Integer> population,int zipCode) {
		
		//PopulationReader popR = new TXTPopulationReader("population.txt");
		//popR.read();
				
		//Set<Integer> ZipCodes = Population.getZipCodes();
		Set<Integer> ZipCodes = population.keySet();
		
		//Scanner kbIn = new Scanner(System.in);
		//Population pop = Population.getInstance();
		//String zip = "?";
		
		//while (!ZipCodes.contains(zip)) {
		//	System.out.println("Enter a valid ZIP code:  ");
		//	zip = kbIn.next();
		//	Logger l = Logger.getInstance(); l.log(zip); //Logging
		//}
		//int intZIP = Integer.parseInt(zip);
		
		//PropertiesReader pr = new CSVPropertiesReader("properties.csv");
		//ArrayList<Properties> props = pr.read();
		//if(props == null) {return;}
		
		long attributeTotal = strategy.attributeSum(zipCode, properties);
		double attTotal = (double)attributeTotal;
		
		int propCount = 0;
		for (Properties prop : properties) {
			if (prop.getZipCode() == zipCode) {
				propCount++;
			}
		}
		
		if(propCount==0 ||attTotal==0)   //display 0 if the total residential mv for that zip code is 0;
			{
			return 0;	
			}
		else {
		double average = attTotal/propCount;	
		System.out.println((int)average);
		return (int)average;
		}
	}
	
	public double MarketValuePerCapita(ArrayList<Properties> properties, HashMap<Integer, Integer> population, int zipCode) {
		//PopulationReader popR = new TXTPopulationReader("population.txt");
		//popR.read();
				
		//Set<Integer> ZipCodes = Population.getZipCodes();
		
		//Scanner kbIn = new Scanner(System.in);
		//Population pop = Population.getInstance();
		//String zip = "?";
		
		//while (!ZipCodes.toString().contains(zip)) {
		//	System.out.println("Enter a valid ZIP code:  ");
		//	zip = kbIn.next();
		//	Logger l = Logger.getInstance(); l.log(zip); //Logging
		//}
		//int intZIP = Integer.parseInt(zip);
		
		//PropertiesReader pr = new CSVPropertiesReader("properties.csv");
		//ArrayList<Properties> props = pr.read();
		if(properties == null) {return 0;}
		double mvTotal = new StrategyAveResidentialMV().attributeSum(zipCode, properties);	
		double mvPerCapita = mvTotal/population.get(zipCode);
		System.out.println((int)mvPerCapita);
		return mvPerCapita; 
	}
}
