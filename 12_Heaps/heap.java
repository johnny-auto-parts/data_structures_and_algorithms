// heap.java
// demonstrates heaps

import java.io.*;
////////////////////////////////////////////////////////////////////
class Node
  {
  private int iData;
// -----------------------------------------------------------------
  public Node(int key)
    { iData = key; }
// -----------------------------------------------------------------
  public int getKey()
    { return iData; }
// -----------------------------------------------------------------
  public void setKey(int id)
    { iData = id; }
// -----------------------------------------------------------------
  }
////////////////////////////////////////////////////////////////////
class Heap
  {
  private Node[] heapArray;
  private int maxSize;           // biggest we allow the heap to grow
  private int currentSize;       // how much is currently taken
// -----------------------------------------------------------------
  public Heap(int mx)
    {
    // constructor
    maxSize = mx;
    currentSize = 0;
    heapArray = new Node[maxSize]; // create empty array
    }
// -----------------------------------------------------------------
  public boolean isEmpty()
    { return currentSize==0; }
// -----------------------------------------------------------------
  public boolean insert(int key)
    {
    if(currentSize==maxSize)
      return false;
    Node newNode = new Node(key);
    heapArray[currentSize] = newNode;
    trickleUp(currentSize++);
    return true;
    }
// -----------------------------------------------------------------
  public void trickleUp(int index)
    {
    int parent = (index - 1) / 2;  // formula to find parent in a binary tree array
    Node bottom = heapArray[index];

    while( index > 0 && heapArray[parent].getKey() < bottom.getKey() )
      // 1. We're not at the root (we wouldn't be able to go up any more)
      // 2. The key we're trickling up must be bigger than its parent
      {
      heapArray[index] = heapArray[parent];   // move the parent down a level
      index = parent;    // set new index where the parent use to be
      parent = (parent-1) / 2;   // calculate new parent
      }
    heapArray[index] = bottom;  // put our original element in the final "hole"
    }
// -----------------------------------------------------------------
  public Node remove()
    {
    // We always remove what is on top (it is a heap after all!)
    Node root = heapArray[0];
    heapArray[0] = heapArray[--currentSize];  // put last element at the root
    trickleDown(0);
    return root; // return the element we detached for processing
    }
// -----------------------------------------------------------------
  public void trickleDown(int index)
    // more complicated than trickleUp since we have to compare
    // two children instead of looking at one parent
    {
    int largerChild;
    Node top = heapArray[index];      // save element where we start
    
    // while node has at least one child
    while(index < currentSize/2)
      {
      int leftChild = 2*index+1;
      int rightChild = leftChild+1;

      // if 1. right child exists and 2. left child is smaller than right child
      if(rightChild < currentSize && 
           heapArray[leftChild].getKey() < heapArray[rightChild].getKey() )
        largerChild = rightChild;
      else
        largerChild = leftChild;
      
      // if what we want to move is bigger than both of its children, break the loop 
      if( top.getKey() >= heapArray[largerChild].getKey() )
        break;

      // shift larger child up
      heapArray[index] = heapArray[largerChild];
      // re-enter the loop with the index at the larger child position
      index = largerChild;
      }
    // put the original element in the hole we created
    heapArray[index] = top;
    }
// -----------------------------------------------------------------
  public boolean change(int index, int newValue)
    {
    if(index<0 || index>=currentSize)
      return false;
    int oldValue = heapArray[index].getKey();  // save old value
    heapArray[index].setKey(newValue);  // set new value

    if(oldValue < newValue)
      trickleUp(index);
    else
      trickleDown(index);
    return true;
    }
// -----------------------------------------------------------------
  public void displayHeap()
    {
    System.out.print("heapArray: ");
    for(int m=0; m<currentSize; m++)
      if(heapArray[m] != null)
        System.out.print( heapArray[m].getKey() + " ");
      else
        System.out.print( "-- ");
    System.out.println();

    int nBlanks = 32;
    int itemsPerRow = 1;
    int column = 0;
    int j = 0;
    String dots = "...........................";
    System.out.println(dots+dots);

    while(currentSize > 0) // for each heap item
      {
      if(column == 0)     // first item in row?
        for(int k=0; k<nBlanks; k++)
          System.out.print(' ');

      System.out.print(heapArray[j].getKey());

      if(++j == currentSize)
        break;

      if(++column==itemsPerRow)
        {
        nBlanks /= 2;
        itemsPerRow *= 2;
        column = 0;
        System.out.println();
        }
      else
        for(int k=0; k<nBlanks*2-2; k++)
          System.out.print(' ');
      }
    System.out.println("\n"+dots+dots);
    }
// -----------------------------------------------------------------
  }
////////////////////////////////////////////////////////////////////
class HeapApp
  {
  public static void main(String[] args) throws IOException
    {
    int value, value2;
    Heap theHeap = new Heap(31);
    boolean success;

    theHeap.insert(70);
    theHeap.insert(40);
    theHeap.insert(50);
    theHeap.insert(20);
    theHeap.insert(60);
    theHeap.insert(100);
    theHeap.insert(80);
    theHeap.insert(30);
    theHeap.insert(10);
    theHeap.insert(90);

    while(true)
      {
      System.out.print("Enter first letter of ");
      System.out.print("show, insert, remove, change: ");
      int choice = getChar();
      switch(choice)
        {
        case 's':                 // show
          theHeap.displayHeap();
          break;
        case 'i':                 // insert
          System.out.print("Enter value to insert: ");
          value = getInt();
          success = theHeap.insert(value);
          if( !success)
            System.out.println("Can't insert, heap full");
          break;
        case 'r':                 // remove
          if( !theHeap.isEmpty() )
            theHeap.remove();
          else
            System.out.println("Can't remove; heap empty");
          break;
        case 'c':                // change
          System.out.print("Enter current index of item: ");
          value = getInt();
          System.out.print("Enter new key: ");
          value2 = getInt();
          success = theHeap.change(value, value2);
          if( !success )
            System.out.println("Invalid index");
          break;
        default:
          System.out.println("Invalid entry\n");
        }
      }
    }
// -----------------------------------------------------------------
  public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }
// -----------------------------------------------------------------
  public static char getChar() throws IOException
    {
    String s = getString();
    return s.charAt(0);
    }
// -----------------------------------------------------------------
  public static int getInt() throws IOException
    {
    String s = getString();
    return Integer.parseInt(s);
    }
// -----------------------------------------------------------------
  }
////////////////////////////////////////////////////////////////////
