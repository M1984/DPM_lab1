/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide17;
import java.sql.Date;
import java.util.*;

// line 7 "../slide 17.ump"
public class CourseSection
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CourseSection Attributes
  private Date term;
  private String sectionLetter;

  //CourseSection Associations
  private Course course;
  private List<Student> registrant;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CourseSection(Date aTerm, String aSectionLetter, Course aCourse)
  {
    term = aTerm;
    sectionLetter = aSectionLetter;
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create courseSection due to course");
    }
    registrant = new ArrayList<Student>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTerm(Date aTerm)
  {
    boolean wasSet = false;
    term = aTerm;
    wasSet = true;
    return wasSet;
  }

  public boolean setSectionLetter(String aSectionLetter)
  {
    boolean wasSet = false;
    sectionLetter = aSectionLetter;
    wasSet = true;
    return wasSet;
  }

  public Date getTerm()
  {
    return term;
  }

  public String getSectionLetter()
  {
    return sectionLetter;
  }

  public Course getCourse()
  {
    return course;
  }

  public Student getRegistrant(int index)
  {
    Student aRegistrant = registrant.get(index);
    return aRegistrant;
  }

  public List<Student> getRegistrant()
  {
    List<Student> newRegistrant = Collections.unmodifiableList(registrant);
    return newRegistrant;
  }

  public int numberOfRegistrant()
  {
    int number = registrant.size();
    return number;
  }

  public boolean hasRegistrant()
  {
    boolean has = registrant.size() > 0;
    return has;
  }

  public int indexOfRegistrant(Student aRegistrant)
  {
    int index = registrant.indexOf(aRegistrant);
    return index;
  }

  public boolean setCourse(Course aCourse)
  {
    boolean wasSet = false;
    //Must provide course to courseSection
    if (aCourse == null)
    {
      return wasSet;
    }

    if (course != null && course.numberOfCourseSections() <= Course.minimumNumberOfCourseSections())
    {
      return wasSet;
    }

    Course existingCourse = course;
    course = aCourse;
    if (existingCourse != null && !existingCourse.equals(aCourse))
    {
      boolean didRemove = existingCourse.removeCourseSection(this);
      if (!didRemove)
      {
        course = existingCourse;
        return wasSet;
      }
    }
    course.addCourseSection(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfRegistrant()
  {
    return 0;
  }

  public boolean addRegistrant(Student aRegistrant)
  {
    boolean wasAdded = false;
    if (registrant.contains(aRegistrant)) { return false; }
    registrant.add(aRegistrant);
    if (aRegistrant.indexOfCourseSection(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRegistrant.addCourseSection(this);
      if (!wasAdded)
      {
        registrant.remove(aRegistrant);
      }
    }
    return wasAdded;
  }

  public boolean removeRegistrant(Student aRegistrant)
  {
    boolean wasRemoved = false;
    if (!registrant.contains(aRegistrant))
    {
      return wasRemoved;
    }

    int oldIndex = registrant.indexOf(aRegistrant);
    registrant.remove(oldIndex);
    if (aRegistrant.indexOfCourseSection(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRegistrant.removeCourseSection(this);
      if (!wasRemoved)
      {
        registrant.add(oldIndex,aRegistrant);
      }
    }
    return wasRemoved;
  }

  public boolean addRegistrantAt(Student aRegistrant, int index)
  {  
    boolean wasAdded = false;
    if(addRegistrant(aRegistrant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrant()) { index = numberOfRegistrant() - 1; }
      registrant.remove(aRegistrant);
      registrant.add(index, aRegistrant);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRegistrantAt(Student aRegistrant, int index)
  {
    boolean wasAdded = false;
    if(registrant.contains(aRegistrant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrant()) { index = numberOfRegistrant() - 1; }
      registrant.remove(aRegistrant);
      registrant.add(index, aRegistrant);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRegistrantAt(aRegistrant, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Course placeholderCourse = course;
    this.course = null;
    placeholderCourse.removeCourseSection(this);
    ArrayList<Student> copyOfRegistrant = new ArrayList<Student>(registrant);
    registrant.clear();
    for(Student aRegistrant : copyOfRegistrant)
    {
      aRegistrant.removeCourseSection(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "sectionLetter" + ":" + getSectionLetter()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "term" + "=" + (getTerm() != null ? !getTerm().equals(this)  ? getTerm().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "course = "+(getCourse()!=null?Integer.toHexString(System.identityHashCode(getCourse())):"null")
     + outputString;
  }
}