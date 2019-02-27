/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.util.*;

// line 57 "../../../../../BTMS.ump"
public class Driver
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Driver Attributes
  private String name;
  private boolean onSickLeave;

  //Autounique Attributes
  private int id;

  //Driver Associations
  private BTMS bTMS;
  private List<DriverSchedule> driverSchedules;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Driver(String aName, boolean aOnSickLeave, BTMS aBTMS)
  {
    // line 60 "../../../../../BTMS.ump"
    if (aName == null || aName.length() == 0) {
    	  throw new RuntimeException("The name of a driver cannot be empty.");
    	}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    onSickLeave = aOnSickLeave;
    id = nextId++;
    boolean didAddBTMS = setBTMS(aBTMS);
    if (!didAddBTMS)
    {
      throw new RuntimeException("Unable to create driver due to bTMS");
    }
    driverSchedules = new ArrayList<DriverSchedule>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 60 "../../../../../BTMS.ump"
    if (aName == null || aName.length() == 0) {
    	  throw new RuntimeException("The name of a driver cannot be empty.");
    	}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setOnSickLeave(boolean aOnSickLeave)
  {
    boolean wasSet = false;
    onSickLeave = aOnSickLeave;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public boolean getOnSickLeave()
  {
    return onSickLeave;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isOnSickLeave()
  {
    return onSickLeave;
  }
  /* Code from template association_GetOne */
  public BTMS getBTMS()
  {
    return bTMS;
  }
  /* Code from template association_GetMany */
  public DriverSchedule getDriverSchedule(int index)
  {
    DriverSchedule aDriverSchedule = driverSchedules.get(index);
    return aDriverSchedule;
  }

  public List<DriverSchedule> getDriverSchedules()
  {
    List<DriverSchedule> newDriverSchedules = Collections.unmodifiableList(driverSchedules);
    return newDriverSchedules;
  }

  public int numberOfDriverSchedules()
  {
    int number = driverSchedules.size();
    return number;
  }

  public boolean hasDriverSchedules()
  {
    boolean has = driverSchedules.size() > 0;
    return has;
  }

  public int indexOfDriverSchedule(DriverSchedule aDriverSchedule)
  {
    int index = driverSchedules.indexOf(aDriverSchedule);
    return index;
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
      existingBTMS.removeDriver(this);
    }
    bTMS.addDriver(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDriverSchedules()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public DriverSchedule addDriverSchedule(DriverSchedule.Shift aShift, RouteAssignment aAssignment, BTMS aBTMS)
  {
    return new DriverSchedule(aShift, this, aAssignment, aBTMS);
  }

  public boolean addDriverSchedule(DriverSchedule aDriverSchedule)
  {
    boolean wasAdded = false;
    if (driverSchedules.contains(aDriverSchedule)) { return false; }
    Driver existingDriver = aDriverSchedule.getDriver();
    boolean isNewDriver = existingDriver != null && !this.equals(existingDriver);
    if (isNewDriver)
    {
      aDriverSchedule.setDriver(this);
    }
    else
    {
      driverSchedules.add(aDriverSchedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDriverSchedule(DriverSchedule aDriverSchedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aDriverSchedule, as it must always have a driver
    if (!this.equals(aDriverSchedule.getDriver()))
    {
      driverSchedules.remove(aDriverSchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDriverScheduleAt(DriverSchedule aDriverSchedule, int index)
  {  
    boolean wasAdded = false;
    if(addDriverSchedule(aDriverSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDriverSchedules()) { index = numberOfDriverSchedules() - 1; }
      driverSchedules.remove(aDriverSchedule);
      driverSchedules.add(index, aDriverSchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDriverScheduleAt(DriverSchedule aDriverSchedule, int index)
  {
    boolean wasAdded = false;
    if(driverSchedules.contains(aDriverSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDriverSchedules()) { index = numberOfDriverSchedules() - 1; }
      driverSchedules.remove(aDriverSchedule);
      driverSchedules.add(index, aDriverSchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDriverScheduleAt(aDriverSchedule, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    BTMS placeholderBTMS = bTMS;
    this.bTMS = null;
    if(placeholderBTMS != null)
    {
      placeholderBTMS.removeDriver(this);
    }
    for(int i=driverSchedules.size(); i > 0; i--)
    {
      DriverSchedule aDriverSchedule = driverSchedules.get(i - 1);
      aDriverSchedule.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "," +
            "onSickLeave" + ":" + getOnSickLeave()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bTMS = "+(getBTMS()!=null?Integer.toHexString(System.identityHashCode(getBTMS())):"null");
  }
}