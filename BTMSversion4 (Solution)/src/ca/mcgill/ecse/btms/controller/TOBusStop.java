/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.controller;

// line 12 "../../../../../BTMSTransferObjects.ump"
public class TOBusStop
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBusStop Attributes
  private int number;
  private int minutesFromStart;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBusStop(int aNumber, int aMinutesFromStart)
  {
    number = aNumber;
    minutesFromStart = aMinutesFromStart;
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

  public boolean setMinutesFromStart(int aMinutesFromStart)
  {
    boolean wasSet = false;
    minutesFromStart = aMinutesFromStart;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public int getMinutesFromStart()
  {
    return minutesFromStart;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "minutesFromStart" + ":" + getMinutesFromStart()+ "]";
  }
}