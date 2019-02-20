/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package zerotofour;

// line 7 "../(5) zero-to-four.ump"
public class Comment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Comment Attributes
  private String text;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Comment(String aText)
  {
    text = aText;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setText(String aText)
  {
    boolean wasSet = false;
    text = aText;
    wasSet = true;
    return wasSet;
  }

  public String getText()
  {
    return text;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "text" + ":" + getText()+ "]";
  }
}