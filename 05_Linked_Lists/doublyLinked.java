// doublyLinked.java
// a doubly-linked list

class Link
  {
  public long dData;
  public Link next;
  public Link previous;

  public Link(long d)
    { dData = d; }

  public void displayLink()
    { System.out.print(dData + " "); }

  }
////////////////////////////////////////////////////////////////////
class DoublyLinkedList
  {
  private Link first;
  private Link last;

  public DoublyLinkedList()
    {
    first = null;
    last = null;
    }

  public boolean isEmpty()
    { return first==null; }

  public void insertFirst(long dd)
    {
    Link newLink = new Link(dd);

    if( isEmpty() )
      last = newLink;
    else
      first.previous = newLink; // if the list already has an object, newLink <--old first
    newLink.next = first;
    first = newLink;
    }

  public void insertLast(long dd)
    {
    Link newLink = new Link(dd);
    if( isEmpty() )
      first = newLink;  // opposite edge case of insertFirst()
    else
      {
      // hook last element up to new link
      last.next = newLink;  
      newLink.previous = last;
      }
    last = newLink;  // this is always the case when inserting a last element
    }

  public Link deleteFirst()
    {
    Link temp = first;
    if(first.next == null)  // only one item
      last = null;
    else
      first.next.previous = null; // 2nd item, which is becoming 1st item now has no prior item
    first = first.next;  // 2nd becomes first
    return temp;
    }

  public Link deleteLast()
    {
    Link temp = last;
    if(first.next == null) // only one item
      first = null;
    else
      last.previous.next = null; // the item before last now has no subsequent item
    last = last.previous; // the last item becomes what was 2nd to last
    return temp;
    }

  public boolean insertAfter(long key, long dd) // insert dd after key
    {
    Link current = first; // start at the beginning
    while(current.dData != key) // until we find the key
      {
      current = current.next;
      if(current == null) // we didn't find it!
        return false;
      }
    Link newLink = new Link(dd);

    if(current==last) // edge case if last key is passed to us
      {
      newLink.next = null;
      last = newLink;
      }
    else
      {
      // wedge new link after current
      newLink.next = current.next;
      current.next.previous = newLink;
      }
    newLink.previous = current; // we're always inserting after current
    current.next = newLink;
    return true;
    }

  public Link deleteKey(long key)
    {
    // same search subroutine from insertAfter()
    Link current = first;
    while(current.dData != key)
      {
      current = current.next;
      if(current == null)
        return null;
      }
    if(current==first) // edge case if first key is passed to us
      first = current.next; // old 2nd becomes 1st
    else
      current.previous.next = current.next; // if not first, previous item latches across to next item
    
    if(current==last) // edge case if last key is passed to us
      last = current.previous; // last becomes 2nd to last key
    else
      current.next.previous = current.previous; // if not last, the next item's previous item refers to current's previous
    return current; // this value was deleted
    }

  public void displayForward() // print the doubly linked list from start to finish
    {
    System.out.print("List (first-->last): ");
    Link current = first; // start at beginning
    while(current != null)
      {
      current.displayLink();
      current = current.next;
      }
    System.out.println("");
    }

  public void displayBackward() // print the doubly linked list from finish to start
    {
    System.out.print("List (last-->first): ");
    Link current = last;
    while(current != null)
      {
      current.displayLink();
      current = current.previous;
      }
    System.out.println("");
    }
  }
///////////////////////////////////////////////////////////////////////////////////////////////////
class DoublyLinkedApp
  {
  public static void main(String[] args)
    {
    DoublyLinkedList theList = new DoublyLinkedList();

    theList.insertFirst(22);
    theList.insertFirst(44);
    theList.insertFirst(66);

    theList.insertLast(11);
    theList.insertLast(33);
    theList.insertLast(55);

    theList.displayForward();
    theList.displayBackward();

    theList.deleteFirst();
    theList.deleteLast();
    theList.deleteKey(11);

    theList.displayForward();

    theList.insertAfter(22,77);
    theList.insertAfter(33,88);

    theList.displayForward();
    }
  }

