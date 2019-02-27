/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;

// line 53 "../../../../../BTMS.ump"
public class BusStop
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BusStop Attributes
  private int number;

  //BusStop Associations
  private Route route;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BusStop(int aNumber, Route aRoute)
  {
    number = aNumber;
    boolean didAddRoute = setRoute(aRoute);
    if (!didAddRoute)
    {
      throw new RuntimeException("Unable to create busStop due to route");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }
  /* Code from template association_GetOne */
  public Route getRoute()
  {
    return route;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRoute(Route aRoute)
  {
    boolean wasSet = false;
    if (aRoute == null)
    {
      return wasSet;
    }

    Route existingRoute = route;
    route = aRoute;
    if (existingRoute != null && !existingRoute.equals(aRoute))
    {
      existingRoute.removeBusStop(this);
    }
    route.addBusStop(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Route placeholderRoute = route;
    this.route = null;
    if(placeholderRoute != null)
    {
      placeholderRoute.removeBusStop(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "route = "+(getRoute()!=null?Integer.toHexString(System.identityHashCode(getRoute())):"null");
  }
}