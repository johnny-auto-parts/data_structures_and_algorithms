// firstLastList.java
// demonstrates a Linked List with first and last references

class Link
  {
  public long dData;
  public Link next;

  public Link(long d)	// constructor
    { dData = d; }

  public void displayLink()
    {System.out.print(dData + " "); }
  }

/////////////////////////////////////////
class FirstLastList
  {
  private Link first;
  private Link last;

  public FirstLastList()	// constructor
    {
    first = null;
    last = null;
    }

  public boolean isEmpty()
    { return first==null; }

  public void insertFirst(long dd)	// insert at front of List
    {
    Link newLink = new Link(dd);	// our new link

    if( isEmpty() )
      last = newLink;		// if the list is empty, we need to do a 'last' operation 

    newLink.next = first;	// always hook the new link in front of whatever "was" first
    first = newLink;		// first becomes the new link
    }

  public void insertLast(long dd)
    {
    Link newLink = new Link (dd);	// our new link
    if( isEmpty() )
      first = newLink;		// if the list is empty, we need to do a 'first' operation
    else
      last.next = newLink;
    last = newLink;
    }

  public long deleteFirst()	// delete the first link
    {
    long temp = first.dData;	// our method wants to return this
    
    if(first.next == null)	// if there isn't a 2nd item beforehand, last will always be null
      last = null;
    first = first.next;		// first becomes whatever was 2nd

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

///////////////////////////////////////////////////////////////////////////////////
class FirstLastApp
  {
  public static void main(String[] args)
    {
    FirstLastList theList = new FirstLastList();

    // insert at front
    theList.insertFirst(22);
    theList.insertFirst(44);
    theList.insertFirst(66);
    // insert at rear
    theList.insertLast(11);
    theList.insertLast(33);
    theList.insertLast(55);

    theList.displayList();

    // delete first two items
    theList.deleteFirst();
    theList.deleteFirst();

    theList.displayList();
    }
  }
