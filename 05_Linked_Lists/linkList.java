// linkList.java
// a simple linked list

class Link
  {
  public int iData;		// a data item (the key)
  public double dData;		// another data item
  public Link next;		// next link in the list

  public Link(int id, double dd) 	// constructor
    {
    iData = id;
    dData = dd;				// next automatically set to null
    }

  public void displayLink()	// display ourself
    {
    System.out.print("{" + iData + ", " + dData + "} ");
    }

  }

////////////////////////////////////////////////////////////////////////
class LinkList
  {
  private Link first;		// a reference to the first link on the list

  public LinkList()		// constructor
    {
    first = null;
    }

  public boolean isEmpty()
    {
    return (first==null);
    }

  public void insertFirst(int id, double dd)	// insert Link at start of List
    {
    Link newLink = new Link(id,dd);		// make new Link
    newLink.next = first;			// set the new Link's next link to whatever was 'first'
    first = newLink;				// set first to the new Link
    }

  public Link deleteFirst()			// delete Link at start of the list
    {
    Link temp = first;				// save reference to the link to delete
    first = first.next;				// implicitly delete by setting first to second
    return temp;				// deleted link is returned
    }

  public void displayList()
    {
    System.out.print("List (first-->last): ");
    Link current = first;			// start at beginning of the list
    while(current != null)			// until end of the list
      {
      current.displayLink();			// print data
      current = current.next;			// move to the next link
      }
    System.out.println("");
    }
  }

///////////////////////////////////////////////////////////////////////////////////////////////

class LinkListApp
  {
  public static void main(String[] args)
    {
    LinkList theList = new LinkList();

    // insert 4 items
    theList.insertFirst(22, 2.99);
    theList.insertFirst(44, 4.99);
    theList.insertFirst(66, 6.99);
    theList.insertFirst(88, 8.99);

    theList.displayList();

    while( !theList.isEmpty() )
      {
      Link aLink = theList.deleteFirst();	// remember, this is why deleteFirst returns something
      System.out.print("Deleted ");
      aLink.displayLink();
      System.out.print("");
      }

    theList.displayList();

    }
  }
