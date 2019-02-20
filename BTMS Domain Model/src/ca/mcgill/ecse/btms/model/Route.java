/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.util.*;

// line 35 "../../../../../BTMS.ump"
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
  private BusVehicle bus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Route(int aNumber, BusVehicle aBus)
  {
    if (!setNumber(aNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate number");
    }
    boolean didAddBus = setBus(aBus);
    if (!didAddBus)
    {
      throw new RuntimeException("Unable to create route due to bus");
    }
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
  public BusVehicle getBus()
  {
    return bus;
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
      existingBus.removeRoute(this);
    }
    bus.addRoute(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    routesByNumber.remove(getNumber());
    BusVehicle placeholderBus = bus;
    this.bus = null;
    if(placeholderBus != null)
    {
      placeholderBus.removeRoute(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bus = "+(getBus()!=null?Integer.toHexString(System.identityHashCode(getBus())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 38 ../../../../../BTMS.ump
  * -- 1 Route routes
  
}