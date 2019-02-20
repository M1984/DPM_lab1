/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package slide25;
import java.util.*;

// line 3 "../slide 25.ump"
public class Window
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Window Associations
  private List<Slider> scrollbar;
  private Header title;
  private Panel body;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Window(Panel aBody)
  {
    scrollbar = new ArrayList<Slider>();
    if (aBody == null || aBody.getWindow() != null)
    {
      throw new RuntimeException("Unable to create Window due to aBody");
    }
    body = aBody;
  }

  public Window()
  {
    scrollbar = new ArrayList<Slider>();
    body = new Panel(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Slider getScrollbar(int index)
  {
    Slider aScrollbar = scrollbar.get(index);
    return aScrollbar;
  }

  public List<Slider> getScrollbar()
  {
    List<Slider> newScrollbar = Collections.unmodifiableList(scrollbar);
    return newScrollbar;
  }

  public int numberOfScrollbar()
  {
    int number = scrollbar.size();
    return number;
  }

  public boolean hasScrollbar()
  {
    boolean has = scrollbar.size() > 0;
    return has;
  }

  public int indexOfScrollbar(Slider aScrollbar)
  {
    int index = scrollbar.indexOf(aScrollbar);
    return index;
  }

  public Header getTitle()
  {
    return title;
  }

  public boolean hasTitle()
  {
    boolean has = title != null;
    return has;
  }

  public Panel getBody()
  {
    return body;
  }

  public boolean isNumberOfScrollbarValid()
  {
    boolean isValid = numberOfScrollbar() >= minimumNumberOfScrollbar() && numberOfScrollbar() <= maximumNumberOfScrollbar();
    return isValid;
  }

  public static int requiredNumberOfScrollbar()
  {
    return 2;
  }

  public static int minimumNumberOfScrollbar()
  {
    return 2;
  }

  public static int maximumNumberOfScrollbar()
  {
    return 2;
  }

  public Slider addScrollbar()
  {
    if (numberOfScrollbar() >= maximumNumberOfScrollbar())
    {
      return null;
    }
    else
    {
      return new Slider(this);
    }
  }

  public boolean addScrollbar(Slider aScrollbar)
  {
    boolean wasAdded = false;
    if (scrollbar.contains(aScrollbar)) { return false; }
    if (numberOfScrollbar() >= maximumNumberOfScrollbar())
    {
      return wasAdded;
    }

    Window existingWindow = aScrollbar.getWindow();
    boolean isNewWindow = existingWindow != null && !this.equals(existingWindow);

    if (isNewWindow && existingWindow.numberOfScrollbar() <= minimumNumberOfScrollbar())
    {
      return wasAdded;
    }

    if (isNewWindow)
    {
      aScrollbar.setWindow(this);
    }
    else
    {
      scrollbar.add(aScrollbar);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeScrollbar(Slider aScrollbar)
  {
    boolean wasRemoved = false;
    //Unable to remove aScrollbar, as it must always have a window
    if (this.equals(aScrollbar.getWindow()))
    {
      return wasRemoved;
    }

    //window already at minimum (2)
    if (numberOfScrollbar() <= minimumNumberOfScrollbar())
    {
      return wasRemoved;
    }
    scrollbar.remove(aScrollbar);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean setTitle(Header aNewTitle)
  {
    boolean wasSet = false;
    if (title != null && !title.equals(aNewTitle) && equals(title.getWindow()))
    {
      //Unable to setTitle, as existing title would become an orphan
      return wasSet;
    }

    title = aNewTitle;
    Window anOldWindow = aNewTitle != null ? aNewTitle.getWindow() : null;

    if (!this.equals(anOldWindow))
    {
      if (anOldWindow != null)
      {
        anOldWindow.title = null;
      }
      if (title != null)
      {
        title.setWindow(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (scrollbar.size() > 0)
    {
      Slider aScrollbar = scrollbar.get(scrollbar.size() - 1);
      aScrollbar.delete();
      scrollbar.remove(aScrollbar);
    }
    
      
    Header existingTitle = title;
    title = null;
    if (existingTitle != null)
    {
      existingTitle.delete();
      existingTitle.setWindow(null);
    }
    Panel existingBody = body;
    body = null;
    if (existingBody != null)
    {
      existingBody.delete();
    }
  }

}