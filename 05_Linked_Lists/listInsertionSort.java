// listInsertionSort.java
// pass array of links to linked list sorting function
// comparisons: 0(N^2)
// copies: N*2
class Link
  {
  public long dData;
  public Link next;
  
  public Link(long dd)  // constructor
    { dData = dd; }
  }
///////////////////////////////////////////////////////
class SortedList
  {
  private Link first;	// first item in sorted list

  public SortedList()	// no arg constructor
    { first = null; }

  public SortedList(Link[] linkArr)	// constructor with array as arg
    {
    first = null;	// initialize list
    // copy all elements from the array to the linked list
    for(int j=0; j<linkArr.length; j++)
      insert( linkArr[j] );
    }

  public void insert(Link k)	// insert in order
    {
    Link previous = null;
    Link current = first;

    while(current != null && k.dData > current.dData)
      {
      // shift forward
      previous = current;
      current = current.next;
      }
    if(previous==null)	// if list is currently empty
      first = k;	// set first
    else
      previous.next = k;	// otherwise insert in between previous and current
    k.next = current;		// since we're always inserting before current
    }

  public Link remove()
    {
    Link temp = first;
    first = first.next;
    return temp;
    }
  }
///////////////////////////////////////////////////////////////////
class ListInsertionSortApp
  {
  public static void main(String[] args)
    {
    int size = 10;

    Link[] linkArray = new Link[size];	// create an array of Links

    // fill array with random numbers
    for(int j=0; j<size; j++)
      {
      int n = (int)(java.lang.Math.random()*99);
      Link newLink = new Link(n);
      linkArray[j] = newLink;
      }
    
    // print the unsorted array
    System.out.print("Unsorted array: ");
    for(int j=0; j<size; j++)
      System.out.print( linkArray[j].dData + " ");
    System.out.println("");

    // transfer the array to a sorted list
    SortedList theSortedList = new SortedList(linkArray);

    // repopulate the array with sorted list contents
    for(int j=0; j<size; j++)
      // 
      linkArray[j] = theSortedList.remove();

    // print the sorted array
    System.out.print("Sorted Array: ");
    for(int j=0; j<size; j++)
      System.out.print(linkArray[j].dData + " ");
    System.out.println("");
    }
  }
