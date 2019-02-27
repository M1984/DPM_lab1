/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.controller;

// line 23 "../../../../../BTMSTransferObjects.ump"
public class TODriver
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TODriver Attributes
  private String name;
  private int id;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TODriver(String aName, int aId)
  {
    name = aName;
    id = aId;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getId()
  {
    return id;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "id" + ":" + getId()+ "]";
  }
}