// queue.java

class Queue
  {
  private int maxSize;
  private long[] queArray;
  private int front;
  private int rear;
  private int nItems;

  public Queue(int s)		//constructor
    {
    maxSize = s;
    queArray = new long[maxSize];
    front = 0;
    rear = -1;
    nItems = 0;
    }

  public void insert(long j)	//put item at rear of queue
    {
    if(rear == maxSize-1)	//deal with wraparound
      rear = -1;
    queArray[++rear] = j;	//increment rear and insert j
    nItems++;
    }

  public long remove()			//take item from the front of the queue
    {
    long temp = queArray[front++];	//get value and increment front
    if(front == maxSize)		//deal with wraparound
      front = 0;
    nItems--;
    return temp;
    }

  public long peekFront()	//peek at the front of the queue
    {
    return queArray[front];
    }

  public boolean isEmpty()
    {
    return (nItems==0);
    }

  public boolean isFull()
    {
    return (nItems==maxSize);
    }

  public int size()
    {
    return nItems;
    }

  }

///////////////////////////////////////////////////////////////////////////

class QueueApp
  {
  public static void main(String[] args)
    {
    Queue theQueue = new Queue(5);	//queue will hold 5 items

    //insert 4 items
    theQueue.insert(10);
    theQueue.insert(20);
    theQueue.insert(30);
    theQueue.insert(40);

    //remove 3 items (10/20/30)
    theQueue.remove();
    theQueue.remove();
    theQueue.remove();

    //insert 4 more items (this will wrap around)
    theQueue.insert(50);
    theQueue.insert(60);
    theQueue.insert(70);
    theQueue.insert(80);

    //remove everything one by one and print it
    while( !theQueue.isEmpty() )
      {
      long n = theQueue.remove();
      System.out.print(n);
      System.out.print(" ");
      }

    System.out.println("");
    }
  }
