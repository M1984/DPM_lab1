/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package slide16;

// line 3 "../slide 16.ump"
public class Student
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum StudentType { Quebec, Canada, International }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Student Attributes
  private StudentType studentType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Student(StudentType aStudentType)
  {
    studentType = aStudentType;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStudentType(StudentType aStudentType)
  {
    boolean wasSet = false;
    studentType = aStudentType;
    wasSet = true;
    return wasSet;
  }

  public StudentType getStudentType()
  {
    return studentType;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "studentType" + "=" + (getStudentType() != null ? !getStudentType().equals(this)  ? getStudentType().toString().replaceAll("  ","    ") : "this" : "null");
  }
}