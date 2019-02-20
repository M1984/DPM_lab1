/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide25;

// line 9 "../slide 25.ump"
public class Header
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Header Associations
  private Window window;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Header(Window aWindow)
  {
    boolean didAddWindow = setWindow(aWindow);
    if (!didAddWindow)
    {
      throw new RuntimeException("Unable to create title due to window");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Window getWindow()
  {
    return window;
  }

  public boolean setWindow(Window aNewWindow)
  {
    boolean wasSet = false;
    if (aNewWindow == null)
    {
      //Unable to setWindow to null, as title must always be associated to a window
      return wasSet;
    }
    
    Header existingTitle = aNewWindow.getTitle();
    if (existingTitle != null && !equals(existingTitle))
    {
      //Unable to setWindow, the current window already has a title, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Window anOldWindow = window;
    window = aNewWindow;
    window.setTitle(this);

    if (anOldWindow != null)
    {
      anOldWindow.setTitle(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Window existingWindow = window;
    window = null;
    if (existingWindow != null)
    {
      existingWindow.setTitle(null);
    }
  }

}