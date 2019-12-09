package edu.upenn.cit594.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import edu.upenn.cit594.processor.propertiesProcessor;
import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Properties;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.processor.Strategy;
import edu.upenn.cit594.processor.StrategyAveResidentialMV;
import edu.upenn.cit594.processor.StrategyAveResidentialTLA;
import edu.upenn.cit594.processor.populationProcessor;

public class userInterface {
	HashMap<Integer, Integer> population;
	ArrayList<Parking> parking;
	ArrayList<Properties> properties;

	public userInterface(HashMap<Integer, Integer> population2, ArrayList<Parking> parking2, ArrayList<Properties> properties2) {
		this.population=population2;
		this.parking=parking2;
		this.properties=properties2;
	}

	public void askUserForStep() {
		int step;
		int zipCode;
		int totalPopulation=0;
		
		//for memorization purpose:
		HashMap<Integer, Double> totalFinesPerCapita=new HashMap<>();
		HashMap<Integer, Double> aveResidentialMV = new HashMap<>() ;
		HashMap<Integer, Double> aveResidentialTLA = new HashMap<>() ;
		HashMap<Integer, Double> mktValuePerCapita = new HashMap<>() ;
		String outputForQuestion6=null;
		
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object

		while(true) {
			System.out.println("please specify the action to be performed (type 0~6)"+"\n");

			String userStep = myObj.nextLine();  // Read user input
			Logger l = Logger.getInstance(); l.log(userStep); //Logging

			if(userStep.matches("[0-9]+") && (Integer.parseInt(userStep))<=6&&Integer.parseInt(userStep)>=0)
			{
				step = Integer.parseInt(userStep);
				//start processing
				//processor will call askUserForZipCode
				if (step == 0) {
					System.exit(0);
				}
				else if (step == 1) {

					if(totalPopulation==0)  //for memorization. check to see if it has been been calculated already.
					{
						populationProcessor.totalPopulation(population);
					}
					else
						System.out.println(totalPopulation);
				}
				else if (step == 2) {
					//for memorization purpose: check if the calculation has been done for previous same user input
					//if(totalFinesPerCapita!=null)
					//{
					//	ArrayList<String> sortedTotalFinesPerCapita= new ArrayList<>();
					//	Set<Integer> set = totalFinesPerCapita.keySet();
					//	Object[] arr=set.toArray();
					//	Arrays.sort(arr);
					//	for(Object key:arr) {
					//		sortedTotalFinesPerCapita.add(key+" "+totalFinesPerCapita.get(key));
					//	}

					//	for(int i=0;i<sortedTotalFinesPerCapita.size();i++)
					//		System.out.println(sortedTotalFinesPerCapita.get(i));

					//}
					//calculate if the calculation has not been done before
					//else {
					if (totalFinesPerCapita.isEmpty()) {
						ParkingProcessor parkingProcessor = new ParkingProcessor();									
						totalFinesPerCapita = parkingProcessor.totalFinesPerCapita(parking, population,true);
					}
					else {
						for (int ZIP : totalFinesPerCapita.keySet()) {
							System.out.println(ZIP+" "+totalFinesPerCapita.get(ZIP));
						}
					}
					
					//}
				}
				else if (step == 3) {
					double aveRMV=0;
					zipCode=askUserForZipCode();
					if (zipCode == 0) {System.out.println(zipCode);break;}
					
					//for memorization purpose: check if the calculation has been done for previous same user input
					if(aveResidentialMV!=null&&aveResidentialMV.containsKey(zipCode))
					{
						aveRMV=aveResidentialMV.get(zipCode);
						System.out.println(aveResidentialMV.get(zipCode));
					}

					else
					{
						propertiesProcessor propertiesProcessor=new propertiesProcessor();
						aveRMV =propertiesProcessor.PropertyAverage(new StrategyAveResidentialMV(),properties,population,zipCode);
						aveResidentialMV.put(zipCode,aveRMV );
					}
				}
				else if (step == 4) {
					double aveRTLA=0;
					zipCode=askUserForZipCode();
					if (zipCode == 0) {System.out.println(zipCode);break;}
					if(aveResidentialTLA!=null&&aveResidentialTLA.containsKey(zipCode))
					{
						aveRTLA=aveResidentialTLA.get(zipCode);
						System.out.println(aveResidentialTLA.get(zipCode));
					}
					else {
					propertiesProcessor propertiesProcessor=new propertiesProcessor();
					aveRTLA=propertiesProcessor.PropertyAverage(new StrategyAveResidentialTLA(), properties,population,zipCode);
					aveResidentialTLA.put(zipCode,aveRTLA );
					}

				}
				else if (step == 5) {
					double mktValue=0;
					zipCode=askUserForZipCode();
					if (zipCode == 0) {System.out.println(zipCode);break;}
					if(mktValuePerCapita!=null&&mktValuePerCapita.containsKey(zipCode))
					{
						mktValue=mktValuePerCapita.get(zipCode);
						System.out.println(mktValuePerCapita.get(zipCode));
					}
					else {
					propertiesProcessor propertiesProcessor=new propertiesProcessor();
					mktValue=propertiesProcessor.MarketValuePerCapita( properties,population,zipCode,true);
					mktValuePerCapita.put(zipCode, mktValue);
					}
				}
				else if (step == 6) {
					//calculate the top 1 fine per capita with its area zip code and total residential market value per capita for that zipcode area.

					if(outputForQuestion6==null)
					{
					ParkingProcessor parkingProcessor=new ParkingProcessor();
					outputForQuestion6=parkingProcessor.Top1FinePerCapita(population, properties, parking);
					}
					else
						System.out.println(outputForQuestion6);

				}
			}
			askUserForStep();
		}
	}

	public int askUserForZipCode() {
		int zipCode;
		System.out.println("please specify the Zip Code");
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		
		String userZipCode = myObj.nextLine();  // Read user input
		Logger l = Logger.getInstance(); l.log(userZipCode); //Logging
		
		Set<Integer> ZipCodes = Population.getZipCodes();
		if (!ZipCodes.contains(Integer.parseInt(userZipCode))) {
			return 0;
		}
		
		if(userZipCode.matches("[0-9]+") && userZipCode.length()==5){
			zipCode=Integer.parseInt(userZipCode);
			return zipCode;
		}
		else {
			return 0;
		}
	}


	//public static void main(String[] args) {

	//	userInterface testInput = new userInterface();
	//	testInput.askUserForStep();
	//	testInput.askUserForZipCode();

	//	}

}
