package edu.upenn.cit594;

import java.util.ArrayList;
import java.util.HashMap;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Properties;
import edu.upenn.cit594.datamanagement.CSVPropertiesReader;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.datamanagement.TXTPopulationReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.ui.userInterface;

public class Main {
	
	
	public static void main(String[] args) {
		if (args.length != 5) {
			System.out.println("invalid args");
			System.exit(0);
		}
		if (!(args[0].equals("csv") || args[0].equals("json"))) {
			System.out.println("invalid args");
			System.exit(0);
		}
		
		String parkingFileFormat = args[0];
		String parkingFile = args[1];
		String propertyFile = args[2];
		String populationFile = args[3];	
		String logFile = args[4];
		
		Logger l = Logger.getInstance(logFile);
		l.log(args);
		
		HashMap<Integer,Integer> population = new HashMap<>();
		ArrayList<Parking> parking = new ArrayList<>();
		ArrayList<Properties> properties = new ArrayList<>();
		
		//read population file
		PopulationReader populationReader = new TXTPopulationReader(populationFile);
		population = populationReader.read();
		
		//read parking file
		ParkingReader parkingReader = ParkingProcessor.parkingProcess(parkingFileFormat, parkingFile);
		parking = parkingReader.read();

		//read properties file
		PropertiesReader propertiesReader = new CSVPropertiesReader(propertyFile);
		properties= propertiesReader.read();
		
		userInterface ui = new userInterface(population, parking, properties);
		ui.askUserForStep();
		
	}
}
