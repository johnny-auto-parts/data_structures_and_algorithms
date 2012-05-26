// sortedList.java
// a sorted linked list

class Link
  {
  public long dData;
  public Link next;

  public Link(long dd)
    { dData = dd; }

  public void displayLink()
    { System.out.print(dData + " "); }

  }
///////////////////////////////////////////////////
class SortedList
  {
  private Link first;

  public SortedList()		// constructor
    { first = null; }

  public boolean isEmpty()
    { return (first==null); }

  public void insert(long key)
    {
    Link newLink = new Link(key);

    // start at first link
    Link previous = null;
    Link current = first;

    while(current != null && key > current.dData)
      {
      // shift to next item
      previous = current;
      current = current.next;
      }

    if(previous==null)	// if list is currently empty
      first = newLink;  // set first
    else
      previous.next = newLink; // otherwise, insert in between previous and current
    newLink.next = current; // always set next to whatever is "current" (this will be null if list was empty)
    }

  public Link remove()	// remove first
    {
    Link temp = first;
    first = first.next;
    return temp;
    }

  public void displayList()
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
///////////////////////////////////////////////////
class SortedListApp
  {
  public static void main(String[] args)
    {
    SortedList theSortedList = new SortedList();
    theSortedList.insert(20);
    theSortedList.insert(40);

    theSortedList.displayList();

    theSortedList.insert(10);
    theSortedList.insert(30);
    theSortedList.insert(50);

    theSortedList.displayList();

    theSortedList.remove();

    theSortedList.displayList();
    }
  }
