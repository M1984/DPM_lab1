/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;

// line 74 "../../../../../BTMS.ump"
public class DriverSchedule
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Shift { Morning, Afternoon, Night }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DriverSchedule Attributes
  private Shift shift;

  //DriverSchedule Associations
  private Driver driver;
  private RouteAssignment assignment;
  private BTMS bTMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DriverSchedule(Shift aShift, Driver aDriver, RouteAssignment aAssignment, BTMS aBTMS)
  {
    shift = aShift;
    boolean didAddDriver = setDriver(aDriver);
    if (!didAddDriver)
    {
      throw new RuntimeException("Unable to create driverSchedule due to driver");
    }
    boolean didAddAssignment = setAssignment(aAssignment);
    if (!didAddAssignment)
    {
      throw new RuntimeException("Unable to create driverSchedule due to assignment");
    }
    boolean didAddBTMS = setBTMS(aBTMS);
    if (!didAddBTMS)
    {
      throw new RuntimeException("Unable to create schedule due to bTMS");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setShift(Shift aShift)
  {
    boolean wasSet = false;
    shift = aShift;
    wasSet = true;
    return wasSet;
  }

  public Shift getShift()
  {
    return shift;
  }
  /* Code from template association_GetOne */
  public Driver getDriver()
  {
    return driver;
  }
  /* Code from template association_GetOne */
  public RouteAssignment getAssignment()
  {
    return assignment;
  }
  /* Code from template association_GetOne */
  public BTMS getBTMS()
  {
    return bTMS;
  }
  /* Code from template association_SetOneToMany */
  public boolean setDriver(Driver aDriver)
  {
    boolean wasSet = false;
    if (aDriver == null)
    {
      return wasSet;
    }

    Driver existingDriver = driver;
    driver = aDriver;
    if (existingDriver != null && !existingDriver.equals(aDriver))
    {
      existingDriver.removeDriverSchedule(this);
    }
    driver.addDriverSchedule(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAssignment(RouteAssignment aAssignment)
  {
    boolean wasSet = false;
    if (aAssignment == null)
    {
      return wasSet;
    }

    RouteAssignment existingAssignment = assignment;
    assignment = aAssignment;
    if (existingAssignment != null && !existingAssignment.equals(aAssignment))
    {
      existingAssignment.removeDriverSchedule(this);
    }
    assignment.addDriverSchedule(this);
    wasSet = true;
    return wasSet;
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
      existingBTMS.removeSchedule(this);
    }
    bTMS.addSchedule(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Driver placeholderDriver = driver;
    this.driver = null;
    if(placeholderDriver != null)
    {
      placeholderDriver.removeDriverSchedule(this);
    }
    RouteAssignment placeholderAssignment = assignment;
    this.assignment = null;
    if(placeholderAssignment != null)
    {
      placeholderAssignment.removeDriverSchedule(this);
    }
    BTMS placeholderBTMS = bTMS;
    this.bTMS = null;
    if(placeholderBTMS != null)
    {
      placeholderBTMS.removeSchedule(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "shift" + "=" + (getShift() != null ? !getShift().equals(this)  ? getShift().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "driver = "+(getDriver()!=null?Integer.toHexString(System.identityHashCode(getDriver())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignment = "+(getAssignment()!=null?Integer.toHexString(System.identityHashCode(getAssignment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bTMS = "+(getBTMS()!=null?Integer.toHexString(System.identityHashCode(getBTMS())):"null");
  }
}