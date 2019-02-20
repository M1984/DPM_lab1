/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.util.*;

// line 47 "../../../../../BTMS.ump"
public class Driver
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Driver Attributes
  private String name;

  //Driver Associations
  private RouteAssignment routeAssignment;
  private List<DriverSchedule> driverSchedules;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Driver(String aName, RouteAssignment aRouteAssignment)
  {
    // line 50 "../../../../../BTMS.ump"
    if (aName == null || aName.length()==0 ){
      	throw new RuntimeException ("The name of a driver cannot be emoty")
      	}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    boolean didAddRouteAssignment = setRouteAssignment(aRouteAssignment);
    if (!didAddRouteAssignment)
    {
      throw new RuntimeException("Unable to create driver due to routeAssignment");
    }
    driverSchedules = new ArrayList<DriverSchedule>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 50 "../../../../../BTMS.ump"
    if (aName == null || aName.length()==0 ){
      	throw new RuntimeException ("The name of a driver cannot be emoty")
      	}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne */
  public RouteAssignment getRouteAssignment()
  {
    return routeAssignment;
  }
  /* Code from template association_GetMany */
  public DriverSchedule getDriverSchedule(int index)
  {
    DriverSchedule aDriverSchedule = driverSchedules.get(index);
    return aDriverSchedule;
  }

  public List<DriverSchedule> getDriverSchedules()
  {
    List<DriverSchedule> newDriverSchedules = Collections.unmodifiableList(driverSchedules);
    return newDriverSchedules;
  }

  public int numberOfDriverSchedules()
  {
    int number = driverSchedules.size();
    return number;
  }

  public boolean hasDriverSchedules()
  {
    boolean has = driverSchedules.size() > 0;
    return has;
  }

  public int indexOfDriverSchedule(DriverSchedule aDriverSchedule)
  {
    int index = driverSchedules.indexOf(aDriverSchedule);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRouteAssignment(RouteAssignment aRouteAssignment)
  {
    boolean wasSet = false;
    if (aRouteAssignment == null)
    {
      return wasSet;
    }

    RouteAssignment existingRouteAssignment = routeAssignment;
    routeAssignment = aRouteAssignment;
    if (existingRouteAssignment != null && !existingRouteAssignment.equals(aRouteAssignment))
    {
      existingRouteAssignment.removeDriver(this);
    }
    routeAssignment.addDriver(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDriverSchedules()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public DriverSchedule addDriverSchedule()
  {
    return new DriverSchedule(this);
  }

  public boolean addDriverSchedule(DriverSchedule aDriverSchedule)
  {
    boolean wasAdded = false;
    if (driverSchedules.contains(aDriverSchedule)) { return false; }
    Driver existingDriver = aDriverSchedule.getDriver();
    boolean isNewDriver = existingDriver != null && !this.equals(existingDriver);
    if (isNewDriver)
    {
      aDriverSchedule.setDriver(this);
    }
    else
    {
      driverSchedules.add(aDriverSchedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDriverSchedule(DriverSchedule aDriverSchedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aDriverSchedule, as it must always have a driver
    if (!this.equals(aDriverSchedule.getDriver()))
    {
      driverSchedules.remove(aDriverSchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDriverScheduleAt(DriverSchedule aDriverSchedule, int index)
  {  
    boolean wasAdded = false;
    if(addDriverSchedule(aDriverSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDriverSchedules()) { index = numberOfDriverSchedules() - 1; }
      driverSchedules.remove(aDriverSchedule);
      driverSchedules.add(index, aDriverSchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDriverScheduleAt(DriverSchedule aDriverSchedule, int index)
  {
    boolean wasAdded = false;
    if(driverSchedules.contains(aDriverSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDriverSchedules()) { index = numberOfDriverSchedules() - 1; }
      driverSchedules.remove(aDriverSchedule);
      driverSchedules.add(index, aDriverSchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDriverScheduleAt(aDriverSchedule, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    RouteAssignment placeholderRouteAssignment = routeAssignment;
    this.routeAssignment = null;
    if(placeholderRouteAssignment != null)
    {
      placeholderRouteAssignment.removeDriver(this);
    }
    for(int i=driverSchedules.size(); i > 0; i--)
    {
      DriverSchedule aDriverSchedule = driverSchedules.get(i - 1);
      aDriverSchedule.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "routeAssignment = "+(getRouteAssignment()!=null?Integer.toHexString(System.identityHashCode(getRouteAssignment())):"null");
  }
}