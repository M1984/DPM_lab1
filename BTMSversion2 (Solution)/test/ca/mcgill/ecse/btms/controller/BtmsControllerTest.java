package ca.mcgill.ecse.btms.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse.btms.application.BtmsApplication;
import ca.mcgill.ecse.btms.model.BTMS;
import ca.mcgill.ecse.btms.model.BusVehicle;
import ca.mcgill.ecse.btms.model.Driver;
import ca.mcgill.ecse.btms.model.DriverSchedule.Shift;
import ca.mcgill.ecse.btms.model.Route;
import ca.mcgill.ecse.btms.model.RouteAssignment;

public class BtmsControllerTest {
	
	private static int nextDriverID = 1;
	
	@Before
	public void setUp() {
		// clear all data
		BTMS btms = BtmsApplication.getBtms();
		btms.delete();
	}
	
	@Test
	public void testCreateDriverSuccess() {
		BTMS btms = BtmsApplication.getBtms();
		String name = "driver";
		int id = nextDriverID++;
		
		try {
			BtmsController.createDriver(name);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultDriver(name, btms, 1, false, id);
	}
	
	@Test
	public void testCreateDriverNull() {
		BTMS btms = BtmsApplication.getBtms();		
		String name = null;

		String error = null;
		try {
			BtmsController.createDriver(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The name of a driver cannot be empty.", error);
		// check no change in memory
		checkResultDriver(name, btms, 0, false, 0);
	}
	
	@Test
	public void testCreateDriverEmpty() {
		BTMS btms = BtmsApplication.getBtms();		
		String name = "";

		String error = null;
		try {
			BtmsController.createDriver(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The name of a driver cannot be empty.", error);
		// check no change in memory
		checkResultDriver(name, btms, 0, false, 0);
	}
	
	@Test
	public void testToggleSickStatus() {
		BTMS btms = BtmsApplication.getBtms();
		String name = "driver";
		int id = nextDriverID++;
		btms.addDriver(name, false);
		// check that sick status is false
		checkResultDriver(name, btms, 1, false, id);

		try {
			BtmsController.toggleSickStatus(id);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check that sick status is true
		checkResultDriver(name, btms, 1, true, id);
	}
	
	@Test
	public void testToggleSickStatusDriverDoesNotExist() {
		BTMS btms = BtmsApplication.getBtms();
		String name = "driver";
		int id = nextDriverID++;
		btms.addDriver(name, false);
		// check that sick status is false
		checkResultDriver(name, btms, 1, false, id);

		String error = null;
		try {
			BtmsController.toggleSickStatus(nextDriverID);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("An existing driver must be specified to toggle the driver's status.", error);		
		// check that sick status is still false
		checkResultDriver(name, btms, 1, false, id);
	}
	
	@Test
	public void testDeleteDriverSuccess() {
		BTMS btms = BtmsApplication.getBtms();
		String name = "driver";
		int id = nextDriverID++;
		
		try {
			BtmsController.createDriver(name);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultDriver(name, btms, 1, false, id);
		
		BtmsController.deleteDriver(id);
		
		// check model in memory
		checkResultDriver(name, btms, 0, false, 0);
	}

	@Test
	public void testDeleteDriverDoesNotExist() {
		BTMS btms = BtmsApplication.getBtms();
		String name = "driver";
		int id = nextDriverID++;
		
		try {
			BtmsController.createDriver(name);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultDriver(name, btms, 1, false, id);
		
		BtmsController.deleteDriver(nextDriverID);
				
		// check no change in memory
		checkResultDriver(name, btms, 1, false, id);
	}
	
	private void checkResultDriver(String name, BTMS btms, int numberDrivers, boolean sickStatus, int id) {
		assertEquals(numberDrivers, btms.getDrivers().size());
		if (numberDrivers > 0) {
			assertEquals(name, btms.getDriver(0).getName());
			assertEquals(sickStatus, btms.getDriver(0).getOnSickLeave());
			assertEquals(id, btms.getDriver(0).getId());
			assertEquals(btms, btms.getDriver(0).getBTMS());
			assertEquals(0, btms.getDriver(0).getDriverSchedules().size());
		}
		assertEquals(0, btms.getRoutes().size());
		assertEquals(0, btms.getVehicles().size());
		assertEquals(0, btms.getAssignments().size());
		assertEquals(0, btms.getSchedule().size());
	}
	
	@Test
	public void testCreateRouteSuccess() {
		BTMS btms = BtmsApplication.getBtms();
		int number = 1;
		
		try {
			BtmsController.createRoute(number);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultRoute(number, btms, 1);
	}
	
	@Test
	public void testCreateRouteNotPositive() {
		BTMS btms = BtmsApplication.getBtms();		
		int number = 0;

		String error = null;
		try {
			BtmsController.createRoute(number);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The number of a route must be greater than zero.", error);
		// check no change in memory
		checkResultRoute(number, btms, 0);
	}
	
	@Test
	public void testCreateRouteDuplicate() {
		BTMS btms = BtmsApplication.getBtms();		
		int number = 1;
		
		try {
			BtmsController.createRoute(number);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		// check model in memory
		checkResultRoute(number, btms, 1);
		
		String error = null;
		try {
			BtmsController.createRoute(number);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("A route with this number already exists. Please use a different number.", error);
		// check no change in memory
		checkResultRoute(number, btms, 1);
	}
	
	@Test
	public void testCreateRouteSuccessMaximumRouteNumber() {
		BTMS btms = BtmsApplication.getBtms();
		int number = 9999;
		
		try {
			BtmsController.createRoute(number);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultRoute(number, btms, 1);
	}
	
	@Test
	public void testCreateRouteNumberTooLong() {
		BTMS btms = BtmsApplication.getBtms();		
		int number = 10000;

		String error = null;
		try {
			BtmsController.createRoute(number);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The number of a route cannot be greater than 9999.", error);
		// check no change in memory
		checkResultRoute(number, btms, 0);
	}

	private void checkResultRoute(int number, BTMS btms, int numberRoutes) {
		assertEquals(numberRoutes, btms.getRoutes().size());
		if (numberRoutes > 0) {
			assertEquals(number, btms.getRoute(0).getNumber());
			assertEquals(btms, btms.getRoute(0).getBTMS());
			assertEquals(0, btms.getRoute(0).getRouteAssignments().size());
		}
		assertEquals(0, btms.getDrivers().size());
		assertEquals(0, btms.getVehicles().size());
		assertEquals(0, btms.getAssignments().size());
		assertEquals(0, btms.getSchedule().size());
	}

	@Test
	public void testCreateBusSuccess() {
		BTMS btms = BtmsApplication.getBtms();
		String licencePlate = "XYZ123";
		
		try {
			BtmsController.createBus(licencePlate);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultBus(licencePlate, btms, 1, false);
	}
	
	@Test
	public void testCreateBusNull() {
		BTMS btms = BtmsApplication.getBtms();		
		String licencePlate = null;

		String error = null;
		try {
			BtmsController.createBus(licencePlate);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The licence plate of a bus cannot be empty.", error);
		// check no change in memory
		checkResultBus(licencePlate, btms, 0, false);
	}
	
	@Test
	public void testCreateBusEmpty() {
		BTMS btms = BtmsApplication.getBtms();		
		String licencePlate = "";

		String error = null;
		try {
			BtmsController.createBus(licencePlate);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The licence plate of a bus cannot be empty.", error);
		// check no change in memory
		checkResultBus(licencePlate, btms, 0, false);
	}
	
	@Test
	public void testCreateBusSuccessMaximumLicenceNr() {
		BTMS btms = BtmsApplication.getBtms();		
		String licencePlate = "abcdefghij";

		try {
			BtmsController.createBus(licencePlate);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultBus(licencePlate, btms, 1, false);
	}
	
	@Test
	public void testCreateBusLicenceNrTooLong() {
		BTMS btms = BtmsApplication.getBtms();		
		String licencePlate = "abcdefghijk";

		String error = null;
		try {
			BtmsController.createBus(licencePlate);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The licence plate of a bus cannot be longer than 10 characters.", error);
		// check no change in memory
		checkResultBus(licencePlate, btms, 0, false);
	}
	
	@Test
	public void testCreateBusDuplicate() {
		BTMS btms = BtmsApplication.getBtms();
		String licencePlate = "XYZ123";
		
		try {
			BtmsController.createBus(licencePlate);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultBus(licencePlate, btms, 1, false);
		
		String error = null;
		try {
			BtmsController.createBus(licencePlate);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("A bus with this licence plate already exists. Please use a different licence plate.", error);
		// check no change in memory
		checkResultBus(licencePlate, btms, 1, false);
	}
	
	@Test
	public void testToggleRepairStatus() {
		BTMS btms = BtmsApplication.getBtms();
		String licencePlate = "XYZ123";
		btms.addVehicle(licencePlate, false);
		// check that repair status is false
		checkResultBus(licencePlate, btms, 1, false);

		try {
			BtmsController.toggleRepairStatus(licencePlate);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check that repair status is true
		checkResultBus(licencePlate, btms, 1, true);
	}
	
	@Test
	public void testToggleRepairStatusBusNull() {
		BTMS btms = BtmsApplication.getBtms();
		String licencePlate = "XYZ123";
		btms.addVehicle(licencePlate, false);
		// check that repair status is false
		checkResultBus(licencePlate, btms, 1, false);

		String error = null;
		try {
			BtmsController.toggleRepairStatus(null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("An existing bus must be specified to toggle the bus' status.", error);		
		// check that repair status is still false
		checkResultBus(licencePlate, btms, 1, false);
	}
	
	@Test
	public void testToggleRepairStatusBusDoesNotExist() {
		BTMS btms = BtmsApplication.getBtms();
		String licencePlate = "XYZ123";
		btms.addVehicle(licencePlate, false);
		// check that repair status is false
		checkResultBus(licencePlate, btms, 1, false);

		String error = null;
		try {
			BtmsController.toggleRepairStatus("321ZYX");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("An existing bus must be specified to toggle the bus' status.", error);
		// check that repair status is still false
		checkResultBus(licencePlate, btms, 1, false);
	}
	
	private void checkResultBus(String licencePlate, BTMS btms, int numberBuses, boolean repairStatus) {
		assertEquals(numberBuses, btms.getVehicles().size());
		if (numberBuses > 0) {
			assertEquals(licencePlate, btms.getVehicle(0).getLicencePlate());
			assertEquals(repairStatus, btms.getVehicle(0).getInRepairShop());
			assertEquals(btms, btms.getVehicle(0).getBTMS());
			assertEquals(0, btms.getVehicle(0).getRouteAssignments().size());
		}
		assertEquals(0, btms.getRoutes().size());
		assertEquals(0, btms.getDrivers().size());
		assertEquals(0, btms.getAssignments().size());
		assertEquals(0, btms.getSchedule().size());
	}

	@Test
	public void testAssignSuccess() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date date = new java.sql.Date(tempDate.getTime());
		String licencePlate = "XYZ123";
		BusVehicle bus = btms.addVehicle(licencePlate, false);
		int number = 1;
		Route route = btms.addRoute(number);
		
		try {
			BtmsController.assign(licencePlate, number, date);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultAssignment(bus, route, date, btms, 1, 1, 1);
	}
	
	@Test
	public void testAssignBusSameDateDifferentRoute() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date date = new java.sql.Date(tempDate.getTime());
		String licencePlate = "XYZ123";
		BusVehicle bus = btms.addVehicle(licencePlate, false);
		int number = 1;
		btms.addRoute(number);
		int number2 = 2;
		Route route2 = btms.addRoute(number2);
		
		try {
			BtmsController.assign(licencePlate, number, date);
			BtmsController.assign(licencePlate, number2, date);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
				
		// check model in memory
		checkResultAssignment(bus, route2, date, btms, 1, 2, 1);
	}
	
	@Test
	public void testAssignDateMoreThan365Days() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.util.Date tempFutureDate = new java.util.Date(tempDate.getTime() + 366*24*60*60*1000L);
		java.sql.Date date = new java.sql.Date(tempFutureDate.getTime());
		String licencePlate = "XYZ123";
		BusVehicle bus = btms.addVehicle(licencePlate, false);
		int number = 1;
		Route route = btms.addRoute(number);
		
		String error = null;
		try {
			BtmsController.assign(licencePlate, number, date);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The date must be within a year from today.", error);
		// check no change in memory
		checkResultAssignment(bus, route, date, btms, 0, 1, 1);
	}
	
	@Test
	public void testAssignBusNull() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date date = new java.sql.Date(tempDate.getTime());
		int number = 1;
		Route route = btms.addRoute(number);
		
		String error = null;
		try {
			BtmsController.assign(null, number, date);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("A bus must be specified for the assignment.", error);
		// check no change in memory
		checkResultAssignment(null, route, date, btms, 0, 1, 0);
	}
	
	@Test
	public void testAssignRouteDoesNotExist() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date date = new java.sql.Date(tempDate.getTime());
		String licencePlate = "XYZ123";
		BusVehicle bus = btms.addVehicle(licencePlate, false);
		
		String error = null;
		try {
			BtmsController.assign(licencePlate, 0, date);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("A route must be specified for the assignment.", error);
		// check no change in memory
		checkResultAssignment(bus, null, date, btms, 0, 0, 1);
	}
	
	private void checkResultAssignment(BusVehicle bus, Route route, Date date, BTMS btms, int numberAssignments, int numberRoutes, int numberBuses) {
		assertEquals(numberAssignments, btms.getAssignments().size());
		if (numberAssignments > 0) {
			assertEquals(date, btms.getAssignment(0).getDate());
			assertEquals(bus, btms.getAssignment(0).getBus());
			assertEquals(route, btms.getAssignment(0).getRoute());
			assertEquals(btms, btms.getAssignment(0).getBTMS());
			assertEquals(0, btms.getAssignment(0).getDriverSchedules().size());
		}
		assertEquals(numberRoutes, btms.getRoutes().size());
		assertEquals(0, btms.getDrivers().size());
		assertEquals(numberBuses, btms.getVehicles().size());
		assertEquals(0, btms.getSchedule().size());
	}

	@Test
	public void testScheduleSuccess() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date date = new java.sql.Date(tempDate.getTime());
		String licencePlate = "XYZ123";
		BusVehicle bus = btms.addVehicle(licencePlate, false);
		int number = 1;
		Route route = btms.addRoute(number);
		RouteAssignment assignment = btms.addAssignment(date, bus, route);
		String name = "driver";
		int id = nextDriverID++;
		Driver driver = btms.addDriver(name, false);
		String shift = "Afternoon";
				
		try {
			BtmsController.schedule(id, date, number, licencePlate, shift);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultSchedule(driver, assignment, Shift.valueOf(shift), btms, 1, 1, 1, 1, 1);
	}
	
	@Test
	public void testScheduleDriverDoesNotExist() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date date = new java.sql.Date(tempDate.getTime());
		String licencePlate = "XYZ123";
		BusVehicle bus = btms.addVehicle(licencePlate, false);
		int number = 1;
		Route route = btms.addRoute(number);
		RouteAssignment assignment = btms.addAssignment(date, bus, route);
		String shift = "Afternoon";
		
		String error = null;
		try {
			BtmsController.schedule(nextDriverID, date, number, licencePlate, shift);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("A driver must be specified for the schedule.", error);
		// check no change in memory
		checkResultSchedule(null, assignment, Shift.valueOf(shift), btms, 0, 1, 1, 1, 0);
	}
	
	@Test
	public void testScheduleAssignmentDateNull() {
		BTMS btms = BtmsApplication.getBtms();
		java.sql.Date date = null;
		String licencePlate = "XYZ123";
		btms.addVehicle(licencePlate, false);
		int number = 1;
		btms.addRoute(number);
		RouteAssignment assignment = null;
		String name = "driver";
		int id = nextDriverID++;
		Driver driver = btms.addDriver(name, false);
		String shift = "Afternoon";
		
		String error = null;
		try {
			BtmsController.schedule(id, date, number, licencePlate, shift);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("An assignment must be specified for the schedule.", error);
		// check no change in memory
		checkResultSchedule(driver, assignment, Shift.valueOf(shift), btms, 0, 0, 1, 1, 1);
	}
	
	@Test
	public void testScheduleAssignmentNumberDoesNotExist() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date date = new java.sql.Date(tempDate.getTime());
		String licencePlate = "XYZ123";
		btms.addVehicle(licencePlate, false);
		int number = 0;
		RouteAssignment assignment = null;
		String name = "driver";
		int id = nextDriverID++;
		Driver driver = btms.addDriver(name, false);
		String shift = "Afternoon";
		
		String error = null;
		try {
			BtmsController.schedule(id, date, number, licencePlate, shift);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("An assignment must be specified for the schedule.", error);
		// check no change in memory
		checkResultSchedule(driver, assignment, Shift.valueOf(shift), btms, 0, 0, 0, 1, 1);
	}
	
	@Test
	public void testScheduleAssignmentLicencePlateNull() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date date = new java.sql.Date(tempDate.getTime());
		String licencePlate = null;
		int number = 1;
		btms.addRoute(number);
		RouteAssignment assignment = null;
		String name = "driver";
		int id = nextDriverID++;
		Driver driver = btms.addDriver(name, false);
		String shift = "Afternoon";
		
		String error = null;
		try {
			BtmsController.schedule(id, date, number, licencePlate, shift);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("An assignment must be specified for the schedule.", error);
		// check no change in memory
		checkResultSchedule(driver, assignment, Shift.valueOf(shift), btms, 0, 0, 1, 0, 1);
	}
	
	@Test
	public void testScheduleAssignmentLicencePlateDoesNotExist() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date date = new java.sql.Date(tempDate.getTime());
		String licencePlate = "321ZYX";
		int number = 1;
		btms.addRoute(number);
		RouteAssignment assignment = null;
		String name = "driver";
		int id = nextDriverID++;
		Driver driver = btms.addDriver(name, false);
		String shift = "Afternoon";
		
		String error = null;
		try {
			BtmsController.schedule(id, date, number, licencePlate, shift);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("An assignment must be specified for the schedule.", error);
		// check no change in memory
		checkResultSchedule(driver, assignment, Shift.valueOf(shift), btms, 0, 0, 1, 0, 1);
	}
	
	@Test
	public void testScheduleShiftDoesNotExist() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date date = new java.sql.Date(tempDate.getTime());
		String licencePlate = "XYZ123";
		BusVehicle bus = btms.addVehicle(licencePlate, false);
		int number = 1;
		Route route = btms.addRoute(number);
		RouteAssignment assignment = btms.addAssignment(date, bus, route);
		String name = "driver";
		int id = nextDriverID++;
		Driver driver = btms.addDriver(name, false);
		String shift = "Today";
		
		String error = null;
		try {
			BtmsController.schedule(id, date, number, licencePlate, shift);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("A shift must be specified for the schedule.", error);
		// check no change in memory
		checkResultSchedule(driver, assignment, Shift.Morning, btms, 0, 1, 1, 1, 1);
	}
	
	private void checkResultSchedule(Driver driver, RouteAssignment assignment, Shift shift, BTMS btms, int numberSchedules, int numberAssignments, int numberRoutes, int numberBuses, int numberDrivers) {
		assertEquals(numberSchedules, btms.getSchedule().size());
		if (numberSchedules > 0) {
			assertEquals(assignment, btms.getSchedule(0).getAssignment());
			assertEquals(driver, btms.getSchedule(0).getDriver());
			assertEquals(shift, btms.getSchedule(0).getShift());
			assertEquals(btms, btms.getSchedule(0).getBTMS());
		}
		assertEquals(numberAssignments, btms.getAssignments().size());
		assertEquals(numberRoutes, btms.getRoutes().size());
		assertEquals(numberDrivers, btms.getDrivers().size());
		assertEquals(numberBuses, btms.getVehicles().size());
	}

	@Test
	public void testGetAssignmentsForDateSuccessTwoAssignments() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date today = new java.sql.Date(tempDate.getTime());
		java.sql.Date tomorrow = new java.sql.Date(tempDate.getTime() + 24*60*60*1000);
		String licencePlate = "XYZ123";
		BusVehicle bus = btms.addVehicle(licencePlate, false);
		String licencePlate2 = "123XYZ";
		BusVehicle bus2 = btms.addVehicle(licencePlate2, false);
		int number = 1;
		Route route = btms.addRoute(number);
		RouteAssignment assignment = btms.addAssignment(today, bus, route);
		RouteAssignment assignment2 = btms.addAssignment(tomorrow, bus2, route);
		RouteAssignment assignment3 = btms.addAssignment(today, bus, route);
		String name = "driver";
		nextDriverID++;
		Driver driver = btms.addDriver(name, false);
		Shift shift1 = Shift.Morning;
		Shift shift2 = Shift.Afternoon;
		Shift shift3 = Shift.Night;
		btms.addSchedule(shift1, driver, assignment);
		btms.addSchedule(shift2, driver, assignment2);
		btms.addSchedule(shift3, driver, assignment3);
		
		ArrayList<TODailyOverviewItem> result = (ArrayList<TODailyOverviewItem>) BtmsController.getDailyOverview(today);
		
		// check model in memory
		assertEquals(2, result.size());
		checkResultDailyOverviewItem(assignment, result.get(0));
		checkResultDailyOverviewItem(assignment3, result.get(1));
	}

	@Test
	public void testGetAssignmentsForDateSuccessOneAssignment() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date today = new java.sql.Date(tempDate.getTime());
		java.sql.Date tomorrow = new java.sql.Date(tempDate.getTime() + 24*60*60*1000);
		String licencePlate = "XYZ123";
		BusVehicle bus = btms.addVehicle(licencePlate, false);
		String licencePlate2 = "123XYZ";
		BusVehicle bus2 = btms.addVehicle(licencePlate2, false);
		int number = 1;
		Route route = btms.addRoute(number);
		RouteAssignment assignment = btms.addAssignment(today, bus, route);
		RouteAssignment assignment2 = btms.addAssignment(tomorrow, bus2, route);
		RouteAssignment assignment3 = btms.addAssignment(today, bus, route);
		String name = "driver";
		nextDriverID++;
		Driver driver = btms.addDriver(name, false);
		Shift shift1 = Shift.Morning;
		Shift shift2 = Shift.Afternoon;
		Shift shift3 = Shift.Night;
		btms.addSchedule(shift1, driver, assignment);
		btms.addSchedule(shift2, driver, assignment2);
		btms.addSchedule(shift3, driver, assignment3);
		
		ArrayList<TODailyOverviewItem> result = (ArrayList<TODailyOverviewItem>) BtmsController.getDailyOverview(tomorrow);
		
		// check model in memory
		assertEquals(1, result.size());
		checkResultDailyOverviewItem(assignment2, result.get(0));
	}

	@Test
	public void testGetAssignmentsForDateSuccessNoAssignment() {
		BTMS btms = BtmsApplication.getBtms();
		java.util.Date tempDate = btms.getCurrentDate();
		java.sql.Date today = new java.sql.Date(tempDate.getTime());
		java.sql.Date tomorrow = new java.sql.Date(tempDate.getTime() + 24*60*60*1000);
		java.sql.Date dayAfterTomorrow = new java.sql.Date(tempDate.getTime() + 2*24*60*60*1000);
		String licencePlate = "XYZ123";
		BusVehicle bus = btms.addVehicle(licencePlate, false);
		String licencePlate2 = "123XYZ";
		BusVehicle bus2 = btms.addVehicle(licencePlate2, false);
		int number = 1;
		Route route = btms.addRoute(number);
		RouteAssignment assignment = btms.addAssignment(today, bus, route);
		RouteAssignment assignment2 = btms.addAssignment(tomorrow, bus2, route);
		RouteAssignment assignment3 = btms.addAssignment(today, bus, route);
		String name = "driver";
		nextDriverID++;
		Driver driver = btms.addDriver(name, false);
		Shift shift1 = Shift.Morning;
		Shift shift2 = Shift.Afternoon;
		Shift shift3 = Shift.Night;
		btms.addSchedule(shift1, driver, assignment);
		btms.addSchedule(shift2, driver, assignment2);
		btms.addSchedule(shift3, driver, assignment3);
		
		ArrayList<TODailyOverviewItem> result = (ArrayList<TODailyOverviewItem>) BtmsController.getDailyOverview(dayAfterTomorrow);
		
		// check model in memory
		assertEquals(0, result.size());
	}
	
	private void checkResultDailyOverviewItem(RouteAssignment assignment, TODailyOverviewItem item) {
		assertEquals(assignment.getRoute().getNumber(), item.getNumber());
		assertEquals(assignment.getBus().getLicencePlate(), item.getLicencePlate());
		assertEquals(assignment.getBus().isInRepairShop(), item.isInRepairShop());
		assertEquals(assignment.getDriverSchedule(0).getShift().toString(), item.getShift());
		assertEquals(assignment.getDriverSchedule(0).getDriver().getId(), item.getId());
		assertEquals(assignment.getDriverSchedule(0).getDriver().getName(), item.getName());
		assertEquals(assignment.getDriverSchedule(0).getDriver().isOnSickLeave(), item.isSick());
	}
	
}
