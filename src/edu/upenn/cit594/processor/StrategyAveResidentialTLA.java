package edu.upenn.cit594.processor;

import java.util.ArrayList;

import edu.upenn.cit594.data.Properties;
import edu.upenn.cit594.datamanagement.CSVPropertiesReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;

public class StrategyAveResidentialTLA {
	
	public double attributeSum(Integer zip, ArrayList<Properties> props) {
		
		double TLAsum = 0;
		
		for (Properties prop : props) {
			if (prop.getZipCode() == zip) {
				TLAsum += prop.getTotalLivableArea();
			}
		} 
		
		return TLAsum;
	}
	
}
