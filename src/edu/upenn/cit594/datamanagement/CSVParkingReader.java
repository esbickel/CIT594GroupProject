package edu.upenn.cit594.datamanagement;


import java.io.*;
import java.util.*;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.logging.Logger;

public class CSVParkingReader implements ParkingReader {
	String fileName;
	Parking p = new Parking(); // each row of the Parking JSON File
	ArrayList<Parking> parking = new ArrayList<>();

	public CSVParkingReader(String myFile) {
		fileName = myFile;
	}

	public ArrayList<Parking> read() {

		ArrayList<Parking> parking = new ArrayList<>();
		//ArrayList<ArrayList<String>> parkingViolations = new ArrayList<ArrayList<String>>();
		String csvFile;
		//csvFile = "parking.csv";
		csvFile=fileName;
		BufferedReader br = null;

		String line = "";
		String csvSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			Logger l = Logger.getInstance(); l.log(csvFile); //Logging
			while ((line = br.readLine()) != null) {
				Parking p= new Parking();
				double fine=0;
				int zipCode=0;
				String state;
				
				//Use comma as a seperatpr
				String[] violations = line.split(csvSplitBy,-1);
				if(violations[1].isEmpty()||!violations[1].matches("[0-9]+"))
					continue;        //ignore the whole row when: 1) fine is missing. 2) fine is not numeric 
				fine=Double.parseDouble(violations[1]); //convert string fine to numeric fine
				p.setFine(fine);
				//System.out.println("The FINE of the violation is " + violations[1]);
				

				if(violations[4].isEmpty()) //ignore the whole row when state is missing
					continue;
				state=violations[4];
				p.setState(state);
				//System.out.println("The STATE of the violation is " + violations[4]);
				
				if(violations[6].isEmpty()||!violations[6].matches("[0-9]+")) 
					continue;        //ignore the whole row when: 1) zip is missing. 2) zip is not numeric 
				zipCode=Integer.parseInt(violations[6]);
				p.setZipCode(zipCode);
				//System.out.println("The ZIPCODE of the violation is " + violations[6]);

				parking.add(p);

				}
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return (parking);


	}

	public static void main(String[] args) {

		CSVParkingReader test = new CSVParkingReader("parking.csv");
		test.read();
	
			//System.out.println(test2.get(3)); //Printing out all the dates


		}


	
}




