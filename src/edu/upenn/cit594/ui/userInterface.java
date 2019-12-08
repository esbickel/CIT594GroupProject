package edu.upenn.cit594.ui;

import java.util.Scanner;
import edu.upenn.cit594.processor.propertiesProcessor;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.processor.Strategy;
import edu.upenn.cit594.processor.StrategyAveResidentialMV;
import edu.upenn.cit594.processor.StrategyAveResidentialTLA;
import edu.upenn.cit594.processor.populationProcessor;

public class userInterface {
	int step;
	int zipCode;

	public userInterface() {

	}

	public void askUserForStep() {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object

		while(true) {
			System.out.println("please specify the action to be performed (type 0~6)"+"\n");

			String userStep = myObj.nextLine();  // Read user input
			
			//Logging
			Logger l = Logger.getInstance();
			l.log(userStep);
			
			if(userStep.matches("[0-9]+") && (Integer.parseInt(userStep))<=6&&Integer.parseInt(userStep)>=0)
			{
				step = Integer.parseInt(userStep);
				//start processing
				//processor will call askUserForZipCode
				if (step == 0) {
					System.exit(0);
				}
				else if (step == 1) {
					populationProcessor.totalPopulation();
				}
				else if (step == 2) {
					ParkingProcessor.parkingProcess("CSV", "parking.csv");
				}
				else if (step == 3) {
					propertiesProcessor.PropertyAverage(new StrategyAveResidentialMV(), "properties.csv");
				}
				else if (step == 4) {
					propertiesProcessor.PropertyAverage(new StrategyAveResidentialTLA(), "properties.csv");
				}
				else if (step == 5) {
					propertiesProcessor.MarketValuePerCapita();
				}
				else if (step == 6) {
					
				}
			}
			askUserForStep();
		}
	}
	
	public void askUserForZipCode() {
		System.out.println("please specify the Zip Code");
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object

		String userZipCode = myObj.nextLine();  // Read user input
		while(true) {
		if(userZipCode.matches("[0-9]+") && userZipCode.length()==5)
		{
			zipCode=Integer.parseInt(userZipCode);
		}
		askUserForZipCode();
		}
	}
	
	
	public static void main(String[] args) {

		userInterface testInput = new userInterface();
		testInput.askUserForStep();
		testInput.askUserForZipCode();

		}

}
