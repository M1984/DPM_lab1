package ca.mcgill.ecse.btms.application;

import ca.mcgill.ecse.btms.model.*;

public class BtmsApplication {
	
	private static BTMS btms;

	private static int counter = 1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Step 1
		// TODO
		printBtmsStatistics(btms);
		
		// Step 2
		// TODO
		printBtmsStatistics(btms);
		
		// Step 3
		// TODO
		printBtmsStatistics(btms);
		
		// Step 4
		// TODO
		printBtmsStatistics(btms);
		
		// Step 5
		// TODO
		printBtmsStatistics(btms);
		
		// Step 6
		// TODO
		printBtmsStatistics(btms);
		
		// Step 7
		// TODO
		printBtmsStatistics(btms);
		
		// Step 8
		// TODO
		printBtmsStatistics(btms);
		
		// Step 9
		// TODO
		printBtmsStatistics(btms);
		
		// Step 10
		// TODO
		printBtmsStatistics(btms);
	}
	
	public static int increaseCounter() {
		return counter++;
	}
	
	private static void printBtmsStatistics(BTMS btms) {
		System.out.println("===== BTMS: Step " + increaseCounter() +" =====");
		System.out.println("Date: " + btms.getCurrentDate());
		System.out.println("Number of buses: " + btms.getVehicles().size());
		System.out.println("Number of routes: " + btms.getRoutes().size());
		System.out.println("Number of drivers: " + btms.getDrivers().size());
		System.out.println("Number of assignments: " + btms.getAssignments().size());
		System.out.println("Number of schedules: " + btms.getSchedule().size());
		for (BusVehicle bus : btms.getVehicles()) {
			System.out.println("Bus: " + bus.getLicencePlate() + " (in repair: " + bus.getInRepairShop() + ") / #Assignments: " + bus.getRouteAssignments().size());
		}
		for (Route route : btms.getRoutes()) {
			System.out.println("Route: " + route.getNumber() + " / #Assignments: " + route.getRouteAssignments().size());
		}
		for (Driver driver : btms.getDrivers()) {
			System.out.println("Driver: " + driver.getName() + " (sick: " + driver.getOnSickLeave() + ") / #Schedules: " + driver.getDriverSchedules().size());
		}
		for (RouteAssignment assignment : btms.getAssignments()) {
			System.out.println("Assignment: " + assignment.getBus().getLicencePlate() + ", " + assignment.getRoute().getNumber() + ", Date: " + assignment.getDate() +
					" / #Schedules: " + assignment.getDriverSchedules().size());
		}
		for (DriverSchedule schedule : btms.getSchedule()) {
			System.out.println("Schedule: " + schedule.getDriver().getName() + ", " + schedule.getAssignment().getBus().getLicencePlate() + 
					", " + schedule.getAssignment().getRoute().getNumber() + ", Date: " + schedule.getAssignment().getDate() + " " + schedule.getShift().toString());
		}
		System.out.println("========================");
	}

	public static BTMS getBtms() {
		if (btms == null) {
			btms = new BTMS();
		}
 		return btms;
	}
	
}
