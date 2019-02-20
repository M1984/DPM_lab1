/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package slide28;

// line 3 "../slide28.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName)
  {
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 6 "../slide28.ump"
    if (aName == null || aName.length() == 0 || aName.length() > 20) {
          return false;
        }
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    wasSet = true;
    // line 11 "../slide28.ump"
    System.out.println("Successfully set name to : " + aName);
    // END OF UMPLE AFTER INJECTION
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
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}