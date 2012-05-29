// interIterator.java
// an iterator on a linked list
import java.io.*;

class Link
  {
  public long dData;
  public Link next;

  public Link(long dd)		// constructor
    { dData = dd; }

  public void displayLink()
    { System.out.print(dData + " "); }
  }
/////////////////////////////////////////////////////////
class LinkList
  {
  private Link first;

  public LinkList()
    { first = null; }

  public Link getFirst()
    { return first; }

  public void setFirst(Link f)
    { first = f; }

  public boolean isEmpty()
    { return first==null; }

  public ListIterator getIterator()
    {
    return new ListIterator(this); // 'this' refers to the Link List object that the operation is performed on
    }

  public void displayList()
    {
    Link current = first;
    while(current != null)
      {
      current.displayLink();
      current = current.next;
      }
    System.out.println("");
    }
  }
/////////////////////////////////////////////////////////
class ListIterator
  {
  private Link current;
  private Link previous;
  private LinkList ourList;

  public ListIterator(LinkList list) // constructor
    {
    ourList = list;
    reset();
    }

  public void reset()  // set iterator to the first item in list
    {
    current = ourList.getFirst();
    previous = null;
    }

  public boolean atEnd()  // true if iterator is at last link
    { return (current.next==null); }

  public void nextLink()  // go to next link
    {
    previous = current;
    current = current.next;
    }

  public Link getCurrent()  // where is the iterator?
    { return current; }

  public void insertAfter(long dd)  // insert after the iterator
    {
    Link newLink = new Link(dd);

    if( ourList.isEmpty() )
      {
      ourList.setFirst(newLink);
      current = newLink;
      reset();
      }
    else
      {
      newLink.next = current.next; // new link goes to the left of current's next
      current.next = newLink; // current points right to new link
      nextLink();
      }
    }

  public void insertBefore(long dd)
    {
    Link newLink = new Link(dd);

    if(previous == null) //beginning of the list (or empty list)
      {
      newLink.next = ourList.getFirst();
      ourList.setFirst(newLink);
      reset();
      }
    else
      {
      newLink.next = previous.next;
      previous.next = newLink;
      current = newLink;
      }
    }

  public long deleteCurrent()
    {
    long value = current.dData;
    if(previous == null)
      { // special case if we're deleting the first item
      ourList.setFirst(current.next);
      reset();
      }
    else
      {
      previous.next = current.next;

      // if the iterator was at the last element, move it to the first
      //  otherwise move it forward one item
      if( atEnd() )
        reset();
      else
        current = current.next;
      }
      return value;
    }
  }
/////////////////////////////////////////////////////////
class InterIterApp
  {
  public static void main(String[] args) throws IOException
    {
    LinkList theList = new LinkList();
    ListIterator iter1 = theList.getIterator(); // create a new iterator using 'this' linked list
    long value;

    iter1.insertAfter(20);
    iter1.insertAfter(40);
    iter1.insertAfter(80);
    iter1.insertBefore(60);

    while(true)
      {
      System.out.print("Enter first letter of show, reset, ");
      System.out.print("next, get, before, after, delete: ");
      System.out.flush();
      int choice = getChar();
      switch(choice)
        {
	// show/display list
	case 's':
	  if( !theList.isEmpty() )
	    theList.displayList();
	  else
	    System.out.println("List is empty");
	  break;
	// reset iterator
	case 'r':
	  iter1.reset();
	  break;
	// go to next link
	case 'n':
	  if( !theList.isEmpty() && !iter1.atEnd() )
	    iter1.nextLink();
	  else
	    System.out.println("Can't go to next link");
	  break;
	// get current item
        case 'g':
	  if ( !theList.isEmpty() )
	    {
	    value = iter1.getCurrent().dData;
            System.out.println("Returned " + value);
	    }
	  else
	    System.out.println("List is empty");
	  break;
	// insert item before current item
        case 'b':
	  System.out.print("Enter value to insert: ");
	  System.out.flush();
	  value = getInt();
	  iter1.insertBefore(value);
	  break;
	// insert item after current item
	case 'a':
	  System.out.print("Enter value to insert: ");
	  System.out.flush();
	  value = getInt();
	  iter1.insertAfter(value);
	  break;
	// delete current item
	case 'd':
	  if( !theList.isEmpty() )
	    {
	    value = iter1.deleteCurrent();
	    System.out.println("Deleted " + value);
	    }
	  else
	    System.out.println("Can't delete");
	  break;
	default:
	  System.out.println("Invalid entry");
	} // end switch
      } // end while
    } // end main

  public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }

  public static char getChar() throws IOException
    {
    String s = getString();
    return s.charAt(0);
    }

  public static int getInt() throws IOException
    {
    String s = getString();
    return Integer.parseInt(s);
    }

  }

