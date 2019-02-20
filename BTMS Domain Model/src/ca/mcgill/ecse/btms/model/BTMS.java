/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.util.*;

// line 3 "../../../../../BTMS.ump"
public class BTMS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BTMS Associations
  private List<BusVehicle> vehicles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BTMS()
  {
    vehicles = new ArrayList<BusVehicle>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public BusVehicle getVehicle(int index)
  {
    BusVehicle aVehicle = vehicles.get(index);
    return aVehicle;
  }

  public List<BusVehicle> getVehicles()
  {
    List<BusVehicle> newVehicles = Collections.unmodifiableList(vehicles);
    return newVehicles;
  }

  public int numberOfVehicles()
  {
    int number = vehicles.size();
    return number;
  }

  public boolean hasVehicles()
  {
    boolean has = vehicles.size() > 0;
    return has;
  }

  public int indexOfVehicle(BusVehicle aVehicle)
  {
    int index = vehicles.indexOf(aVehicle);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfVehicles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BusVehicle addVehicle(String aLicencePlate, boolean aInRepairShop)
  {
    return new BusVehicle(aLicencePlate, aInRepairShop, this);
  }

  public boolean addVehicle(BusVehicle aVehicle)
  {
    boolean wasAdded = false;
    if (vehicles.contains(aVehicle)) { return false; }
    BTMS existingBTMS = aVehicle.getBTMS();
    boolean isNewBTMS = existingBTMS != null && !this.equals(existingBTMS);
    if (isNewBTMS)
    {
      aVehicle.setBTMS(this);
    }
    else
    {
      vehicles.add(aVehicle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeVehicle(BusVehicle aVehicle)
  {
    boolean wasRemoved = false;
    //Unable to remove aVehicle, as it must always have a bTMS
    if (!this.equals(aVehicle.getBTMS()))
    {
      vehicles.remove(aVehicle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addVehicleAt(BusVehicle aVehicle, int index)
  {  
    boolean wasAdded = false;
    if(addVehicle(aVehicle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfVehicles()) { index = numberOfVehicles() - 1; }
      vehicles.remove(aVehicle);
      vehicles.add(index, aVehicle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveVehicleAt(BusVehicle aVehicle, int index)
  {
    boolean wasAdded = false;
    if(vehicles.contains(aVehicle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfVehicles()) { index = numberOfVehicles() - 1; }
      vehicles.remove(aVehicle);
      vehicles.add(index, aVehicle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addVehicleAt(aVehicle, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (vehicles.size() > 0)
    {
      BusVehicle aVehicle = vehicles.get(vehicles.size() - 1);
      aVehicle.delete();
      vehicles.remove(aVehicle);
    }
    
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 5 "../../../../../BTMS.ump"
  1 <@>- * Route routes 
  1 <@>- * RouteAssignmeant assignment  
  1 <@>- * Driver drivers 
  1 <@>- * DriverSchedule schedule 
  
  public java.util.Date getCurrentDate () 
  {
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    java.util.Date date = cal.getTime();
    return date;
  }

  
}