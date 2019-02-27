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

  //Route Associations
  private BTMS bTMS;
  private List<RouteAssignment> routeAssignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Route(int aNumber, BTMS aBTMS)
  {
    if (!setNumber(aNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate number");
    }
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
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bTMS = "+(getBTMS()!=null?Integer.toHexString(System.identityHashCode(getBTMS())):"null");
  }
}