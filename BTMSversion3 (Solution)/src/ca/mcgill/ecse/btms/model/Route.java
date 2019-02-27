/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.util.*;
import java.sql.Date;

// line 47 "../../../../../BTMS.ump"
public class Route
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Route> routesByNumber = new HashMap<Integer, Route>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Route Attributes
  private int number;
  private int averageMinutesToNextBusStop;

  //Route Associations
  private List<BusStop> busStops;
  private BTMS bTMS;
  private List<RouteAssignment> routeAssignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Route(int aNumber, int aAverageMinutesToNextBusStop, BTMS aBTMS)
  {
    averageMinutesToNextBusStop = aAverageMinutesToNextBusStop;
    if (!setNumber(aNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate number");
    }
    busStops = new ArrayList<BusStop>();
    boolean didAddBTMS = setBTMS(aBTMS);
    if (!didAddBTMS)
    {
      throw new RuntimeException("Unable to create route due to bTMS");
    }
    routeAssignments = new ArrayList<RouteAssignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    Integer anOldNumber = getNumber();
    if (hasWithNumber(aNumber)) {
      return wasSet;
    }
    number = aNumber;
    wasSet = true;
    if (anOldNumber != null) {
      routesByNumber.remove(anOldNumber);
    }
    routesByNumber.put(aNumber, this);
    return wasSet;
  }

  public boolean setAverageMinutesToNextBusStop(int aAverageMinutesToNextBusStop)
  {
    boolean wasSet = false;
    averageMinutesToNextBusStop = aAverageMinutesToNextBusStop;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }
  /* Code from template attribute_GetUnique */
  public static Route getWithNumber(int aNumber)
  {
    return routesByNumber.get(aNumber);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithNumber(int aNumber)
  {
    return getWithNumber(aNumber) != null;
  }

  public int getAverageMinutesToNextBusStop()
  {
    return averageMinutesToNextBusStop;
  }
  /* Code from template association_GetMany */
  public BusStop getBusStop(int index)
  {
    BusStop aBusStop = busStops.get(index);
    return aBusStop;
  }

  public List<BusStop> getBusStops()
  {
    List<BusStop> newBusStops = Collections.unmodifiableList(busStops);
    return newBusStops;
  }

  public int numberOfBusStops()
  {
    int number = busStops.size();
    return number;
  }

  public boolean hasBusStops()
  {
    boolean has = busStops.size() > 0;
    return has;
  }

  public int indexOfBusStop(BusStop aBusStop)
  {
    int index = busStops.indexOf(aBusStop);
    return index;
  }
  /* Code from template association_GetOne */
  public BTMS getBTMS()
  {
    return bTMS;
  }
  /* Code from template association_GetMany */
  public RouteAssignment getRouteAssignment(int index)
  {
    RouteAssignment aRouteAssignment = routeAssignments.get(index);
    return aRouteAssignment;
  }

  public List<RouteAssignment> getRouteAssignments()
  {
    List<RouteAssignment> newRouteAssignments = Collections.unmodifiableList(routeAssignments);
    return newRouteAssignments;
  }

  public int numberOfRouteAssignments()
  {
    int number = routeAssignments.size();
    return number;
  }

  public boolean hasRouteAssignments()
  {
    boolean has = routeAssignments.size() > 0;
    return has;
  }

  public int indexOfRouteAssignment(RouteAssignment aRouteAssignment)
  {
    int index = routeAssignments.indexOf(aRouteAssignment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBusStops()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BusStop addBusStop(int aNumber)
  {
    return new BusStop(aNumber, this);
  }

  public boolean addBusStop(BusStop aBusStop)
  {
    boolean wasAdded = false;
    if (busStops.contains(aBusStop)) { return false; }
    Route existingRoute = aBusStop.getRoute();
    boolean isNewRoute = existingRoute != null && !this.equals(existingRoute);
    if (isNewRoute)
    {
      aBusStop.setRoute(this);
    }
    else
    {
      busStops.add(aBusStop);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBusStop(BusStop aBusStop)
  {
    boolean wasRemoved = false;
    //Unable to remove aBusStop, as it must always have a route
    if (!this.equals(aBusStop.getRoute()))
    {
      busStops.remove(aBusStop);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBusStopAt(BusStop aBusStop, int index)
  {  
    boolean wasAdded = false;
    if(addBusStop(aBusStop))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusStops()) { index = numberOfBusStops() - 1; }
      busStops.remove(aBusStop);
      busStops.add(index, aBusStop);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBusStopAt(BusStop aBusStop, int index)
  {
    boolean wasAdded = false;
    if(busStops.contains(aBusStop))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusStops()) { index = numberOfBusStops() - 1; }
      busStops.remove(aBusStop);
      busStops.add(index, aBusStop);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBusStopAt(aBusStop, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBTMS(BTMS aBTMS)
  {
    boolean wasSet = false;
    if (aBTMS == null)
    {
      return wasSet;
    }

    BTMS existingBTMS = bTMS;
    bTMS = aBTMS;
    if (existingBTMS != null && !existingBTMS.equals(aBTMS))
    {
      existingBTMS.removeRoute(this);
    }
    bTMS.addRoute(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRouteAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public RouteAssignment addRouteAssignment(Date aDate, BusVehicle aBus, BTMS aBTMS)
  {
    return new RouteAssignment(aDate, aBus, this, aBTMS);
  }

  public boolean addRouteAssignment(RouteAssignment aRouteAssignment)
  {
    boolean wasAdded = false;
    if (routeAssignments.contains(aRouteAssignment)) { return false; }
    Route existingRoute = aRouteAssignment.getRoute();
    boolean isNewRoute = existingRoute != null && !this.equals(existingRoute);
    if (isNewRoute)
    {
      aRouteAssignment.setRoute(this);
    }
    else
    {
      routeAssignments.add(aRouteAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRouteAssignment(RouteAssignment aRouteAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aRouteAssignment, as it must always have a route
    if (!this.equals(aRouteAssignment.getRoute()))
    {
      routeAssignments.remove(aRouteAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRouteAssignmentAt(RouteAssignment aRouteAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addRouteAssignment(aRouteAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRouteAssignments()) { index = numberOfRouteAssignments() - 1; }
      routeAssignments.remove(aRouteAssignment);
      routeAssignments.add(index, aRouteAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRouteAssignmentAt(RouteAssignment aRouteAssignment, int index)
  {
    boolean wasAdded = false;
    if(routeAssignments.contains(aRouteAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRouteAssignments()) { index = numberOfRouteAssignments() - 1; }
      routeAssignments.remove(aRouteAssignment);
      routeAssignments.add(index, aRouteAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRouteAssignmentAt(aRouteAssignment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    routesByNumber.remove(getNumber());
    while (busStops.size() > 0)
    {
      BusStop aBusStop = busStops.get(busStops.size() - 1);
      aBusStop.delete();
      busStops.remove(aBusStop);
    }
    
    BTMS placeholderBTMS = bTMS;
    this.bTMS = null;
    if(placeholderBTMS != null)
    {
      placeholderBTMS.removeRoute(this);
    }
    for(int i=routeAssignments.size(); i > 0; i--)
    {
      RouteAssignment aRouteAssignment = routeAssignments.get(i - 1);
      aRouteAssignment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "averageMinutesToNextBusStop" + ":" + getAverageMinutesToNextBusStop()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bTMS = "+(getBTMS()!=null?Integer.toHexString(System.identityHashCode(getBTMS())):"null");
  }
}