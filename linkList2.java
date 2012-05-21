// linkList2.java
// A Link Listed with specific find/delete

class Link
  {
  public int iData;		// (key)
  public double dData;
  public Link next;

  public Link (int id, double dd) // constructor
    {
    iData = id;
    dData = dd;
    }

  public void displayLink()	// display ourself
    {
    System.out.print("{" + iData + ", " + dData + "} ");
    }

  }

///////////////////////////////////////////////////////////
class LinkList
  {
  private Link first;		// ref to first link on the list

  public LinkList()		// constructor
    {
    first = null;		// by default, link list is empty
    }

  public void insertFirst(int id, double dd)
    {
    Link newLink = new Link(id, dd);
    newLink.next = first;	// we're putting the new link at the front
    first = newLink;
    }

  public Link find(int key)	// find link with given key
    {
    Link current = first;	// start at first
    while(current.iData != key)
      {
      if(current.next == null)
        return null;		// we didn't find it
      else
        current = current.next;	// go to next link
      }
    return current;		// found it!
    }

  public Link delete(int key)	// delete link with given key
    {
    Link current = first;
    Link previous = first;	// once we find our Link, we adjust the previous Link
    while(current.iData != key)
      {
      if(current.next == null)  // reached end of Linked List without finding it
        return null;
      else
        { 
	previous = current; 	// set last traversed link to the current link
	current = current.next; // set what we're currently traversing to the next link
	}			// if key is found, current variable will ultimately hold it
      }
    if(current == first)	// if its the first link, simply set first to second
      first = first.next;
    else
      previous.next = current.next;	//otherwise, connect the previous Link across to the next link
    return current;
    }

  public void displayList()	// display the linked list
    {
    System.out.print("List (first-->last): ");
    Link current = first;
    while(current != null)
      {
      current.displayLink();
      current = current.next;
      }
    System.out.println("");
    }

  }
/////////////////////////////////////////////////////////////////////////////////
class LinkList2App
  {
  public static void main(String[] args)
    {
    LinkList theList = new LinkList();

    theList.insertFirst(22,2.99);
    theList.insertFirst(44,4.99);
    theList.insertFirst(66,6.99);
    theList.insertFirst(88,8.99);

    theList.displayList();

    Link f = theList.find(44);
    if( f != null)
      System.out.println("Found link with key " + f.iData);
    else
      System.out.println("Can't find link");

    Link d = theList.delete(66);
    if ( d != null)
      System.out.println("Deleted link with key " + d.iData);
    else
      System.out.println("Can't delete link");

    theList.displayList();
    }
  }
