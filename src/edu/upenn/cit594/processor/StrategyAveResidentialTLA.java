package edu.upenn.cit594.processor;

import java.util.ArrayList;

import edu.upenn.cit594.data.Properties;
import edu.upenn.cit594.datamanagement.CSVPropertiesReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;

public class StrategyAveResidentialTLA implements Strategy {
	
	public long attributeSum(Integer zip, ArrayList<Properties> props) {
		
		long TLAsum = 0;
		
		for (Properties prop : props) {
			if (prop.getZipCode() == zip) {
				TLAsum += prop.getTotalLivableArea();
			}
		} 
		
		return TLAsum;
	}
	
}
