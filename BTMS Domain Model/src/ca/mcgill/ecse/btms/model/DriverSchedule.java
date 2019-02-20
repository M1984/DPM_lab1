/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;

// line 59 "../../../../../BTMS.ump"
public class DriverSchedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DriverSchedule Associations
  private Driver driver;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DriverSchedule(Driver aDriver)
  {
    boolean didAddDriver = setDriver(aDriver);
    if (!didAddDriver)
    {
      throw new RuntimeException("Unable to create driverSchedule due to driver");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Driver getDriver()
  {
    return driver;
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

  public void delete()
  {
    Driver placeholderDriver = driver;
    this.driver = null;
    if(placeholderDriver != null)
    {
      placeholderDriver.removeDriverSchedule(this);
    }
  }

}