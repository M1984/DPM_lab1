/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.controller;
import java.sql.Date;

// line 11 "../../../../../BTMSTransferObjects.ump"
public class TORouteAssignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TORouteAssignment Attributes
  private Date date;
  private String licencePlate;
  private int number;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TORouteAssignment(Date aDate, String aLicencePlate, int aNumber)
  {
    date = aDate;
    licencePlate = aLicencePlate;
    number = aNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setLicencePlate(String aLicencePlate)
  {
    boolean wasSet = false;
    licencePlate = aLicencePlate;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public String getLicencePlate()
  {
    return licencePlate;
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
            "licencePlate" + ":" + getLicencePlate()+ "," +
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}