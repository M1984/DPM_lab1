/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide25;

// line 8 "../slide 25.ump"
public class Slider
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Slider Associations
  private Window window;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Slider(Window aWindow)
  {
    boolean didAddWindow = setWindow(aWindow);
    if (!didAddWindow)
    {
      throw new RuntimeException("Unable to create scrollbar due to window");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Window getWindow()
  {
    return window;
  }

  public boolean setWindow(Window aWindow)
  {
    boolean wasSet = false;
    //Must provide window to scrollbar
    if (aWindow == null)
    {
      return wasSet;
    }

    //window already at maximum (2)
    if (aWindow.numberOfScrollbar() >= Window.maximumNumberOfScrollbar())
    {
      return wasSet;
    }
    
    Window existingWindow = window;
    window = aWindow;
    if (existingWindow != null && !existingWindow.equals(aWindow))
    {
      boolean didRemove = existingWindow.removeScrollbar(this);
      if (!didRemove)
      {
        window = existingWindow;
        return wasSet;
      }
    }
    window.addScrollbar(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Window placeholderWindow = window;
    this.window = null;
    placeholderWindow.removeScrollbar(this);
  }

}