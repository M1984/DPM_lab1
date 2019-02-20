/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package slide29;

// line 1 "../slide29-file2.ump"
// line 5 "../slide29-file1.ump"
public class X
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //X Attributes
  private int b;
  private int a;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public X(int aB, int aA)
  {
    b = aB;
    a = aA;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setB(int aB)
  {
    boolean wasSet = false;
    b = aB;
    wasSet = true;
    return wasSet;
  }

  public boolean setA(int aA)
  {
    boolean wasSet = false;
    a = aA;
    wasSet = true;
    return wasSet;
  }

  public int getB()
  {
    return b;
  }

  public int getA()
  {
    return a;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "b" + ":" + getB()+ "," +
            "a" + ":" + getA()+ "]";
  }
}