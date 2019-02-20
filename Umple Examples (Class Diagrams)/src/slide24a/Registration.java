/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide24a;

// line 5 "../slide 24a.ump"
public class Registration
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Registration Attributes
  private String grade;

  //Registration Associations
  private Student student;
  private CourseSection courseSection;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetStudent;
  private boolean canSetCourseSection;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Registration(String aGrade, Student aStudent, CourseSection aCourseSection)
  {
    cachedHashCode = -1;
    canSetStudent = true;
    canSetCourseSection = true;
    grade = aGrade;
    boolean didAddStudent = setStudent(aStudent);
    if (!didAddStudent)
    {
      throw new RuntimeException("Unable to create registration due to student");
    }
    boolean didAddCourseSection = setCourseSection(aCourseSection);
    if (!didAddCourseSection)
    {
      throw new RuntimeException("Unable to create registration due to courseSection");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGrade(String aGrade)
  {
    boolean wasSet = false;
    grade = aGrade;
    wasSet = true;
    return wasSet;
  }

  public String getGrade()
  {
    return grade;
  }

  public Student getStudent()
  {
    return student;
  }

  public CourseSection getCourseSection()
  {
    return courseSection;
  }

  public boolean setStudent(Student aStudent)
  {
    boolean wasSet = false;
    if (!canSetStudent) { return false; }
    if (aStudent == null)
    {
      return wasSet;
    }

    Student existingStudent = student;
    student = aStudent;
    if (existingStudent != null && !existingStudent.equals(aStudent))
    {
      existingStudent.removeRegistration(this);
    }
    if (!student.addRegistration(this))
    {
      student = existingStudent;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }

  public boolean setCourseSection(CourseSection aCourseSection)
  {
    boolean wasSet = false;
    if (!canSetCourseSection) { return false; }
    if (aCourseSection == null)
    {
      return wasSet;
    }

    CourseSection existingCourseSection = courseSection;
    courseSection = aCourseSection;
    if (existingCourseSection != null && !existingCourseSection.equals(aCourseSection))
    {
      existingCourseSection.removeRegistration(this);
    }
    if (!courseSection.addRegistration(this))
    {
      courseSection = existingCourseSection;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    Registration compareTo = (Registration)obj;
  
    if (student == null && compareTo.student != null)
    {
      return false;
    }
    else if (student != null && !student.equals(compareTo.student))
    {
      return false;
    }

    if (courseSection == null && compareTo.courseSection != null)
    {
      return false;
    }
    else if (courseSection != null && !courseSection.equals(compareTo.courseSection))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (student != null)
    {
      cachedHashCode = cachedHashCode * 23 + student.hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (courseSection != null)
    {
      cachedHashCode = cachedHashCode * 23 + courseSection.hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetStudent = false;
    canSetCourseSection = false;
    return cachedHashCode;
  }

  public void delete()
  {
    Student placeholderStudent = student;
    this.student = null;
    placeholderStudent.removeRegistration(this);
    CourseSection placeholderCourseSection = courseSection;
    this.courseSection = null;
    placeholderCourseSection.removeRegistration(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "grade" + ":" + getGrade()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "student = "+(getStudent()!=null?Integer.toHexString(System.identityHashCode(getStudent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "courseSection = "+(getCourseSection()!=null?Integer.toHexString(System.identityHashCode(getCourseSection())):"null")
     + outputString;
  }
}