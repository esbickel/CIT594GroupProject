package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Properties;
import edu.upenn.cit594.datamanagement.CSVPropertiesReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.datamanagement.TXTPopulationReader;

public class propertiesProcessor {
		
	public static void PropertyAverage(Strategy strategy, String fileName) {
		
		PopulationReader popR = new TXTPopulationReader("population.txt");
		popR.read();
				
		Set<String> ZipCodes = Population.getZipCodes();
		
		Scanner kbIn = new Scanner(System.in);
		Population pop = Population.getInstance();
		String zip = "?";
		
		while (!ZipCodes.contains(zip)) {
			System.out.println("Enter a valid ZIP code:  ");
			zip = kbIn.next();
			Logger l = Logger.getInstance(); l.log(zip); //Logging
		}
		int intZIP = Integer.parseInt(zip);
		
		PropertiesReader pr = new CSVPropertiesReader("properties.csv");
		ArrayList<Properties> props = pr.read();
		if(props == null) {return;}
		
		long attributeTotal = strategy.attributeSum(intZIP, props);
		double attTotal = (double)attributeTotal;
		
		int propCount = 0;
		for (Properties prop : props) {
			if (prop.getZipCode() == intZIP) {
				propCount++;
			}
		}
		
		double average = attTotal/propCount;
		
		System.out.println(average);
	}
	
	public static void MarketValuePerCapita() {
		PopulationReader popR = new TXTPopulationReader("population.txt");
		popR.read();
				
		Set<String> ZipCodes = Population.getZipCodes();
		
		Scanner kbIn = new Scanner(System.in);
		Population pop = Population.getInstance();
		String zip = "?";
		
		while (!ZipCodes.contains(zip)) {
			System.out.println("Enter a valid ZIP code:  ");
			zip = kbIn.next();
			Logger l = Logger.getInstance(); l.log(zip); //Logging
		}
		int intZIP = Integer.parseInt(zip);
		
		PropertiesReader pr = new CSVPropertiesReader("properties.csv");
		ArrayList<Properties> props = pr.read();
		if(props == null) {return;}
		
		long mvTotal = new StrategyAveResidentialMV().attributeSum(intZIP, props);
				
		System.out.println(mvTotal);
	}
}
