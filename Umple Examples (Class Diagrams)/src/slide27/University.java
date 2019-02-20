/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide27;

// line 3 "../slide 27.ump"
public class University
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static University theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //University Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private University()
  {
    name = null;
  }

  public static University getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new University();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]"
     + outputString;
  }
}