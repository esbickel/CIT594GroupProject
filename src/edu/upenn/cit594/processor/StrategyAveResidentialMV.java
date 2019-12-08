package edu.upenn.cit594.processor;

import java.util.ArrayList;

import edu.upenn.cit594.data.Properties;
import edu.upenn.cit594.datamanagement.CSVPropertiesReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;

public class StrategyAveResidentialMV {
	
	public double attributeSum(Integer zip, ArrayList<Properties> props) {
		
		double MVsum = 0;
		
		for (Properties prop : props) {
			if (prop.getZipCode() == zip) {
				MVsum += prop.getMarketValue();
			}
		}
		
		return MVsum;
	}
	
}
