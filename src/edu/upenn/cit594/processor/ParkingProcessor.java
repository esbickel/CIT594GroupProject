package edu.upenn.cit594.processor;
import java.text.DecimalFormat;
import java.util.*;
import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.datamanagement.CSVParkingReader;
import edu.upenn.cit594.datamanagement.JSONParkingReader;
import edu.upenn.cit594.datamanagement.ParkingReader;

public class ParkingProcessor {

	protected ParkingReader reader;

	public static ParkingReader parkingProcess(String fileType, String fileName) {

		if(fileType.equals("json"))
			return new JSONParkingReader(fileName);

		if(fileType.equals("csv"))
			return new CSVParkingReader(fileName);

		return null;
	}

	public ArrayList<String> totalFinesPerCapita(ArrayList<Parking> parking, ArrayList<Population> population) {
		ArrayList<String> sortedOutput= new ArrayList<>();
		HashMap<Integer,Double> totalFines = new HashMap<>();
		HashMap<Integer,Double> totalFinesPerCapita = new HashMap<>();

		for(int i=0; i<parking.size();i++)
		{	
			Parking p=parking.get(i);  //for memorization puporse
			int zipCode =p.getZipCode();
			double fines =p.getFine();
			double currentTotalFines=0;
			
			if(!p.getState().equals("PA")) // ignore an parking entry when state is not "PA"
				continue;
			
			
			if(totalFines.containsKey(zipCode))
			{
				currentTotalFines =totalFines.get(zipCode);
				totalFines.put(zipCode,currentTotalFines+fines );
			}
			
			if(!totalFines.containsKey(zipCode))	//specific zip code shows first time in Parking.csv
				totalFines.put(zipCode, fines);

									
		}
		
		for(int i=0;i<population.size();i++) {
			Population p=population.get(i);
			int zipCode=p.getZipCode();
			int pop=p.getPopulation();
			
			if(pop==0)
				continue; // ignore an entry with population=0
			
			if(!totalFines.containsKey(zipCode)) 
				continue; //ignore non PA state, ignore entry when fine for that zipcode is missing

			DecimalFormat df = new DecimalFormat("#.####");//round down to 4 decimal points
			String roundedDown = df.format((totalFines.get(zipCode)/pop));
			//System.out.println("population="+ pop +"; zipCode="+zipCode + ";sumFine="+totalFines.get(zipCode));

			totalFinesPerCapita.put(zipCode, Double.parseDouble(roundedDown));
        } 
		
		Set<Integer> set = totalFinesPerCapita.keySet();
		Object[] arr=set.toArray();
		Arrays.sort(arr);
		for(Object key:arr) {
			sortedOutput.add(key+" "+totalFinesPerCapita.get(key));
		}
		for(int i=0;i<sortedOutput.size();i++)
			System.out.println(sortedOutput.get(i));
		return sortedOutput;
	}




}
