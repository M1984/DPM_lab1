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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TORoute(int aNumber)
  {
    number = aNumber;
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]";
  }
}