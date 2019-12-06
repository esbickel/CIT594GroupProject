package edu.upenn.cit594.datamanagement;

import java.util.ArrayList;

import edu.upenn.cit594.data.Parking;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVParkingReader implements Reader {

        public ArrayList<Parking> read() {


        ArrayList<ArrayList<String>> parkingViolations = new ArrayList<ArrayList<String>>();
        String csvFile = "parking.csv";
        BufferedReader br = null;

        String line = "";
        String csvSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                //Use comma as a seperatpr
                String[] violations = line.split(csvSplitBy);
                System.out.println("The date of the violation is " + violations[0]);
                ArrayList<String> row = new ArrayList<>();
                for (String col : violations) {
                    row.add(col);
                }
                parkingViolations.add(row);
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
        return (parkingViolations);


    }

    public static void main(String[] args) {

        CSVFileReader test = new CSVFileReader();
        ArrayList<ArrayList<String>> test2 = test.csvData();
        for(ArrayList<String> each: test2) {
            System.out.println(each.get(0)); //Printing out all the dates


        }


    }

}


	
	

