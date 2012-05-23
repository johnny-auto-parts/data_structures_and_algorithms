// linkStack.java
// demonstrates a stack implemented with a linked list

class Link
  {
  public long dData;
  public Link next;

  public Link(long dd)	// constructor
    { dData = dd; }

  public void displayLink()
    { System.out.print(dData + " "); }

  }

////////////////////////////////////////////////////////
class LinkList
  {
  private Link first;

  public LinkList()	// constructor
    { first = null; }

  public boolean isEmpty()
    { return (first==null); }

  public void insertFirst(long dd)
    {
    Link newLink = new Link(dd);
    newLink.next = first;
    first = newLink;
    }

  public long deleteFirst()
    {
    Link temp = first;
    first = first.next;
    return temp.dData;
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

////////////////////////////////////////////////////////
class LinkStack
  {
  private LinkList theList;

  public LinkStack()	// constructor
    {
    theList = new LinkList();
    }

  public void push(long j)	// put item on top of the stack
    {
    theList.insertFirst(j);
    }

  public long pop()		// take item from top of the stack
    {
    return theList.deleteFirst();
    }

  public boolean isEmpty()
    {
    return ( theList.isEmpty() );
    }

  public void displayStack()
    {
    System.out.println("Stack (top-->bottom): ");
    theList.displayList();
    }
  }

////////////////////////////////////////////////////////
class LinkStackApp
  {
  public static void main(String[] args)
    {
    LinkStack theStack = new LinkStack();

    theStack.push(20);
    theStack.push(40);
    
    theStack.displayStack();

    theStack.push(60);
    theStack.push(80);

    theStack.displayStack();

    theStack.pop();
    theStack.pop();

    theStack.displayStack();
    }
  }

