/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide22;
import java.util.*;

// line 3 "../slide 22.ump"
public class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Associations
  private List<Course> successor;
  private List<Course> mutuallyExclusive;
  private List<Course> prerequisite;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Course()
  {
    successor = new ArrayList<Course>();
    mutuallyExclusive = new ArrayList<Course>();
    prerequisite = new ArrayList<Course>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Course getSuccessor(int index)
  {
    Course aSuccessor = successor.get(index);
    return aSuccessor;
  }

  public List<Course> getSuccessor()
  {
    List<Course> newSuccessor = Collections.unmodifiableList(successor);
    return newSuccessor;
  }

  public int numberOfSuccessor()
  {
    int number = successor.size();
    return number;
  }

  public boolean hasSuccessor()
  {
    boolean has = successor.size() > 0;
    return has;
  }

  public int indexOfSuccessor(Course aSuccessor)
  {
    int index = successor.indexOf(aSuccessor);
    return index;
  }

  public Course getMutuallyExclusive(int index)
  {
    Course aMutuallyExclusive = mutuallyExclusive.get(index);
    return aMutuallyExclusive;
  }

  public List<Course> getMutuallyExclusive()
  {
    List<Course> newMutuallyExclusive = Collections.unmodifiableList(mutuallyExclusive);
    return newMutuallyExclusive;
  }

  public int numberOfMutuallyExclusive()
  {
    int number = mutuallyExclusive.size();
    return number;
  }

  public boolean hasMutuallyExclusive()
  {
    boolean has = mutuallyExclusive.size() > 0;
    return has;
  }

  public int indexOfMutuallyExclusive(Course aMutuallyExclusive)
  {
    int index = mutuallyExclusive.indexOf(aMutuallyExclusive);
    return index;
  }

  public Course getPrerequisite(int index)
  {
    Course aPrerequisite = prerequisite.get(index);
    return aPrerequisite;
  }

  public List<Course> getPrerequisite()
  {
    List<Course> newPrerequisite = Collections.unmodifiableList(prerequisite);
    return newPrerequisite;
  }

  public int numberOfPrerequisite()
  {
    int number = prerequisite.size();
    return number;
  }

  public boolean hasPrerequisite()
  {
    boolean has = prerequisite.size() > 0;
    return has;
  }

  public int indexOfPrerequisite(Course aPrerequisite)
  {
    int index = prerequisite.indexOf(aPrerequisite);
    return index;
  }

  public static int minimumNumberOfSuccessor()
  {
    return 0;
  }

  public boolean addSuccessor(Course aSuccessor)
  {
    boolean wasAdded = false;
    if (successor.contains(aSuccessor)) { return false; }
    successor.add(aSuccessor);
    if (aSuccessor.indexOfPrerequisite(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSuccessor.addPrerequisite(this);
      if (!wasAdded)
      {
        successor.remove(aSuccessor);
      }
    }
    return wasAdded;
  }

  public boolean removeSuccessor(Course aSuccessor)
  {
    boolean wasRemoved = false;
    if (!successor.contains(aSuccessor))
    {
      return wasRemoved;
    }

    int oldIndex = successor.indexOf(aSuccessor);
    successor.remove(oldIndex);
    if (aSuccessor.indexOfPrerequisite(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSuccessor.removePrerequisite(this);
      if (!wasRemoved)
      {
        successor.add(oldIndex,aSuccessor);
      }
    }
    return wasRemoved;
  }

  public boolean addSuccessorAt(Course aSuccessor, int index)
  {  
    boolean wasAdded = false;
    if(addSuccessor(aSuccessor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSuccessor()) { index = numberOfSuccessor() - 1; }
      successor.remove(aSuccessor);
      successor.add(index, aSuccessor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSuccessorAt(Course aSuccessor, int index)
  {
    boolean wasAdded = false;
    if(successor.contains(aSuccessor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSuccessor()) { index = numberOfSuccessor() - 1; }
      successor.remove(aSuccessor);
      successor.add(index, aSuccessor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSuccessorAt(aSuccessor, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfMutuallyExclusive()
  {
    return 0;
  }

  public boolean addMutuallyExclusive(Course aMutuallyExclusive)
  {
    boolean wasAdded = false;
    if (mutuallyExclusive.contains(aMutuallyExclusive)) { return false; }
    mutuallyExclusive.add(aMutuallyExclusive);
    if (aMutuallyExclusive.indexOfMutuallyExclusive(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMutuallyExclusive.addMutuallyExclusive(this);
      if (!wasAdded)
      {
        mutuallyExclusive.remove(aMutuallyExclusive);
      }
    }
    return wasAdded;
  }

  public boolean removeMutuallyExclusive(Course aMutuallyExclusive)
  {
    boolean wasRemoved = false;
    if (!mutuallyExclusive.contains(aMutuallyExclusive))
    {
      return wasRemoved;
    }

    int oldIndex = mutuallyExclusive.indexOf(aMutuallyExclusive);
    mutuallyExclusive.remove(oldIndex);
    if (aMutuallyExclusive.indexOfMutuallyExclusive(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMutuallyExclusive.removeMutuallyExclusive(this);
      if (!wasRemoved)
      {
        mutuallyExclusive.add(oldIndex,aMutuallyExclusive);
      }
    }
    return wasRemoved;
  }

  public boolean addMutuallyExclusiveAt(Course aMutuallyExclusive, int index)
  {  
    boolean wasAdded = false;
    if(addMutuallyExclusive(aMutuallyExclusive))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMutuallyExclusive()) { index = numberOfMutuallyExclusive() - 1; }
      mutuallyExclusive.remove(aMutuallyExclusive);
      mutuallyExclusive.add(index, aMutuallyExclusive);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMutuallyExclusiveAt(Course aMutuallyExclusive, int index)
  {
    boolean wasAdded = false;
    if(mutuallyExclusive.contains(aMutuallyExclusive))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMutuallyExclusive()) { index = numberOfMutuallyExclusive() - 1; }
      mutuallyExclusive.remove(aMutuallyExclusive);
      mutuallyExclusive.add(index, aMutuallyExclusive);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMutuallyExclusiveAt(aMutuallyExclusive, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfPrerequisite()
  {
    return 0;
  }

  public boolean addPrerequisite(Course aPrerequisite)
  {
    boolean wasAdded = false;
    if (prerequisite.contains(aPrerequisite)) { return false; }
    prerequisite.add(aPrerequisite);
    if (aPrerequisite.indexOfSuccessor(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPrerequisite.addSuccessor(this);
      if (!wasAdded)
      {
        prerequisite.remove(aPrerequisite);
      }
    }
    return wasAdded;
  }

  public boolean removePrerequisite(Course aPrerequisite)
  {
    boolean wasRemoved = false;
    if (!prerequisite.contains(aPrerequisite))
    {
      return wasRemoved;
    }

    int oldIndex = prerequisite.indexOf(aPrerequisite);
    prerequisite.remove(oldIndex);
    if (aPrerequisite.indexOfSuccessor(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPrerequisite.removeSuccessor(this);
      if (!wasRemoved)
      {
        prerequisite.add(oldIndex,aPrerequisite);
      }
    }
    return wasRemoved;
  }

  public boolean addPrerequisiteAt(Course aPrerequisite, int index)
  {  
    boolean wasAdded = false;
    if(addPrerequisite(aPrerequisite))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPrerequisite()) { index = numberOfPrerequisite() - 1; }
      prerequisite.remove(aPrerequisite);
      prerequisite.add(index, aPrerequisite);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePrerequisiteAt(Course aPrerequisite, int index)
  {
    boolean wasAdded = false;
    if(prerequisite.contains(aPrerequisite))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPrerequisite()) { index = numberOfPrerequisite() - 1; }
      prerequisite.remove(aPrerequisite);
      prerequisite.add(index, aPrerequisite);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPrerequisiteAt(aPrerequisite, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Course> copyOfSuccessor = new ArrayList<Course>(successor);
    successor.clear();
    for(Course aSuccessor : copyOfSuccessor)
    {
      aSuccessor.removePrerequisite(this);
    }
    ArrayList<Course> copyOfMutuallyExclusive = new ArrayList<Course>(mutuallyExclusive);
    mutuallyExclusive.clear();
    for(Course aMutuallyExclusive : copyOfMutuallyExclusive)
    {
      aMutuallyExclusive.removeMutuallyExclusive(this);
    }
    ArrayList<Course> copyOfPrerequisite = new ArrayList<Course>(prerequisite);
    prerequisite.clear();
    for(Course aPrerequisite : copyOfPrerequisite)
    {
      aPrerequisite.removeSuccessor(this);
    }
  }

}