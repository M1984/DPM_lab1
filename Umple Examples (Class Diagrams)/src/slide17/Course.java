/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide17;
import java.util.*;
import java.sql.Date;

// line 5 "../slide 17.ump"
public class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private String description;
  private String code;

  //Course Associations
  private List<CourseSection> courseSections;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Course(String aDescription, String aCode)
  {
    description = aDescription;
    code = aCode;
    courseSections = new ArrayList<CourseSection>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setCode(String aCode)
  {
    boolean wasSet = false;
    code = aCode;
    wasSet = true;
    return wasSet;
  }

  public String getDescription()
  {
    return description;
  }

  public String getCode()
  {
    return code;
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

  public boolean isNumberOfCourseSectionsValid()
  {
    boolean isValid = numberOfCourseSections() >= minimumNumberOfCourseSections();
    return isValid;
  }

  public static int minimumNumberOfCourseSections()
  {
    return 1;
  }

  public CourseSection addCourseSection(Date aTerm, String aSectionLetter)
  {
    CourseSection aNewCourseSection = new CourseSection(aTerm, aSectionLetter, this);
    return aNewCourseSection;
  }

  public boolean addCourseSection(CourseSection aCourseSection)
  {
    boolean wasAdded = false;
    if (courseSections.contains(aCourseSection)) { return false; }
    Course existingCourse = aCourseSection.getCourse();
    boolean isNewCourse = existingCourse != null && !this.equals(existingCourse);

    if (isNewCourse && existingCourse.numberOfCourseSections() <= minimumNumberOfCourseSections())
    {
      return wasAdded;
    }
    if (isNewCourse)
    {
      aCourseSection.setCourse(this);
    }
    else
    {
      courseSections.add(aCourseSection);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCourseSection(CourseSection aCourseSection)
  {
    boolean wasRemoved = false;
    //Unable to remove aCourseSection, as it must always have a course
    if (this.equals(aCourseSection.getCourse()))
    {
      return wasRemoved;
    }

    //course already at minimum (1)
    if (numberOfCourseSections() <= minimumNumberOfCourseSections())
    {
      return wasRemoved;
    }

    courseSections.remove(aCourseSection);
    wasRemoved = true;
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
    for(int i=courseSections.size(); i > 0; i--)
    {
      CourseSection aCourseSection = courseSections.get(i - 1);
      aCourseSection.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "description" + ":" + getDescription()+ "," +
            "code" + ":" + getCode()+ "]"
     + outputString;
  }
}