package edu.upenn.cit594.datamanagement;

import java.io.*;
import java.util.*;

import edu.upenn.cit594.data.Population;


public class TXTPopulationReader implements PopulationReader{

	String fileName;
	ArrayList<Population> population;
	
	public TXTPopulationReader(String myFile) {
		fileName=myFile;
		
	}
	
	public ArrayList<Population> read(){
		population = new ArrayList<>();
		String str;
		String txtSplitBy =" ";
		
			BufferedReader in = null;
				try {
					in = new  BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					while ((str=in.readLine())!= null) {
						String[] populationString=str.split(txtSplitBy);
						
						//System.out.print(populationString[0]+"\n");
						
						if(populationString.length<2)
							continue;
						Population pp = new Population();
						pp.setPopulation(Integer.parseInt(populationString[1]));
						pp.setZipCode(Integer.parseInt(populationString[0]));
						
						//System.out.print(pp.getPopulation()+"\n");

						population.add(pp);
						
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		//System.out.println(tweets.get(1).getCoordinate()+tweets.get(1).getText());
		
	return population;
	}

	public String[] splitt(String str){
		String strr = str.trim();
		String[] pp = strr.split("\t");
		return pp;
	}
	
	public static void main(String[] args) {

		TXTPopulationReader test3 = new TXTPopulationReader("population.txt");
		test3.read();

	}
	
}
