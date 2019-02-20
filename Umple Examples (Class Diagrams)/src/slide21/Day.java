/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide21;
import java.util.*;

// line 3 "../slide 21.ump"
public class Day
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Day Associations
  private List<Note> notes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Day()
  {
    notes = new ArrayList<Note>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Note getNote(int index)
  {
    Note aNote = notes.get(index);
    return aNote;
  }

  public List<Note> getNotes()
  {
    List<Note> newNotes = Collections.unmodifiableList(notes);
    return newNotes;
  }

  public int numberOfNotes()
  {
    int number = notes.size();
    return number;
  }

  public boolean hasNotes()
  {
    boolean has = notes.size() > 0;
    return has;
  }

  public int indexOfNote(Note aNote)
  {
    int index = notes.indexOf(aNote);
    return index;
  }

  public static int minimumNumberOfNotes()
  {
    return 0;
  }

  public boolean addNote(Note aNote)
  {
    boolean wasAdded = false;
    if (notes.contains(aNote)) { return false; }
    notes.add(aNote);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeNote(Note aNote)
  {
    boolean wasRemoved = false;
    if (notes.contains(aNote))
    {
      notes.remove(aNote);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addNoteAt(Note aNote, int index)
  {  
    boolean wasAdded = false;
    if(addNote(aNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotes()) { index = numberOfNotes() - 1; }
      notes.remove(aNote);
      notes.add(index, aNote);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveNoteAt(Note aNote, int index)
  {
    boolean wasAdded = false;
    if(notes.contains(aNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotes()) { index = numberOfNotes() - 1; }
      notes.remove(aNote);
      notes.add(index, aNote);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addNoteAt(aNote, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    notes.clear();
  }

}