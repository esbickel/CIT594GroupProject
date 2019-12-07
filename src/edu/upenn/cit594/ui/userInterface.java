package edu.upenn.cit594.ui;

import java.util.Scanner;

public class userInterface {
	int step;
	int zipCode;

	public userInterface() {

	}

	public void askUserForStep() {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object

		while(true) {
			System.out.println("please specify the action to be performed (type 0~6)"+"\n");

			String userStep = myObj.nextLine();  // Read user input
			if(userStep.matches("[0-9]+") && (Integer.parseInt(userStep))<=6&&Integer.parseInt(userStep)>=0)
			{
				step = Integer.parseInt(userStep);
				//start processing
				//processor will call askUserForZipCode
			}
			askUserForStep();
		}
	}
	
	public void askUserForZipCode() {
		System.out.println("please specify the Zip Code");
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object

		String userZipCode = myObj.nextLine();  // Read user input
		while(true) {
		if(userZipCode.matches("[0-9]+") && userZipCode.length()==5)
		{
			zipCode=Integer.parseInt(userZipCode);
		}
		askUserForZipCode();
		}
	}
	
	
	public static void main(String[] args) {

		userInterface testInput = new userInterface();
		testInput.askUserForStep();
		testInput.askUserForZipCode();

		}

}
