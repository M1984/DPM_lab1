package ca.mcgill.ecse.btms.controller;

import ca.mcgill.ecse.btms.application.BtmsApplication;
import ca.mcgill.ecse.btms.model.BTMS;

public class BtmsController {

	public BtmsController() {
	}
	
	public static void createDriver(String name) throws InvalidInputException {
		BTMS btms = BtmsApplication.getBtms();
		try {
			btms.addDriver(name, false);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void createRoute(int number) throws InvalidInputException {
		String error = "";
		if (number <= 0) {
			error = "The number of a route must be greater than zero. ";
		}
		if (number > 9999) {
			error = "The number of a route cannot be greater than 9999. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		BTMS btms = BtmsApplication.getBtms();
		try {
			btms.addRoute(number);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate number")) {
				error = "A route with this number already exists. Please use a different number.";
			}
			throw new InvalidInputException(error);
		}		
	}
	
}
