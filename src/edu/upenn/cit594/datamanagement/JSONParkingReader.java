package edu.upenn.cit594.datamanagement;
import edu.upenn.cit594.data.Parking;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JSONParkingReader implements ParkingReader{
	String fileName;
	Parking p = new Parking(); // each row of the Parking JSON File
	ArrayList<Parking> parking = new ArrayList<>();

	public JSONParkingReader(String myFile) {
		fileName = myFile;
	}

	public ArrayList<Parking> read(){
		int n=0;
		//create a parser
		JSONParser parser = new JSONParser();

		// open the file and get the array of JSON objects

		JSONArray parkings = new JSONArray();
		try {
			parkings = (JSONArray)parser.parse(new FileReader(fileName));
			Logger l = Logger.getInstance(); l.log(fileName); //Logging
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		// use an iterator to iterate over each element of the array 
		Iterator iter = parkings.iterator();

		// iterate while there are more objects in array
		while(iter.hasNext()) {
			p = new Parking();
			// get the next JSON object
			JSONObject JSONParking = (JSONObject) iter.next();

			// use the "get" method to print the value associated with that key
			
			String fineString = JSONParking.get("fine").toString();
			String zipCodeString = JSONParking.get("zip_code").toString();
			
			//JSONParking.get("zip_code").
			if(fineString.length()<1)
				continue;
			if(zipCodeString.length()<1)
				continue;
			
			double fine = Double.parseDouble(fineString);
			int zipCode=Integer.parseInt(zipCodeString);
			//int zipCode= Integer.parseInt(JSONParking.get("zip_code"));


			System.out.print(JSONParking.get("zip_code")+"\n");
			//System.out.println(zipCode);
			//System.out.println(tweet.get("location"));
			
			//p.setFine(fine);		
			//p.setZipCode(zipCode);		
			
			n++;
			parking.add(p);

		}
		System.out.println("n= "+n);
		return parking;

	}



public static void main(String[] args) {

	JSONParkingReader test = new JSONParkingReader("parking.json");
	test.read();

}

}
