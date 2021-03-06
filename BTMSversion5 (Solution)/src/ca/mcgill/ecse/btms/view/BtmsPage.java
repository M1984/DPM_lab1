package ca.mcgill.ecse.btms.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse.btms.controller.BtmsController;
import ca.mcgill.ecse.btms.controller.InvalidInputException;
import ca.mcgill.ecse.btms.controller.TOBusVehicle;
import ca.mcgill.ecse.btms.controller.TODailyOverviewItem;
import ca.mcgill.ecse.btms.controller.TODriver;
import ca.mcgill.ecse.btms.controller.TORoute;
import ca.mcgill.ecse.btms.controller.TORouteAssignment;

public class BtmsPage extends JFrame {

	private static final long serialVersionUID = -4426310869335015542L;
	
	// UI elements
	private JLabel errorMessage;
	// driver
	private JTextField driverNameTextField;
	private JLabel driverNameLabel;
	private JButton addDriverButton;
	private JComboBox<String> driverToggleList;
	private JLabel driverToggleLabel;
	private JButton sickButton;
	private JButton deleteDriverButton;
	// route
	private JTextField routeNumberTextField;
	private JLabel routeNumberLabel;
	private JTextField routeAverageMinutesTextField;
	private JLabel routeAverageMinutesLabel;
	private JTextField routeNumberBusStopsTextField;
	private JLabel routeNumberBusStopsLabel;
	private JButton addRouteButton;
	private JButton updateRouteButton;
	private JButton deleteRouteButton;
	// bus
	private JTextField busLicencePlateTextField;
	private JLabel busLicencePlateLabel;
	private JButton addBusButton;
	private JComboBox<String> busToggleList;
	private JLabel busToggleLabel;
	private JButton repairButton;
	private JButton deleteBusButton;
	// bus assignment
	private JComboBox<String> busList;
	private JLabel busLabel;
	private JComboBox<String> routeList;
	private JLabel routeLabel;
	private JDatePickerImpl assignmentDatePicker;
	private JLabel assignmentDateLabel;
	private JButton assignButton;
	// schedule driver
	private JComboBox<String> driverList;
	private JLabel driverLabel;
	private JComboBox<String> assignmentList;
	private JLabel assignmentLabel;
	private JComboBox<String> shiftList;
	private JLabel shiftLabel;
	private JButton deleteAssignmentButton;
	private JButton scheduleButton;
	private JButton deleteScheduleButton;
	// daily overview
	private JDatePickerImpl overviewDatePicker;
	private JLabel overviewDateLabel; 
	private JTable overviewTable;
	private JScrollPane overviewScrollPane;
	// bus route visualization
	private JComboBox<String> routeList2;
	private JButton upBusStopsButton;
	private JButton downBusStopsButton;
	private BusRouteVisualizer busRouteVisualizer;

	// data elements
	private String error = null;
	// toggle sick status
	private HashMap<Integer, Integer> drivers;
	// toggle repairs status
	private HashMap<Integer, String> buses;
	// bus assignment
	private HashMap<Integer, String> availableBuses;
	private HashMap<Integer, TORoute> routes;
	// schedule driver
	private HashMap<Integer, Integer> availableDrivers;
	private HashMap<Integer, TORouteAssignment> assignments;
	private HashMap<Integer, String> shifts;
	// daily overview
	private DefaultTableModel overviewDtm;
	private String overviewColumnNames[] = {"Route", "Bus", "Shift", "Driver"};
	private static final int HEIGHT_OVERVIEW_TABLE = 200;
	// bus route visualization (the HashMap<Integer, Route> from bus assignment is also used for this)
	private static final int WIDTH_BUS_ROUTE_VISUALIZATION = 200;
	private static final int HEIGHT_BUS_ROUTE_VISUALIZATION = 200;

	/** Creates new form BtmsPage */
	public BtmsPage() {
		initComponents();
		refreshData();
		refreshBusRouteVisualizer();
	}

