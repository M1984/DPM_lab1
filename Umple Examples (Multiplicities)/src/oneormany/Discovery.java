/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package oneormany;
import java.util.*;

// line 3 "../(2) one-or-many.ump"
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

  public Discovery(Comment... allComments)
  {
    comments = new ArrayList<Comment>();
    boolean didAddComments = setComments(allComments);
    if (!didAddComments)
    {
      throw new RuntimeException("Unable to create Discovery, must have at least 1 comments");
    }
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
    return 1;
  }

  public boolean addComment(Comment aComment)
  {
    boolean wasAdded = false;
    if (comments.contains(aComment)) { return false; }
    comments.add(aComment);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeComment(Comment aComment)
  {
    boolean wasRemoved = false;
    if (!comments.contains(aComment))
    {
      return wasRemoved;
    }

    if (numberOfComments() <= minimumNumberOfComments())
    {
      return wasRemoved;
    }

    comments.remove(aComment);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean setComments(Comment... newComments)
  {
    boolean wasSet = false;
    ArrayList<Comment> verifiedComments = new ArrayList<Comment>();
    for (Comment aComment : newComments)
    {
      if (verifiedComments.contains(aComment))
      {
        continue;
      }
      verifiedComments.add(aComment);
    }

    if (verifiedComments.size() != newComments.length || verifiedComments.size() < minimumNumberOfComments())
    {
      return wasSet;
    }

    comments.clear();
    comments.addAll(verifiedComments);
    wasSet = true;
    return wasSet;
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
    comments.clear();
  }

}