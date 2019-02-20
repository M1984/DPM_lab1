/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.sql.Date;
import java.util.*;

// line 41 "../../../../../BTMS.ump"
public class RouteAssignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RouteAssignment Attributes
  private Date dates;

  //RouteAssignment Associations
  private List<Driver> drivers;
  private BusVehicle bus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RouteAssignment(Date aDates, BusVehicle aBus)
  {
    dates = aDates;
    drivers = new ArrayList<Driver>();
    boolean didAddBus = setBus(aBus);
    if (!didAddBus)
    {
      throw new RuntimeException("Unable to create routeAssignment due to bus");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDates(Date aDates)
  {
    boolean wasSet = false;
    dates = aDates;
    wasSet = true;
    return wasSet;
  }

  public Date getDates()
  {
    return dates;
  }
  /* Code from template association_GetMany */
  public Driver getDriver(int index)
  {
    Driver aDriver = drivers.get(index);
    return aDriver;
  }

  public List<Driver> getDrivers()
  {
    List<Driver> newDrivers = Collections.unmodifiableList(drivers);
    return newDrivers;
  }

  public int numberOfDrivers()
  {
    int number = drivers.size();
    return number;
  }

  public boolean hasDrivers()
  {
    boolean has = drivers.size() > 0;
    return has;
  }

  public int indexOfDriver(Driver aDriver)
  {
    int index = drivers.indexOf(aDriver);
    return index;
  }
  /* Code from template association_GetOne */
  public BusVehicle getBus()
  {
    return bus;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDrivers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Driver addDriver(String aName)
  {
    return new Driver(aName, this);
  }

  public boolean addDriver(Driver aDriver)
  {
    boolean wasAdded = false;
    if (drivers.contains(aDriver)) { return false; }
    RouteAssignment existingRouteAssignment = aDriver.getRouteAssignment();
    boolean isNewRouteAssignment = existingRouteAssignment != null && !this.equals(existingRouteAssignment);
    if (isNewRouteAssignment)
    {
      aDriver.setRouteAssignment(this);
    }
    else
    {
      drivers.add(aDriver);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDriver(Driver aDriver)
  {
    boolean wasRemoved = false;
    //Unable to remove aDriver, as it must always have a routeAssignment
    if (!this.equals(aDriver.getRouteAssignment()))
    {
      drivers.remove(aDriver);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDriverAt(Driver aDriver, int index)
  {  
    boolean wasAdded = false;
    if(addDriver(aDriver))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDrivers()) { index = numberOfDrivers() - 1; }
      drivers.remove(aDriver);
      drivers.add(index, aDriver);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDriverAt(Driver aDriver, int index)
  {
    boolean wasAdded = false;
    if(drivers.contains(aDriver))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDrivers()) { index = numberOfDrivers() - 1; }
      drivers.remove(aDriver);
      drivers.add(index, aDriver);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDriverAt(aDriver, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBus(BusVehicle aBus)
  {
    boolean wasSet = false;
    if (aBus == null)
    {
      return wasSet;
    }

    BusVehicle existingBus = bus;
    bus = aBus;
    if (existingBus != null && !existingBus.equals(aBus))
    {
      existingBus.removeRouteAssignment(this);
    }
    bus.addRouteAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=drivers.size(); i > 0; i--)
    {
      Driver aDriver = drivers.get(i - 1);
      aDriver.delete();
    }
    BusVehicle placeholderBus = bus;
    this.bus = null;
    if(placeholderBus != null)
    {
      placeholderBus.removeRouteAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dates" + "=" + (getDates() != null ? !getDates().equals(this)  ? getDates().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "bus = "+(getBus()!=null?Integer.toHexString(System.identityHashCode(getBus())):"null");
  }
}