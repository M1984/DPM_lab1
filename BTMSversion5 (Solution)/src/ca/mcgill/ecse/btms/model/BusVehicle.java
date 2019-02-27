/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.io.Serializable;
import java.util.*;
import java.sql.Date;

// line 16 "../../../../../BTMSPersistence.ump"
// line 24 "../../../../../BTMS.ump"
public class BusVehicle implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, BusVehicle> busvehiclesByLicencePlate = new HashMap<String, BusVehicle>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BusVehicle Attributes
  private String licencePlate;
  private boolean inRepairShop;

  //BusVehicle Associations
  private BTMS bTMS;
  private List<RouteAssignment> routeAssignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BusVehicle(String aLicencePlate, boolean aInRepairShop, BTMS aBTMS)
  {
    // line 27 "../../../../../BTMS.ump"
    if (aLicencePlate == null || aLicencePlate.length() == 0) {
    	  throw new RuntimeException("The licence plate of a bus cannot be empty.");
    	}
    	if (aLicencePlate.length() > 10) {
    	  throw new RuntimeException("The licence plate of a bus cannot be longer than 10 characters.");
    	}
    // END OF UMPLE BEFORE INJECTION
    inRepairShop = aInRepairShop;
    if (!setLicencePlate(aLicencePlate))
    {
      throw new RuntimeException("Cannot create due to duplicate licencePlate");
    }
    boolean didAddBTMS = setBTMS(aBTMS);
    if (!didAddBTMS)
    {
      throw new RuntimeException("Unable to create vehicle due to bTMS");
    }
    routeAssignments = new ArrayList<RouteAssignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLicencePlate(String aLicencePlate)
  {
    boolean wasSet = false;
    // line 27 "../../../../../BTMS.ump"
    if (aLicencePlate == null || aLicencePlate.length() == 0) {
    	  throw new RuntimeException("The licence plate of a bus cannot be empty.");
    	}
    	if (aLicencePlate.length() > 10) {
    	  throw new RuntimeException("The licence plate of a bus cannot be longer than 10 characters.");
    	}
    // END OF UMPLE BEFORE INJECTION
    String anOldLicencePlate = getLicencePlate();
    if (hasWithLicencePlate(aLicencePlate)) {
      return wasSet;
    }
    licencePlate = aLicencePlate;
    wasSet = true;
    if (anOldLicencePlate != null) {
      busvehiclesByLicencePlate.remove(anOldLicencePlate);
    }
    busvehiclesByLicencePlate.put(aLicencePlate, this);
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
  /* Code from template attribute_GetUnique */
  public static BusVehicle getWithLicencePlate(String aLicencePlate)
  {
    return busvehiclesByLicencePlate.get(aLicencePlate);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithLicencePlate(String aLicencePlate)
  {
    return getWithLicencePlate(aLicencePlate) != null;
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
  public static int minimumNumberOfRouteAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public RouteAssignment addRouteAssignment(Date aDate, Route aRoute, BTMS aBTMS)
  {
    return new RouteAssignment(aDate, this, aRoute, aBTMS);
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
    busvehiclesByLicencePlate.remove(getLicencePlate());
    BTMS placeholderBTMS = bTMS;
    this.bTMS = null;
    if(placeholderBTMS != null)
    {
      placeholderBTMS.removeVehicle(this);
    }
    for(int i=routeAssignments.size(); i > 0; i--)
    {
      RouteAssignment aRouteAssignment = routeAssignments.get(i - 1);
      aRouteAssignment.delete();
    }
  }

  // line 22 "../../../../../BTMSPersistence.ump"
   public static  void reinitializeUniqueLicencePlate(List<BusVehicle> buses){
    busvehiclesByLicencePlate = new HashMap<String, BusVehicle>();
    for (BusVehicle bus : buses) {
      busvehiclesByLicencePlate.put(bus.getLicencePlate(), bus);
    }
  }

  // line 37 "../../../../../BTMS.ump"
   public RouteAssignment getAssignmentOnDate(Date date){
    RouteAssignment result = null;
    for (RouteAssignment assignment : getRouteAssignments()) {
      if (assignment.getDate().equals(date)) {
        result = assignment;
        break;
      }
    }
    return result;
  }


  public String toString()
  {
    return super.toString() + "["+
            "licencePlate" + ":" + getLicencePlate()+ "," +
            "inRepairShop" + ":" + getInRepairShop()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bTMS = "+(getBTMS()!=null?Integer.toHexString(System.identityHashCode(getBTMS())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 19 "../../../../../BTMSPersistence.ump"
  private static final long serialVersionUID = 2315072607928790501L ;

  
}