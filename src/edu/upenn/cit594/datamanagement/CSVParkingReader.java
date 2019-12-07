package edu.upenn.cit594.datamanagement;


import java.io.*;
import java.util.*;

import edu.upenn.cit594.data.Parking;

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
			while ((line = br.readLine()) != null) {
				Parking p= new Parking();
				double fine=0;
				int zipCode=0;
				
				//Use comma as a seperatpr
				String[] violations = line.split(csvSplitBy);
				
				fine=Double.parseDouble(violations[1]); //convert string fine to numeric fine
				p.setFine(fine);
				System.out.println("The FINE of the violation is " + violations[1]);
				
				if(violations.length<7) //check if the row has 7 columns in case zip code is missing
					continue;
				zipCode=Integer.parseInt(violations[6]);
				p.setZipCode(zipCode);
				System.out.println("The ZIPCODE of the violation is " + violations[6]);

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




