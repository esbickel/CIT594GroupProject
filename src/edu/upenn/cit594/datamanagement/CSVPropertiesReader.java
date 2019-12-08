package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Properties;
import edu.upenn.cit594.logging.Logger;

public class CSVPropertiesReader implements PropertiesReader {
	String fileName;
	Properties p = new Properties(); // each row of the Parking JSON File
	ArrayList<Properties> properties = new ArrayList<>();
	
	public CSVPropertiesReader(String myFile) {
		fileName = myFile;
	}
	
	public ArrayList<Properties> read() {
		ArrayList<Properties> properties = new ArrayList<>();
		BufferedReader br = null;
		
		String firstRow;
		String line = "";
		String csvSplitBy = ",";
		int a=0; // column number for "market_value"
		int b=0; // column number for "total_livable_area"
		int c=0; // column number for "zip_code"

		try {

			br = new BufferedReader(new FileReader(fileName));
			Logger l = Logger.getInstance(); l.log(fileName); //Logging
			
			//if(br.readLine()==null)
			//{
			//	System.out.print("the file is empty");
			//	return null;
			//}
				
			firstRow=br.readLine();
			String[] field = firstRow.split(csvSplitBy);
			int length = field.length;
			
			while(!field[a].equals("market_value")&& a<length) {
				a++;
			}
			
			while(!field[b].equals("total_livable_area")&& b<length) {
				b++;
			}
			
			while(!field[c].equals("zip_code")&&c<length) {
				c++;
			}
			
			
			while ((line = br.readLine()) != null) {
				Properties property= new Properties();
				double marketValue=0;
				double totalLivableArea=0;
				int zipCode=0;
				
				//Use comma as a seperatpr
				String[] propertyString = line.split(csvSplitBy);
				
				if(propertyString.length<length) //check if the row has 77 columns in case some data is missing
					continue;
				
				if(propertyString[a].isEmpty()||propertyString[b].isEmpty()||propertyString[c].length()<5||!propertyString[c].matches("[0-9]+"))
					continue; //data is missing, ignore the whole row
				marketValue=Double.parseDouble(propertyString[a]); 
				property.setMarketValue(marketValue);
				
				
				totalLivableArea=Double.parseDouble(propertyString[b]);
				property.setTotalLivableArea(totalLivableArea);;
				
				
				zipCode=Integer.parseInt(propertyString[c].substring(0,5)); //only use first 5 digits as zip-code
				property.setZipCode(zipCode);
				properties.add(property);

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
		return (properties);


	}

	public static void main(String[] args) {

		CSVPropertiesReader test = new CSVPropertiesReader("properties.csv");
		test.read();
	
			//System.out.println(test2.get(3)); //Printing out all the dates


		}


	
}
