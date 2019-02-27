package ca.mcgill.ecse.btms.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import ca.mcgill.ecse.btms.controller.BtmsController;
import ca.mcgill.ecse.btms.controller.TOBusStop;
import ca.mcgill.ecse.btms.controller.TORoute;

class BusRouteVisualizer extends JPanel {

	private static final long serialVersionUID = 5765666411683246454L;

	// UI elements
	private List<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
	private static final int LINEX = 30;
	private static final int LINETOPY = 30; 
	int lineHeight;
	private static final int RECTWIDTH = 40;
	private static final int RECTHEIGHT = 20;
	private static final int SPACING = 10;
	private static final int MAXNUMBEROFBUSSTOPSSHOWN = 10;

	// data elements
	private TORoute route;
	private HashMap<Rectangle2D, TOBusStop> busStops;
	private int selectedBusStop;
	private int firstVisibleBusStop;

	public BusRouteVisualizer() {
		super();
		init();
	}

	private void init() {
		route = null;
		busStops = new HashMap<Rectangle2D, TOBusStop>();
		selectedBusStop = 0;
		firstVisibleBusStop = 0;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				for (Rectangle2D rectangle : rectangles) {
					if (rectangle.contains(x, y)) {
						selectedBusStop = busStops.get(rectangle).getNumber();
						break;
					}
				}
				repaint();
			}
		});
	}

	public void setRoute(TORoute route) {
		this.route = route;
		busStops = new HashMap<Rectangle2D, TOBusStop>();
		selectedBusStop = 0;
		firstVisibleBusStop = 0;
		repaint();
	}

	public void moveUp() {
		if (firstVisibleBusStop > 0) {
			firstVisibleBusStop--;			
			repaint();
		}
	}

	public void moveDown() {
		if (route != null && firstVisibleBusStop < route.getNrBusStops() - MAXNUMBEROFBUSSTOPSSHOWN) {
			firstVisibleBusStop++;
			repaint();
		}
	}

	private void doDrawing(Graphics g) {
		if (route != null) {
			int number = route.getNrBusStops();
			if (number > MAXNUMBEROFBUSSTOPSSHOWN) {
				number = MAXNUMBEROFBUSSTOPSSHOWN;
				if (firstVisibleBusStop < route.getNrBusStops() - MAXNUMBEROFBUSSTOPSSHOWN)
					number++;
			}

			Graphics2D g2d = (Graphics2D) g.create();
			BasicStroke thickStroke = new BasicStroke(4);
			g2d.setStroke(thickStroke);
			lineHeight = (number - 1) * (RECTHEIGHT + SPACING);
			g2d.drawLine(LINEX, LINETOPY, LINEX, LINETOPY + lineHeight);

			BasicStroke thinStroke = new BasicStroke(2);
			g2d.setStroke(thinStroke);
			rectangles.clear();
			busStops.clear();
			int index = 0;
			int visibleIndex = 0;
			for (TOBusStop busStop : BtmsController.getBusStopsForRoute(route.getNumber())) {
				if (index >= firstVisibleBusStop && visibleIndex < MAXNUMBEROFBUSSTOPSSHOWN) {
					Rectangle2D rectangle = new Rectangle2D.Float(LINEX - RECTWIDTH / 2, LINETOPY - RECTHEIGHT / 2 + visibleIndex * (RECTHEIGHT + SPACING), RECTWIDTH, RECTHEIGHT);
					rectangles.add(rectangle);
					busStops.put(rectangle, busStop);

					g2d.setColor(Color.WHITE);
					g2d.fill(rectangle);
					g2d.setColor(Color.BLACK);
					g2d.draw(rectangle);
					g2d.drawString(new Integer(busStop.getNumber()).toString(), LINEX - RECTWIDTH / 4, LINETOPY + RECTHEIGHT / 4 + visibleIndex * (RECTHEIGHT + SPACING));

					if (selectedBusStop == busStop.getNumber()) {
						String busStopDetails = busStop.getMinutesFromStart() + "min from first stop";
						g2d.drawString(busStopDetails, LINEX + RECTWIDTH * 3 / 4, LINETOPY + RECTHEIGHT / 4 + visibleIndex * (RECTHEIGHT + SPACING));
					}

					visibleIndex++;
				}
				index++;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

}	