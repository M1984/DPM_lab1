/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package zeroorone;

// line 3 "../(4) zero-or-one.ump"
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

  public Discovery()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public Comment getComment()
  {
    return comment;
  }

  public boolean hasComment()
  {
    boolean has = comment != null;
    return has;
  }

  public boolean setComment(Comment aNewComment)
  {
    boolean wasSet = false;
    comment = aNewComment;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    comment = null;
  }

}