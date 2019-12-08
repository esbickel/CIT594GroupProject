package edu.upenn.cit594.processor;

import java.util.ArrayList;

import edu.upenn.cit594.data.Properties;
import edu.upenn.cit594.datamanagement.CSVPropertiesReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;

public class StrategyAveResidentialMV implements Strategy {
	
	public long attributeSum(Integer zip, ArrayList<Properties> props) {
		
		long MVsum = 0;
		
		for (Properties prop : props) {
			if (prop.getZipCode() == zip) {
				MVsum += prop.getMarketValue();
			}
		}
		
		return MVsum;
	}
	
}
