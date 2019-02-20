package ca.mcgill.ecse.btms.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse.btms.application.BtmsApplication;
import ca.mcgill.ecse.btms.model.BTMS;

public class BtmsControllerTest {
	
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
		
		try {
			BtmsController.createDriver(name);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		// check model in memory
		checkResultDriver(name, btms, 1);
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
		checkResultDriver(name, btms, 0);
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
		checkResultDriver(name, btms, 0);
	}
	
	private void checkResultDriver(String name, BTMS btms, int numberDrivers) {
		assertEquals(numberDrivers, btms.getDrivers().size());
		if (numberDrivers > 0) {
			assertEquals(name, btms.getDriver(0).getName());
			assertEquals(false, btms.getDriver(0).getOnSickLeave());
			assertEquals(1, btms.getDriver(0).getId());
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
		checkResultRoute(number,btms, 1);
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

}
