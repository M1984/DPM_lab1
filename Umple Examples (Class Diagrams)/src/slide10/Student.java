/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide10;

// line 3 "../slide 10.ump"
public class Student
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Student Attributes
  private String studentNumber;
  private String name;
  private int gradeAverage;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Student(String aStudentNumber, String aName, int aGradeAverage)
  {
    studentNumber = aStudentNumber;
    name = aName;
    gradeAverage = aGradeAverage;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStudentNumber(String aStudentNumber)
  {
    boolean wasSet = false;
    studentNumber = aStudentNumber;
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

  public boolean setGradeAverage(int aGradeAverage)
  {
    boolean wasSet = false;
    gradeAverage = aGradeAverage;
    wasSet = true;
    return wasSet;
  }

  /**
   * defaults to String
   */
  public String getStudentNumber()
  {
    return studentNumber;
  }

  public String getName()
  {
    return name;
  }

  /**
   * implemented as int
   */
  public int getGradeAverage()
  {
    return gradeAverage;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "studentNumber" + ":" + getStudentNumber()+ "," +
            "name" + ":" + getName()+ "," +
            "gradeAverage" + ":" + getGradeAverage()+ "]"
     + outputString;
  }
}