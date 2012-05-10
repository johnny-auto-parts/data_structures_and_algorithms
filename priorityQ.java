// priorityQ.java
//  demonstrates a priority queue

class PriorityQ
  {
  // sorted array, from max at index 0 to min at size-1
  private int maxSize;
  private long[] queArray;
  private int nItems;

  public PriorityQ(int s)	//constructor
    {
    maxSize = s;
    queArray = new long[maxSize];
    nItems = 0;
    }

  public void insert(long item)
    {
    int j;

    if(nItems==0)
      queArray[nItems++] = item;
    else
      {
      for(j=nItems-1; j>=0;j--)	//start at end of array (lower numbers/higher priority)
        {
	if(item > queArray[j] )	// if our new item is larger
	  queArray[j+1] = queArray[j];	// shift the existing item upward
	else				// if its smaller
	  break;			// we're done shifting
	}
	queArray[j+1] = item;		// we're always inserting one above the loop index
	nItems++;
      }
    }

    public long remove()	//remove minimum item
      { return queArray[--nItems]; } //highest priority is at the highest index...grab and decrement
    
    public long peekMin()
      { return queArray[nItems-1]; } //simply look at the highest priority item

    public boolean isEmpty()
      { return (nItems==0); }

    public boolean isFull()
      { return (nItems == maxSize); }

  }
///////////////////////////////////////////////////////////////////////////////////////////////////////

class PriorityQApp
  {
  public static void main(String[] args)
    {
    PriorityQ thePQ = new PriorityQ(5);
    thePQ.insert(30);
    thePQ.insert(50);
    thePQ.insert(10);
    thePQ.insert(40);
    thePQ.insert(20);

    while ( !thePQ.isEmpty() )
      {
      long item = thePQ.remove();
      System.out.print(item + " ");	//10,20,30,40,50
      }
    System.out.println("");
    }
  }

