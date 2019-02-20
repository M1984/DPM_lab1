/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package zeroormanybidirectional;
import java.util.*;

// line 3 "../(0) zero-or-many (bidirectional).ump"
public class Discovery
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Discovery Associations
  private List<Comment> comments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Discovery()
  {
    comments = new ArrayList<Comment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Comment getComment(int index)
  {
    Comment aComment = comments.get(index);
    return aComment;
  }

  public List<Comment> getComments()
  {
    List<Comment> newComments = Collections.unmodifiableList(comments);
    return newComments;
  }

  public int numberOfComments()
  {
    int number = comments.size();
    return number;
  }

  public boolean hasComments()
  {
    boolean has = comments.size() > 0;
    return has;
  }

  public int indexOfComment(Comment aComment)
  {
    int index = comments.indexOf(aComment);
    return index;
  }

  public static int minimumNumberOfComments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Comment addComment(String aText)
  {
    return new Comment(aText, this);
  }

  public boolean addComment(Comment aComment)
  {
    boolean wasAdded = false;
    if (comments.contains(aComment)) { return false; }
    Discovery existingDiscovery = aComment.getDiscovery();
    boolean isNewDiscovery = existingDiscovery != null && !this.equals(existingDiscovery);
    if (isNewDiscovery)
    {
      aComment.setDiscovery(this);
    }
    else
    {
      comments.add(aComment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeComment(Comment aComment)
  {
    boolean wasRemoved = false;
    //Unable to remove aComment, as it must always have a discovery
    if (!this.equals(aComment.getDiscovery()))
    {
      comments.remove(aComment);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCommentAt(Comment aComment, int index)
  {  
    boolean wasAdded = false;
    if(addComment(aComment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfComments()) { index = numberOfComments() - 1; }
      comments.remove(aComment);
      comments.add(index, aComment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCommentAt(Comment aComment, int index)
  {
    boolean wasAdded = false;
    if(comments.contains(aComment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfComments()) { index = numberOfComments() - 1; }
      comments.remove(aComment);
      comments.add(index, aComment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCommentAt(aComment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=comments.size(); i > 0; i--)
    {
      Comment aComment = comments.get(i - 1);
      aComment.delete();
    }
  }

}