package edu.upenn.cit594;

import edu.upenn.cit594.logging.Logger;

public class Main {
	
	
	public static void main(String[] args) {
		if (args.length != 5) {
			System.out.println("invalid args");
			System.exit(0);
		}
		if (!(args[0].equals("csv") || args[0].equals("json"))) {
			System.out.println("invalid args");
			System.exit(0);
		}
		
		String ParkingFileFormat = args[0];
		String ParkingFile = args[1];
		String PropertyFile = args[2];
		String PopulationFile = args[3];	
		String LogFile = args[4];
		
		Logger l = Logger.getInstance(LogFile);
		l.log(args);
		
		userInterface ui = new userInterface();
		ui.askUserForStep();
	}
}