	/** This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		// elements for driver
		driverNameTextField = new JTextField();
		driverNameLabel = new JLabel();
		driverNameLabel.setText("Name:");
		addDriverButton = new JButton();
		addDriverButton.setText("Add Driver");
		driverToggleList = new JComboBox<String>(new String[0]);
		driverToggleLabel = new JLabel();
		driverToggleLabel.setText("Select Driver:");
		sickButton = new JButton();
		sickButton.setText("Toggle Sick");
		deleteDriverButton = new JButton();
		deleteDriverButton.setText("Delete");
		
		// elements for route
		routeNumberTextField = new JTextField();
		routeNumberLabel = new JLabel();
		routeNumberLabel.setText("Number:");
		routeAverageMinutesTextField = new JTextField();
		routeAverageMinutesLabel = new JLabel();
		routeAverageMinutesLabel.setText("Average Minutes:");
		routeNumberBusStopsTextField = new JTextField();
		routeNumberBusStopsLabel = new JLabel();
		routeNumberBusStopsLabel.setText("Bus Stops:");
		addRouteButton = new JButton();
		addRouteButton.setText("Add Route");
		updateRouteButton = new JButton();
		updateRouteButton.setText("Update");
		deleteRouteButton = new JButton();
		deleteRouteButton.setText("Delete");
		
		// elements for bus
		busLicencePlateTextField = new JTextField();
		busLicencePlateLabel = new JLabel();
		busLicencePlateLabel.setText("Licence Plate:");
		addBusButton = new JButton();
		addBusButton.setText("Add Bus");
		busToggleList = new JComboBox<String>(new String[0]);
		busToggleLabel = new JLabel();
		busToggleLabel.setText("Select Bus:");
		repairButton = new JButton();
		repairButton.setText("Toggle Repair");
		deleteBusButton = new JButton();
		deleteBusButton.setText("Delete");
		
		// elements for bus assignment
		busList = new JComboBox<String>(new String[0]);
		busLabel = new JLabel();
		busLabel.setText("Select Bus:");
		routeList = new JComboBox<String>(new String[0]);
		routeLabel = new JLabel();
		routeLabel.setText("Select Route:");
		
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		assignmentDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		assignmentDateLabel = new JLabel();
		assignmentDateLabel.setText("Date:");
		
		assignButton = new JButton();
		assignButton.setText("Assign");
		
		// elements for schedule driver
		driverList = new JComboBox<String>(new String[0]);
		driverLabel = new JLabel();
		driverLabel.setText("Select Driver:");
		assignmentList = new JComboBox<String>(new String[0]);
		assignmentLabel = new JLabel();
		assignmentLabel.setText("Select Assignment:");
		shiftList = new JComboBox<String>();
		shiftLabel = new JLabel();
		shiftLabel.setText("Select Shift");
		
		deleteAssignmentButton = new JButton();
		deleteAssignmentButton.setText("Delete Assignment");
		scheduleButton = new JButton();
		scheduleButton.setText("Schedule");
		deleteScheduleButton = new JButton();
		deleteScheduleButton.setText("Delete");
		
		// elements for daily overview
		SqlDateModel overviewModel = new SqlDateModel();
		LocalDate now = LocalDate.now();
		overviewModel.setDate(now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());
		overviewModel.setSelected(true);
		Properties pO = new Properties();
		pO.put("text.today", "Today");
		pO.put("text.month", "Month");
		pO.put("text.year", "Year");
		JDatePanelImpl overviewDatePanel = new JDatePanelImpl(overviewModel, pO);
		overviewDatePicker = new JDatePickerImpl(overviewDatePanel, new DateLabelFormatter());
		overviewDateLabel = new JLabel();
		overviewDateLabel.setText("Date for Overview:");
		
		overviewTable = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (!c.getBackground().equals(getSelectionBackground())) {
					Object obj = getModel().getValueAt(row, column);
					if (obj instanceof java.lang.String) {
						String str = (String)obj;
						c.setBackground(str.endsWith("sick)") ? Color.RED : str.endsWith("repair)") ? Color.YELLOW : Color.WHITE);
					}
					else {
						c.setBackground(Color.WHITE);
					}
				}
				return c;
			}
		};

		overviewScrollPane = new JScrollPane(overviewTable);
		this.add(overviewScrollPane);
		Dimension d = overviewTable.getPreferredSize();
		overviewScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
		overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		// elements for bus stop visualization
		routeList2 = new JComboBox<String>(new String[0]);
		routeList2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        refreshBusRouteVisualizer();
			}
		});
		busRouteVisualizer = new BusRouteVisualizer();
		busRouteVisualizer.setMinimumSize(new Dimension(WIDTH_BUS_ROUTE_VISUALIZATION, HEIGHT_BUS_ROUTE_VISUALIZATION));
		upBusStopsButton = new JButton();
		upBusStopsButton.setText("UP");
		downBusStopsButton = new JButton();
		downBusStopsButton.setText("DOWN");
		
		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Bus Transportation Management System");

		// listeners for driver
		addDriverButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addDriverButtonActionPerformed(evt);
			}
		});
		sickButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sickButtonActionPerformed(evt);
			}
		});
		deleteDriverButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteDriverButtonActionPerformed(evt);
			}
		});
		
		// listeners for route
		addRouteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addRouteButtonActionPerformed(evt);
			}
		});
		updateRouteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateRouteButtonActionPerformed(evt);
			}
		});
		deleteRouteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteRouteButtonActionPerformed(evt);
			}
		});

		// listeners for bus
		addBusButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addBusButtonActionPerformed(evt);
			}
		});
		repairButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				repairButtonActionPerformed(evt);
			}
		});
		deleteBusButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteBusButtonActionPerformed(evt);
			}
		});
		
		// listeners for bus assignment
		assignButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assignButtonActionPerformed(evt);
			}
		});
		
		// listeners for schedule driver
		deleteAssignmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteAssignmentButtonActionPerformed(evt);
			}
		});
		scheduleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				scheduleButtonActionPerformed(evt);
			}
		});
		deleteScheduleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteScheduleButtonActionPerformed(evt);
			}
		});
		
		// listeners for daily overview
		overviewDatePicker.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				refreshDailyOverview();;
			}
		});
		
		// listeners for bus stop visualization
		upBusStopsButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				upBusStopsButtonActionPerformed(evt);
			}
		});
		downBusStopsButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				downBusStopsButtonActionPerformed(evt);
			}
		});
		
		// horizontal line elements
		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineMiddle = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(errorMessage)
						.addComponent(horizontalLineTop)
						.addComponent(horizontalLineMiddle)
						.addComponent(horizontalLineBottom)
						.addComponent(overviewScrollPane)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(driverNameLabel)
										.addComponent(driverToggleLabel)
										.addComponent(busLabel)
										.addComponent(driverLabel))
								.addGroup(layout.createParallelGroup()
										.addComponent(driverNameTextField, 200, 200, 400)
										.addComponent(addDriverButton)
										.addComponent(driverToggleList)
										.addGroup(layout.createSequentialGroup()
												.addComponent(sickButton, 110, 110, 220)
												.addComponent(deleteDriverButton, 70, 70, 140))
										.addComponent(busList)
										.addComponent(driverList))
								.addGroup(layout.createParallelGroup()
										.addComponent(busLicencePlateLabel)
										.addComponent(busToggleLabel)
										.addComponent(routeLabel)
										.addComponent(assignmentLabel))
								.addGroup(layout.createParallelGroup()
										.addComponent(busLicencePlateTextField, 200, 200, 400)
										.addComponent(addBusButton)
										.addComponent(busToggleList)
										.addGroup(layout.createSequentialGroup()
												.addComponent(repairButton, 110, 110, 220)
												.addComponent(deleteBusButton, 70, 70, 140))
										.addComponent(routeList)
										.addComponent(assignmentList)
										.addComponent(deleteAssignmentButton))
								.addGroup(layout.createParallelGroup()
										.addComponent(routeNumberLabel)
										.addComponent(routeAverageMinutesLabel)
										.addComponent(routeNumberBusStopsLabel)
										.addComponent(assignmentDateLabel)
										.addComponent(shiftLabel)
										.addComponent(overviewDateLabel))
								.addGroup(layout.createParallelGroup()
										.addComponent(routeNumberTextField, 290, 290, 580)
										.addComponent(routeAverageMinutesTextField, 290, 290, 580)
										.addComponent(routeNumberBusStopsTextField, 290, 290, 580)
										.addGroup(layout.createSequentialGroup()
												.addComponent(addRouteButton, 100, 100, 200)
												.addComponent(updateRouteButton, 80, 80, 160)
												.addComponent(deleteRouteButton, 70, 70, 140))
										.addComponent(assignmentDatePicker)
										.addComponent(assignButton)
										.addComponent(shiftList)
										.addGroup(layout.createSequentialGroup()
												.addComponent(scheduleButton, 135, 135, 270)
												.addComponent(deleteScheduleButton, 135, 135, 270))
										.addComponent(overviewDatePicker))))
				.addGroup(layout.createParallelGroup()
						.addComponent(routeList2)
						.addGroup(layout.createSequentialGroup()
								.addComponent(upBusStopsButton)
								.addComponent(downBusStopsButton))
						.addComponent(busRouteVisualizer))
				);

		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {driverNameTextField, addDriverButton});
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {routeList, routeList2});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {driverNameTextField, addDriverButton, driverToggleList, busList, driverList});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
				{busLicencePlateTextField, addBusButton, busToggleList, routeList, assignmentList, deleteAssignmentButton, routeList2});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] 
				{routeNumberTextField, routeAverageMinutesTextField, routeNumberBusStopsTextField, assignmentDatePicker, assignButton, shiftList, overviewDatePicker});
		
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(errorMessage)
						.addGroup(layout.createParallelGroup()
								.addComponent(driverNameLabel)
								.addComponent(driverNameTextField)
								.addComponent(busLicencePlateLabel)
								.addComponent(busLicencePlateTextField)
								.addComponent(routeNumberLabel)
								.addComponent(routeNumberTextField))
						.addGroup(layout.createParallelGroup()
								.addComponent(addDriverButton)
								.addComponent(addBusButton)
								.addComponent(routeAverageMinutesLabel)
								.addComponent(routeAverageMinutesTextField))
						.addGroup(layout.createParallelGroup()
								.addComponent(driverToggleLabel)
								.addComponent(driverToggleList)
								.addComponent(busToggleLabel)
								.addComponent(busToggleList)
								.addComponent(routeNumberBusStopsLabel)
								.addComponent(routeNumberBusStopsTextField))
						.addGroup(layout.createParallelGroup()
								.addComponent(sickButton)
								.addComponent(deleteDriverButton)
								.addComponent(repairButton)
								.addComponent(deleteBusButton)
								.addComponent(addRouteButton)
								.addComponent(updateRouteButton)
								.addComponent(deleteRouteButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(horizontalLineTop))
						.addGroup(layout.createParallelGroup()
								.addComponent(busLabel)
								.addComponent(busList)
								.addComponent(routeLabel)
								.addComponent(routeList)
								.addComponent(assignmentDateLabel)
								.addComponent(assignmentDatePicker))
						.addComponent(assignButton)
						.addGroup(layout.createParallelGroup()
								.addComponent(horizontalLineMiddle))
						.addGroup(layout.createParallelGroup()
								.addComponent(driverLabel)
								.addComponent(driverList)
								.addComponent(assignmentLabel)
								.addComponent(assignmentList)
								.addComponent(shiftLabel)
								.addComponent(shiftList))
						.addGroup(layout.createParallelGroup()
								.addComponent(deleteAssignmentButton)
								.addComponent(scheduleButton)
								.addComponent(deleteScheduleButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(horizontalLineBottom))
						.addGroup(layout.createParallelGroup()
								.addComponent(overviewDateLabel)
								.addComponent(overviewDatePicker))
						.addComponent(overviewScrollPane))
				.addGroup(layout.createSequentialGroup()
						.addComponent(routeList2)
						.addGroup(layout.createParallelGroup()
								.addComponent(upBusStopsButton)
								.addComponent(downBusStopsButton))
						.addComponent(busRouteVisualizer))
				);
		
		pack();
	}

	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			// populate page with data
			// driver
			driverNameTextField.setText("");
			// route
			routeNumberTextField.setText("");
			routeAverageMinutesTextField.setText("");
			routeNumberBusStopsTextField.setText("");
			// bus
			busLicencePlateTextField.setText("");
			
			// toggle sick status
			drivers = new HashMap<Integer, Integer>();
			driverToggleList.removeAllItems();
			Integer index = 0;
			for (TODriver driver : BtmsController.getDrivers()) {
				drivers.put(index, driver.getId());
				driverToggleList.addItem("#" + driver.getId() + " " + driver.getName());
				index++;
			};
			driverToggleList.setSelectedIndex(-1);

			// toggle repair status
			buses = new HashMap<Integer, String>();
			busToggleList.removeAllItems();
			index = 0;
			for (TOBusVehicle bus : BtmsController.getBuses()) {
				buses.put(index, bus.getLicencePlate());
				busToggleList.addItem(bus.getLicencePlate());
				index++;
			};
			busToggleList.setSelectedIndex(-1);
		
			// bus assignment - bus
			availableBuses = new HashMap<Integer, String>();
			busList.removeAllItems();
			index = 0;
			for (TOBusVehicle bus : BtmsController.getAvailableBuses()) {
				availableBuses.put(index, bus.getLicencePlate());
				busList.addItem(bus.getLicencePlate());
				index++;
			};
			busList.setSelectedIndex(-1);
			// bus assignment - route (also combo box for bus route visualization)
			routes = new HashMap<Integer, TORoute>();
			routeList.removeAllItems();
			routeList2.removeAllItems();
			routeList2.addItem("no Route selected");
			index = 0;
			for (TORoute route : BtmsController.getRoutes()) {
				routes.put(index, route);
				routeList.addItem("#" + route.getNumber());
				routeList2.addItem("#" + route.getNumber());
				index++;
			};
			routeList.setSelectedIndex(-1);
			// bus assignment - date
			assignmentDatePicker.getModel().setValue(null);
			
			// schedule driver - driver
			availableDrivers = new HashMap<Integer, Integer>();
			driverList.removeAllItems();
			index = 0;
			for (TODriver driver : BtmsController.getAvailableDrivers()) {
				availableDrivers.put(index, driver.getId());
				driverList.addItem("#" + driver.getId() + " " + driver.getName());
				index++;
			};
			driverList.setSelectedIndex(-1);
			// schedule driver - assignment
			assignments = new HashMap<Integer, TORouteAssignment>();
			assignmentList.removeAllItems();
			index = 0;
			for (TORouteAssignment assignment : BtmsController.getAssignments()) {
				assignments.put(index, assignment);
				assignmentList.addItem(assignment.getDate() + ": Route #" + assignment.getNumber() + ", Bus " + assignment.getLicencePlate());
				index++;
			};
			assignmentList.setSelectedIndex(-1);
			// schedule driver - shift
			shifts = new HashMap<Integer, String>();
			shiftList.removeAllItems();
			index = 0;
			for (String shift : BtmsController.getShiftValues()) {
				shifts.put(index, shift);
				shiftList.addItem(shift);
				index++;
			};
			// selectedIndex of shiftList defaults to 0 - does not need to be set
		}
		
		// daily overview
		refreshDailyOverview();

		// this is needed because the size of the window changes depending on whether an error message is shown or not
		pack();
	}

	private void refreshDailyOverview() {
		overviewDtm = new DefaultTableModel(0, 0);
		overviewDtm.setColumnIdentifiers(overviewColumnNames);
		overviewTable.setModel(overviewDtm);
		if (overviewDatePicker.getModel().getValue() != null) {
			for (TODailyOverviewItem item : BtmsController.getDailyOverview((Date) overviewDatePicker.getModel().getValue())) {
				String busText = item.getLicencePlate();
				String shiftText = "---";
				String driverText = "---";
				if (item.isInRepairShop()) {
					busText = busText + " (in repair)";
				}
				if (item.getShift() != null) {
					shiftText = item.getShift();
				}
				if (item.getName() != null) {
					driverText = "#" + item.getId() + " " + item.getName();
					if (item.isSick()) {
						driverText = driverText + " (sick)";
					}
				}
				Object[] obj = {item.getNumber(), busText, shiftText, driverText};
				overviewDtm.addRow(obj);
			}
		}
		Dimension d = overviewTable.getPreferredSize();
		overviewScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
	}

	private void refreshBusRouteVisualizer() {
		busRouteVisualizer.setRoute(routes.get(routeList2.getSelectedIndex() - 1));
	}

	private void addDriverButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = null;
		
		// call the controller
		try {
			BtmsController.createDriver(driverNameTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// update visuals
		refreshData();
	}
	
	private void sickButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedSickDriver = driverToggleList.getSelectedIndex();
		if (selectedSickDriver < 0)
			error = "Driver needs to be selected to toggle sick status!";
		
		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.toggleSickStatus(drivers.get(selectedSickDriver));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		
		// update visuals
		refreshData();
	}
	
	private void deleteDriverButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedSickDriver = driverToggleList.getSelectedIndex();
		if (selectedSickDriver < 0)
			error = "Driver needs to be selected for deletion!";
		
		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.deleteDriver(drivers.get(selectedSickDriver));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		
		// update visuals
		refreshData();
	}

	private void addRouteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int routeNumber = 0;
		try {
			routeNumber = Integer.parseInt(routeNumberTextField.getText());
		}
		catch (NumberFormatException e) {
			error = "Route number needs to be a numerical value! ";
		}
		int averageMinutes = 0;
		try {
			averageMinutes = Integer.parseInt(routeAverageMinutesTextField.getText());
		}
		catch (NumberFormatException e) {
			error = error + "Average minutes needs to be a numerical value! ";
		}
		int numberBusStops = 0;
		try {
			numberBusStops = Integer.parseInt(routeNumberBusStopsTextField.getText());
		}
		catch (NumberFormatException e) {
			error = error + "Number of bus stops needs to be a numerical value!";
		}
		error.trim();
		
		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.createRoute(routeNumber, averageMinutes, numberBusStops);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		// update visuals
		refreshData();
	}
	
	private void deleteRouteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int routeNumber = 0;
		try {
			routeNumber = Integer.parseInt(routeNumberTextField.getText());
		}
		catch (NumberFormatException e) {
			error = "Route number needs to be a numerical value!";
		}
		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.deleteRoute(routeNumber);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		// update visuals
		refreshData();
	}
	
	private void updateRouteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int routeNumber = 0;
		try {
			routeNumber = Integer.parseInt(routeNumberTextField.getText());
		}
		catch (NumberFormatException e) {
			error = "Route number needs to be a numerical value! ";
		}
		int averageMinutes = 0;
		try {
			averageMinutes = Integer.parseInt(routeAverageMinutesTextField.getText());
		}
		catch (NumberFormatException e) {
			error = error + "Average minutes needs to be a numerical value! ";
		}
		int numberBusStops = 0;
		try {
			numberBusStops = Integer.parseInt(routeNumberBusStopsTextField.getText());
		}
		catch (NumberFormatException e) {
			error = error + "Number of bus stops needs to be a numerical value!";
		}
		error.trim();
		
		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.updateRoute(routeNumber, averageMinutes, numberBusStops);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		// update visuals
		refreshData();
	}
	
	private void addBusButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = null;

		// call the controller
		try {
			BtmsController.createBus(busLicencePlateTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
	
	private void repairButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedRepairBus = busToggleList.getSelectedIndex();
		if (selectedRepairBus < 0)
			error = "Bus needs to be selected to toggle repair status!";

		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.toggleRepairStatus(buses.get(selectedRepairBus));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		
		// update visuals
		refreshData();
	}
	
	private void deleteBusButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedRepairBus = busToggleList.getSelectedIndex();
		if (selectedRepairBus < 0)
			error = "Bus needs to be selected for deletion!";

		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.deleteBus(buses.get(selectedRepairBus));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		
		// update visuals
		refreshData();
	}
	
	private void assignButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedBus = busList.getSelectedIndex();
		if (selectedBus < 0)
			error = "Bus needs to be selected for assignment! ";
		int selectedRoute = routeList.getSelectedIndex();
		if (selectedRoute < 0)
			error = error + "Route needs to be selected for assignment! ";
		if (assignmentDatePicker.getModel().getValue() == null)
			error = error + "Date needs to entered for assignment!";
		error = error.trim();

		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.assign(availableBuses.get(selectedBus), routes.get(selectedRoute).getNumber(), (Date) assignmentDatePicker.getModel().getValue());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		// update visuals
		refreshData();			
	}
	
	private void scheduleButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedDriver = driverList.getSelectedIndex();
		if (selectedDriver < 0)
			error = "Driver needs to be selected for schedule! ";
		int selectedAssignment = assignmentList.getSelectedIndex();
		if (selectedAssignment < 0)
			error = error + "Assignment needs to be selected for schedule! ";
		int selectedShift = shiftList.getSelectedIndex();
		if (selectedShift < 0)
			error = error + "Shift needs to be selected for schedule! ";
		error = error.trim();

		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.schedule(availableDrivers.get(selectedDriver), assignments.get(selectedAssignment).getDate(), assignments.get(selectedAssignment).getNumber(), assignments.get(selectedAssignment).getLicencePlate(), shifts.get(selectedShift));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		// update visuals
		refreshData();			
	}

	private void deleteAssignmentButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedAssignment = assignmentList.getSelectedIndex();
		if (selectedAssignment < 0)
			error = error + "Assignment needs to be selected for schedule!";

		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.deleteAssignment(assignments.get(selectedAssignment).getLicencePlate(), assignments.get(selectedAssignment).getNumber(), assignments.get(selectedAssignment).getDate()); 
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		// update visuals
		refreshData();			
	}
	
	private void deleteScheduleButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		int selectedDriver = driverList.getSelectedIndex();
		if (selectedDriver < 0)
			error = "Driver needs to be selected to delete schedule! ";
		int selectedAssignment = assignmentList.getSelectedIndex();
		if (selectedAssignment < 0)
			error = error + "Assignment needs to be selected to delete schedule! ";
		int selectedShift = shiftList.getSelectedIndex();
		if (selectedShift < 0)
			error = error + "Shift needs to be selected to delete schedule! ";
		error = error.trim();
	
		if (error.length() == 0) {
			// call the controller
			try {
				BtmsController.deleteSchedule(availableDrivers.get(selectedDriver), assignments.get(selectedAssignment).getDate(), assignments.get(selectedAssignment).getNumber(), assignments.get(selectedAssignment).getLicencePlate(), shifts.get(selectedShift));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		// update visuals
		refreshData();			
	}
	
	private void upBusStopsButtonActionPerformed(java.awt.event.ActionEvent evt) {
		busRouteVisualizer.moveUp();
	}

	private void downBusStopsButtonActionPerformed(java.awt.event.ActionEvent evt) {
		busRouteVisualizer.moveDown();
	}
}
