package ca.mcgill.ecse.btms.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ca.mcgill.ecse.btms.application.BtmsApplication;
import ca.mcgill.ecse.btms.model.BTMS;
import ca.mcgill.ecse.btms.model.BusStop;
import ca.mcgill.ecse.btms.model.BusVehicle;
import ca.mcgill.ecse.btms.model.Driver;
import ca.mcgill.ecse.btms.model.DriverSchedule;
import ca.mcgill.ecse.btms.model.DriverSchedule.Shift;
import ca.mcgill.ecse.btms.model.Route;
import ca.mcgill.ecse.btms.model.RouteAssignment;

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
	
	public static void deleteDriver(int id) {
		Driver driver = findDriver(id);
		if (driver != null) {
			driver.delete();
		}
	}

	public static void createRoute(int number, int averageMinutesToNextBusStop, int numberOfBusStops) throws InvalidInputException {
		String error = "";
		if (number <= 0) {
			error = "The number of a route must be greater than zero. ";
		}
		if (number > 9999) {
			error = "The number of a route cannot be greater than 9999. ";
		}
		if (averageMinutesToNextBusStop <= 0) {
			error = error + "The average number of minutes to next bus stop must be greater than zero. ";
		}
		if (numberOfBusStops <= 1) {
			error = error + "The number of bus stops must be at least two.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		BTMS btms = BtmsApplication.getBtms();
		try {
			Route route = btms.addRoute(number, averageMinutesToNextBusStop);
			for (int i=0; i<numberOfBusStops; i++) {
				route.addBusStop(i+1);
			}
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate number")) {
				error = "A route with this number already exists. Please use a different number.";
			}
			throw new InvalidInputException(error);
		}		
	}
	
	public static void createBus(String licencePlate) throws InvalidInputException {
		BTMS btms = BtmsApplication.getBtms();
		try {
			btms.addVehicle(licencePlate, false);
		}
		catch (RuntimeException e) {
			String error = e.getMessage();
			if (error.equals("Cannot create due to duplicate licencePlate")) {
				error = "A bus with this licence plate already exists. Please use a different licence plate.";
			}
			throw new InvalidInputException(error);
		}
	}
	
	public static void toggleSickStatus(int id) throws InvalidInputException {
		Driver driver = findDriver(id);
		if (driver == null) {
			throw new InvalidInputException("An existing driver must be specified to toggle the driver's status.");
		}
		driver.setOnSickLeave(!driver.getOnSickLeave());
	}

	public static void toggleRepairStatus(String licencePlate) throws InvalidInputException {
		BusVehicle bus = BusVehicle.getWithLicencePlate(licencePlate);
		if (bus == null) {
			throw new InvalidInputException("An existing bus must be specified to toggle the bus' status.");
		}
		bus.setInRepairShop(!bus.getInRepairShop());
	}
	
	public static void assign(String licencePlate, int number, Date date) throws InvalidInputException {
		String error = "";
		date = cleanDate(date);
		if (!isWithin365DaysFromToday(date)) {
			error = "The date must be within a year from today. ";
		}
		BusVehicle bus = BusVehicle.getWithLicencePlate(licencePlate);
		if (bus == null) {
			error = error + "A bus must be specified for the assignment. ";
		}
		Route route = Route.getWithNumber(number);
		if (route == null) {
			error = error + "A route must be specified for the assignment.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		BTMS btms = BtmsApplication.getBtms();
		try {
			RouteAssignment assignment = bus.getAssignmentOnDate(date);
			if (assignment == null) {
				btms.addAssignment(date, bus, route);				
			}
			else {
				assignment.setRoute(route);
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void schedule(int driverID, Date date, int number, String licencePlate, String shiftName) throws InvalidInputException {
		String error = "";
		Driver driver = findDriver(driverID);
		if (driver == null) {
			error = "A driver must be specified for the schedule. ";
		}
		RouteAssignment assignment = findRouteAssignment(date, number, licencePlate);
		if (assignment == null) {
			error = error + "An assignment must be specified for the schedule. ";
		}
		Shift shift = null;
		try {
			shift = Shift.valueOf(shiftName);
		}
		catch (IllegalArgumentException e) {
			error = error + "A shift must be specified for the schedule.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		BTMS btms = BtmsApplication.getBtms();
		try {
			btms.addSchedule(shift, driver, assignment);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static List<TODailyOverviewItem> getDailyOverview(Date date) {
		date = cleanDate(date);
		BTMS btms = BtmsApplication.getBtms();
		ArrayList<TODailyOverviewItem> items = new ArrayList<TODailyOverviewItem>();
		for (RouteAssignment assignment : btms.getAssignments()) {
			if (assignment.getDate().equals(date)) {
				boolean inRepairShop = assignment.getBus().isInRepairShop();
				if (assignment.getDriverSchedules().size() == 0) {
					TODailyOverviewItem toDailyOverviewItem = new TODailyOverviewItem(assignment.getRoute().getNumber(), assignment.getBus().getLicencePlate(), inRepairShop, null, 0, null, true);
					items.add(toDailyOverviewItem);
				} else {
					for (DriverSchedule schedule : assignment.getDriverSchedules()) {
						boolean sick = schedule.getDriver().isOnSickLeave();
						TODailyOverviewItem toDailyOverviewItem = new TODailyOverviewItem(assignment.getRoute().getNumber(), assignment.getBus().getLicencePlate(), inRepairShop, schedule.getShift().toString(), schedule.getDriver().getId(), schedule.getDriver().getName(), sick);
						items.add(toDailyOverviewItem);
					}
				}
			}
		}
		return items;
	}

	public static List<TODriver> getDrivers() {
		ArrayList<TODriver> drivers = new ArrayList<TODriver>();
		for (Driver driver : BtmsApplication.getBtms().getDrivers()) {
			TODriver toDriver = new TODriver(driver.getName(), driver.getId());
			drivers.add(toDriver);
		}
		return drivers;
	}
	
	public static List<TODriver> getAvailableDrivers() {
		ArrayList<TODriver> drivers = new ArrayList<TODriver>();
		for (Driver driver : BtmsApplication.getBtms().getDrivers()) {
			if (!driver.isOnSickLeave()) {
				TODriver toDriver = new TODriver(driver.getName(), driver.getId());
				drivers.add(toDriver);				
			}
		}
		return drivers;
	}
	
	public static List<TOBusVehicle> getBuses() {
		ArrayList<TOBusVehicle> buses = new ArrayList<TOBusVehicle>();
		for (BusVehicle bus : BtmsApplication.getBtms().getVehicles()) {
			TOBusVehicle toBusVehicle = new TOBusVehicle(bus.getLicencePlate());
			buses.add(toBusVehicle);
		}
		return buses;
	}
	
	public static List<TOBusVehicle> getAvailableBuses() {
		ArrayList<TOBusVehicle> buses = new ArrayList<TOBusVehicle>();
		for (BusVehicle bus : BtmsApplication.getBtms().getVehicles()) {
			if (!bus.isInRepairShop()) {
				TOBusVehicle toBusVehicle = new TOBusVehicle(bus.getLicencePlate());
				buses.add(toBusVehicle);				
			}
		}
		return buses;
	}

	public static List<TORoute> getRoutes() {
		ArrayList<TORoute> routes = new ArrayList<TORoute>();
		for (Route route : BtmsApplication.getBtms().getRoutes()) {
			TORoute toRoute = new TORoute(route.getNumber(), route.getBusStops().size());
			routes.add(toRoute);				
		}
		return routes;
	}
	
	public static List<TOBusStop> getBusStopsForRoute(int number) {
		ArrayList<TOBusStop> busStops = new ArrayList<TOBusStop>();
		Route route = Route.getWithNumber(number);
		if (route != null) {
			for (BusStop busStop : route.getBusStops()) {
				int index = route.indexOfBusStop(busStop);
				TOBusStop toBusStop = new TOBusStop(busStop.getNumber(), index * route.getAverageMinutesToNextBusStop());
				busStops.add(toBusStop);				
			}
		}
		return busStops;
	}

	public static List<TORouteAssignment> getAssignments() {
		ArrayList<TORouteAssignment> assignments = new ArrayList<TORouteAssignment>();
		for (RouteAssignment assignment : BtmsApplication.getBtms().getAssignments()) {
			TORouteAssignment toAssignment = new TORouteAssignment(assignment.getDate(), assignment.getBus().getLicencePlate(), assignment.getRoute().getNumber());
			assignments.add(toAssignment);				
		}
		return assignments;
	}
	
	public static List<String> getShiftValues() {
		ArrayList<String> shifts = new ArrayList<String>();
		for (Shift shift : Shift.values()) {
			shifts.add(shift.toString());
		}
		return shifts;
	}
	
	private static Date cleanDate(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(date.getTime());
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    java.util.Date tempCleanedDate = cal.getTime();
	    java.sql.Date cleanedDate = new java.sql.Date(tempCleanedDate.getTime());
	    return cleanedDate;
	}

	private static boolean isWithin365DaysFromToday(Date date) {
		java.util.Date tempToday = BtmsApplication.getBtms().getCurrentDate();
		java.util.Date tempInOneYear = new java.util.Date(tempToday.getTime() + 366*24*60*60*1000L);
		java.sql.Date inOneYear = new java.sql.Date(tempInOneYear.getTime());
		return date.before(inOneYear);
	}
	
	private static Driver findDriver(int id) {
		Driver foundDriver = null;
		for (Driver driver : BtmsApplication.getBtms().getDrivers()) {
			if (driver.getId() == id) {
				foundDriver = driver;
				break;
			}
		}
		return foundDriver;
	}

	private static RouteAssignment findRouteAssignment(Date date, int number, String licencePlate) {
		RouteAssignment foundAssignment = null;
		for (RouteAssignment assignment : BtmsApplication.getBtms().getAssignments()) {
			if (assignment.getDate().equals(date) && assignment.getRoute().getNumber() == number && assignment.getBus().getLicencePlate().equals(licencePlate)) {
				foundAssignment = assignment;
				break;
			}
		}
		return foundAssignment;
	}
	
}
