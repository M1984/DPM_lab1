/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide17;
import java.util.*;

// line 3 "../slide 17.ump"
public class Student
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Student Attributes
  private String id;
  private String name;

  //Student Associations
  private List<CourseSection> courseSections;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Student(String aId, String aName)
  {
    id = aId;
    name = aName;
    courseSections = new ArrayList<CourseSection>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(String aId)
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

  public String getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public CourseSection getCourseSection(int index)
  {
    CourseSection aCourseSection = courseSections.get(index);
    return aCourseSection;
  }

  public List<CourseSection> getCourseSections()
  {
    List<CourseSection> newCourseSections = Collections.unmodifiableList(courseSections);
    return newCourseSections;
  }

  public int numberOfCourseSections()
  {
    int number = courseSections.size();
    return number;
  }

  public boolean hasCourseSections()
  {
    boolean has = courseSections.size() > 0;
    return has;
  }

  public int indexOfCourseSection(CourseSection aCourseSection)
  {
    int index = courseSections.indexOf(aCourseSection);
    return index;
  }

  public static int minimumNumberOfCourseSections()
  {
    return 0;
  }

  public boolean addCourseSection(CourseSection aCourseSection)
  {
    boolean wasAdded = false;
    if (courseSections.contains(aCourseSection)) { return false; }
    courseSections.add(aCourseSection);
    if (aCourseSection.indexOfRegistrant(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCourseSection.addRegistrant(this);
      if (!wasAdded)
      {
        courseSections.remove(aCourseSection);
      }
    }
    return wasAdded;
  }

  public boolean removeCourseSection(CourseSection aCourseSection)
  {
    boolean wasRemoved = false;
    if (!courseSections.contains(aCourseSection))
    {
      return wasRemoved;
    }

    int oldIndex = courseSections.indexOf(aCourseSection);
    courseSections.remove(oldIndex);
    if (aCourseSection.indexOfRegistrant(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCourseSection.removeRegistrant(this);
      if (!wasRemoved)
      {
        courseSections.add(oldIndex,aCourseSection);
      }
    }
    return wasRemoved;
  }

  public boolean addCourseSectionAt(CourseSection aCourseSection, int index)
  {  
    boolean wasAdded = false;
    if(addCourseSection(aCourseSection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourseSections()) { index = numberOfCourseSections() - 1; }
      courseSections.remove(aCourseSection);
      courseSections.add(index, aCourseSection);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCourseSectionAt(CourseSection aCourseSection, int index)
  {
    boolean wasAdded = false;
    if(courseSections.contains(aCourseSection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourseSections()) { index = numberOfCourseSections() - 1; }
      courseSections.remove(aCourseSection);
      courseSections.add(index, aCourseSection);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCourseSectionAt(aCourseSection, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<CourseSection> copyOfCourseSections = new ArrayList<CourseSection>(courseSections);
    courseSections.clear();
    for(CourseSection aCourseSection : copyOfCourseSections)
    {
      aCourseSection.removeRegistrant(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "]"
     + outputString;
  }
}