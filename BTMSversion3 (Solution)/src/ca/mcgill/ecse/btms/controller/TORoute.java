/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.controller;

// line 7 "../../../../../BTMSTransferObjects.ump"
public class TORoute
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TORoute Attributes
  private int number;
  private int nrBusStops;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TORoute(int aNumber, int aNrBusStops)
  {
    number = aNumber;
    nrBusStops = aNrBusStops;
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

  public boolean setNrBusStops(int aNrBusStops)
  {
    boolean wasSet = false;
    nrBusStops = aNrBusStops;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public int getNrBusStops()
  {
    return nrBusStops;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "nrBusStops" + ":" + getNrBusStops()+ "]";
  }
}