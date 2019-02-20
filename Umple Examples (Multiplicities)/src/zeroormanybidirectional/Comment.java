/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package zeroormanybidirectional;

// line 7 "../(0) zero-or-many (bidirectional).ump"
public class Comment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Comment Attributes
  private String text;

  //Comment Associations
  private Discovery discovery;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Comment(String aText, Discovery aDiscovery)
  {
    text = aText;
    boolean didAddDiscovery = setDiscovery(aDiscovery);
    if (!didAddDiscovery)
    {
      throw new RuntimeException("Unable to create comment due to discovery");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setText(String aText)
  {
    boolean wasSet = false;
    text = aText;
    wasSet = true;
    return wasSet;
  }

  public String getText()
  {
    return text;
  }

  public Discovery getDiscovery()
  {
    return discovery;
  }

  public boolean setDiscovery(Discovery aDiscovery)
  {
    boolean wasSet = false;
    if (aDiscovery == null)
    {
      return wasSet;
    }

    Discovery existingDiscovery = discovery;
    discovery = aDiscovery;
    if (existingDiscovery != null && !existingDiscovery.equals(aDiscovery))
    {
      existingDiscovery.removeComment(this);
    }
    discovery.addComment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Discovery placeholderDiscovery = discovery;
    this.discovery = null;
    if(placeholderDiscovery != null)
    {
      placeholderDiscovery.removeComment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "text" + ":" + getText()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "discovery = "+(getDiscovery()!=null?Integer.toHexString(System.identityHashCode(getDiscovery())):"null");
  }
}