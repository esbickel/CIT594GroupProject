package edu.upenn.cit594.processor;

import java.util.ArrayList;

import edu.upenn.cit594.data.Properties;

public interface Strategy {
	
	public long attributeSum(Integer zip, ArrayList<Properties> props);
	
}
