package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Logger {
	
	private FileWriter fw;
	private PrintWriter out;
	private static String logFile;
	
	// Constructor
	private Logger (String filename) {
		if (logFile == null) {logFile = "log.txt";}
		try {
			fw = new FileWriter(logFile, true);
			out = new PrintWriter(fw);}
		catch (Exception e) {
			try {
				out = new PrintWriter(new File(logFile));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	//Instance
	private static Logger instance = new Logger(logFile);
	
	//Access Method
	public static Logger getInstance() {
		return instance;
		}
	//Access MEthod to set Logger filename
	public static Logger getInstance(String fileName) {
		if (logFile == null) {logFile = fileName;}
		return instance;}
	
	//Non-static method
	public void log(String msg) {
		//input:
		// User choices  + current time
		if(msg.equals("") || msg == null) {
			return;
		}
		
		String time = Long.toString(System.currentTimeMillis());
		
		String toPrint = time + " " + msg;
		
		out.println(toPrint);
		out.flush();
	}
	
	//Overload log method for integers
	public void log(int num) {
		String msg = Integer.toString(num);
		log(msg);
	}
	//Overload log method for array
	public void log(String[] args) {
		String msg = "";
		for (String s: args) {
			msg = msg + s + " ";
		}
		this.log(msg);
	}

	
	/*NEED TO ADD, TO ACCESS LOGGER
	 * Logger newLog = Logger.getInstance();
	 * newLog.log(); // with info
	 * 
	 * PLACES TO USE:
	 * 		When program starts (time + runtime args)
	 * 		When user inputs a new action (log time + action)
	 * 		When a file is opened to read (log time + fileName)
	 * 		When a user enters ZIP, steps 3,4,5 ()
	 */
	
	
	//Test Main
	public static void main(String[] args) {
		/*User needs to enter:
		 * - number (0-6) for activity choice
		 * 
		 */
		
		Logger newLog = Logger.getInstance();
		newLog.log("Test Message 1");
		newLog.log(5);
		
		
		Logger anotherLog = Logger.getInstance();
		anotherLog.log("Log from another instance");
		
	}
}
