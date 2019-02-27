/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.controller;

// line 3 "../../../../../BTMSTransferObjects.ump"
public class TOBusVehicle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBusVehicle Attributes
  private String licencePlate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBusVehicle(String aLicencePlate)
  {
    licencePlate = aLicencePlate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLicencePlate(String aLicencePlate)
  {
    boolean wasSet = false;
    licencePlate = aLicencePlate;
    wasSet = true;
    return wasSet;
  }

  public String getLicencePlate()
  {
    return licencePlate;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "licencePlate" + ":" + getLicencePlate()+ "]";
  }
}