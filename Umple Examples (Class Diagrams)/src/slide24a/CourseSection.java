/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide24a;
import java.util.*;

// line 4 "../slide 24a.ump"
public class CourseSection
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CourseSection Associations
  private List<Registration> registrations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CourseSection()
  {
    registrations = new ArrayList<Registration>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Registration getRegistration(int index)
  {
    Registration aRegistration = registrations.get(index);
    return aRegistration;
  }

  public List<Registration> getRegistrations()
  {
    List<Registration> newRegistrations = Collections.unmodifiableList(registrations);
    return newRegistrations;
  }

  public int numberOfRegistrations()
  {
    int number = registrations.size();
    return number;
  }

  public boolean hasRegistrations()
  {
    boolean has = registrations.size() > 0;
    return has;
  }

  public int indexOfRegistration(Registration aRegistration)
  {
    int index = registrations.indexOf(aRegistration);
    return index;
  }

  public static int minimumNumberOfRegistrations()
  {
    return 0;
  }

  public Registration addRegistration(String aGrade, Student aStudent)
  {
    return new Registration(aGrade, aStudent, this);
  }

  public boolean addRegistration(Registration aRegistration)
  {
    boolean wasAdded = false;
    if (registrations.contains(aRegistration)) { return false; }
    CourseSection existingCourseSection = aRegistration.getCourseSection();
    boolean isNewCourseSection = existingCourseSection != null && !this.equals(existingCourseSection);
    if (isNewCourseSection)
    {
      aRegistration.setCourseSection(this);
    }
    else
    {
      registrations.add(aRegistration);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRegistration(Registration aRegistration)
  {
    boolean wasRemoved = false;
    //Unable to remove aRegistration, as it must always have a courseSection
    if (!this.equals(aRegistration.getCourseSection()))
    {
      registrations.remove(aRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addRegistrationAt(Registration aRegistration, int index)
  {  
    boolean wasAdded = false;
    if(addRegistration(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRegistrationAt(Registration aRegistration, int index)
  {
    boolean wasAdded = false;
    if(registrations.contains(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRegistrationAt(aRegistration, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=registrations.size(); i > 0; i--)
    {
      Registration aRegistration = registrations.get(i - 1);
      aRegistration.delete();
    }
  }

}