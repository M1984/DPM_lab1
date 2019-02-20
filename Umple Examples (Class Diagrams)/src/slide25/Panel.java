/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide25;

// line 1 "../slide 25.ump"
public class Panel
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Panel Associations
  private Window window;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Panel(Window aWindow)
  {
    if (aWindow == null || aWindow.getBody() != null)
    {
      throw new RuntimeException("Unable to create Panel due to aWindow");
    }
    window = aWindow;
  }

  public Panel()
  {
    window = new Window(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Window getWindow()
  {
    return window;
  }

  public void delete()
  {
    Window existingWindow = window;
    window = null;
    if (existingWindow != null)
    {
      existingWindow.delete();
    }
  }

}