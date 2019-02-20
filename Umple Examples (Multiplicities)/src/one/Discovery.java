/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package one;

// line 3 "../(3) one.ump"
public class Discovery
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Discovery Associations
  private Comment comment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Discovery(Comment aComment)
  {
    if (!setComment(aComment))
    {
      throw new RuntimeException("Unable to create Discovery due to aComment");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Comment getComment()
  {
    return comment;
  }

  public boolean setComment(Comment aNewComment)
  {
    boolean wasSet = false;
    if (aNewComment != null)
    {
      comment = aNewComment;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    comment = null;
  }

}