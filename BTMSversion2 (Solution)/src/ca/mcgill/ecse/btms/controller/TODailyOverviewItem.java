/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.controller;

// line 22 "../../../../../BTMSTransferObjects.ump"
public class TODailyOverviewItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TODailyOverviewItem Attributes
  private int number;
  private String licencePlate;
  private boolean inRepairShop;
  private String shift;
  private int id;
  private String name;
  private boolean sick;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TODailyOverviewItem(int aNumber, String aLicencePlate, boolean aInRepairShop, String aShift, int aId, String aName, boolean aSick)
  {
    number = aNumber;
    licencePlate = aLicencePlate;
    inRepairShop = aInRepairShop;
    shift = aShift;
    id = aId;
    name = aName;
    sick = aSick;
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

  public boolean setLicencePlate(String aLicencePlate)
  {
    boolean wasSet = false;
    licencePlate = aLicencePlate;
    wasSet = true;
    return wasSet;
  }

  public boolean setInRepairShop(boolean aInRepairShop)
  {
    boolean wasSet = false;
    inRepairShop = aInRepairShop;
    wasSet = true;
    return wasSet;
  }

  public boolean setShift(String aShift)
  {
    boolean wasSet = false;
    shift = aShift;
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

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setSick(boolean aSick)
  {
    boolean wasSet = false;
    sick = aSick;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public String getLicencePlate()
  {
    return licencePlate;
  }

  public boolean getInRepairShop()
  {
    return inRepairShop;
  }

  public String getShift()
  {
    return shift;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public boolean getSick()
  {
    return sick;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isInRepairShop()
  {
    return inRepairShop;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isSick()
  {
    return sick;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "licencePlate" + ":" + getLicencePlate()+ "," +
            "inRepairShop" + ":" + getInRepairShop()+ "," +
            "shift" + ":" + getShift()+ "," +
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "," +
            "sick" + ":" + getSick()+ "]";
  }
}