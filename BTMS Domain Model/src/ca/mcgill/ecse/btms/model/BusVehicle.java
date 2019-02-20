/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.util.*;
import java.sql.Date;

// line 22 "../../../../../BTMS.ump"
public class BusVehicle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BusVehicle Attributes
  private String licencePlate;
  private boolean inRepairShop;

  //BusVehicle Associations
  private BTMS bTMS;
  private List<Route> routes;
  private List<RouteAssignment> routeAssignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BusVehicle(String aLicencePlate, boolean aInRepairShop, BTMS aBTMS)
  {
    // line 25 "../../../../../BTMS.ump"
    if (aLicencePlate == null || aLicencePlate.length() == 0) {
    	  throw new RuntimeException("The licence plate of a bus cannot be empty.");
    	}
    	if (aLicencePlate.length() > 10) {
    	  throw new RuntimeException("The licence plate of a bus cannot be longer than 10 characters.");
    	}
    // END OF UMPLE BEFORE INJECTION
    licencePlate = aLicencePlate;
    inRepairShop = aInRepairShop;
    boolean didAddBTMS = setBTMS(aBTMS);
    if (!didAddBTMS)
    {
      throw new RuntimeException("Unable to create vehicle due to bTMS");
    }
    routes = new ArrayList<Route>();
    routeAssignments = new ArrayList<RouteAssignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLicencePlate(String aLicencePlate)
  {
    boolean wasSet = false;
    // line 25 "../../../../../BTMS.ump"
    if (aLicencePlate == null || aLicencePlate.length() == 0) {
    	  throw new RuntimeException("The licence plate of a bus cannot be empty.");
    	}
    	if (aLicencePlate.length() > 10) {
    	  throw new RuntimeException("The licence plate of a bus cannot be longer than 10 characters.");
    	}
    // END OF UMPLE BEFORE INJECTION
    licencePlate = aLicencePlate;
    wasSet = true;
    return wasSet;
  }

  public boolean setInRepairShop(boolean aInRepairShop)
  {
    boolean wasSet = false;
    inRepairShop = aInRepairShop;
    wasSet = true;
    return wasSet;
  }

  public String getLicencePlate()
  {
    return licencePlate;
  }

  public boolean getInRepairShop()
  {
    return inRepairShop;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isInRepairShop()
  {
    return inRepairShop;
  }
  /* Code from template association_GetOne */
  public BTMS getBTMS()
  {
    return bTMS;
  }
  /* Code from template association_GetMany */
  public Route getRoute(int index)
  {
    Route aRoute = routes.get(index);
    return aRoute;
  }

  public List<Route> getRoutes()
  {
    List<Route> newRoutes = Collections.unmodifiableList(routes);
    return newRoutes;
  }

  public int numberOfRoutes()
  {
    int number = routes.size();
    return number;
  }

  public boolean hasRoutes()
  {
    boolean has = routes.size() > 0;
    return has;
  }

  public int indexOfRoute(Route aRoute)
  {
    int index = routes.indexOf(aRoute);
    return index;
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
      existingBTMS.removeVehicle(this);
    }
    bTMS.addVehicle(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRoutes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Route addRoute(int aNumber)
  {
    return new Route(aNumber, this);
  }

  public boolean addRoute(Route aRoute)
  {
    boolean wasAdded = false;
    if (routes.contains(aRoute)) { return false; }
    BusVehicle existingBus = aRoute.getBus();
    boolean isNewBus = existingBus != null && !this.equals(existingBus);
    if (isNewBus)
    {
      aRoute.setBus(this);
    }
    else
    {
      routes.add(aRoute);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRoute(Route aRoute)
  {
    boolean wasRemoved = false;
    //Unable to remove aRoute, as it must always have a bus
    if (!this.equals(aRoute.getBus()))
    {
      routes.remove(aRoute);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRouteAt(Route aRoute, int index)
  {  
    boolean wasAdded = false;
    if(addRoute(aRoute))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoutes()) { index = numberOfRoutes() - 1; }
      routes.remove(aRoute);
      routes.add(index, aRoute);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRouteAt(Route aRoute, int index)
  {
    boolean wasAdded = false;
    if(routes.contains(aRoute))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoutes()) { index = numberOfRoutes() - 1; }
      routes.remove(aRoute);
      routes.add(index, aRoute);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRouteAt(aRoute, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRouteAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public RouteAssignment addRouteAssignment(Date aDates)
  {
    return new RouteAssignment(aDates, this);
  }

  public boolean addRouteAssignment(RouteAssignment aRouteAssignment)
  {
    boolean wasAdded = false;
    if (routeAssignments.contains(aRouteAssignment)) { return false; }
    BusVehicle existingBus = aRouteAssignment.getBus();
    boolean isNewBus = existingBus != null && !this.equals(existingBus);
    if (isNewBus)
    {
      aRouteAssignment.setBus(this);
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
    //Unable to remove aRouteAssignment, as it must always have a bus
    if (!this.equals(aRouteAssignment.getBus()))
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
    BTMS placeholderBTMS = bTMS;
    this.bTMS = null;
    if(placeholderBTMS != null)
    {
      placeholderBTMS.removeVehicle(this);
    }
    for(int i=routes.size(); i > 0; i--)
    {
      Route aRoute = routes.get(i - 1);
      aRoute.delete();
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
            "licencePlate" + ":" + getLicencePlate()+ "," +
            "inRepairShop" + ":" + getInRepairShop()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bTMS = "+(getBTMS()!=null?Integer.toHexString(System.identityHashCode(getBTMS())):"null");
  }
}