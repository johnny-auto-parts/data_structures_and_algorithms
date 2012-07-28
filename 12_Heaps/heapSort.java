// heapSort.java
// demonstrates heap sort
import java.io.*;
/////////////////////////////////////////////////////////////////
class Node
  {
  private int iData;
// -------------------------------------------------------------
  public Node(int key)
    { iData = key; }
// -------------------------------------------------------------
  public int getKey()
    { return iData; }
// -------------------------------------------------------------
  }
/////////////////////////////////////////////////////////////////
class Heap
  {
  private Node[] heapArray;
  private int maxSize;
  private int currentSize;
// -------------------------------------------------------------
  public Heap(int mx)
    {
    maxSize = mx;
    currentSize = 0;
    heapArray = new Node[maxSize];
    }
// -------------------------------------------------------------
  public Node remove()
    {
    Node root = heapArray[0];
    heapArray[0] = heapArray[--currentSize];
    trickleDown(0);
    return root;
    }
// -------------------------------------------------------------
  public void trickleDown(int index)
    {
    int largerChild;
    Node top = heapArray[index];
    
    // While we're not on the bottom row
    // !!! Items on the bottom row are already trickled down !!!
    while(index < currentSize/2)
      {
      int leftChild = 2*index+1;
      int rightChild = leftChild+1;
      
      // Determine larger child (if there is a right child at all)
      if(rightChild < currentSize && 
          heapArray[leftChild].getKey() < heapArray[rightChild].getKey() )
        largerChild = rightChild;
      else
        largerChild = leftChild;
      
      // If the item we're analyzing is larger than the larger child,
      //  break the loop
      if(top.getKey() >= heapArray[largerChild].getKey())
        break;
      
      // Otherwise perform the swap
      heapArray[index] = heapArray[largerChild];
      index = largerChild;
      }
    // put original item in the final hole we created
    heapArray[index] = top;
    }
// -------------------------------------------------------------
  public void displayHeap()
    {
    int nBlanks = 32;
    int itemsPerRow = 1;
    int column = 0;
    int j = 0;
    String dots = ".....................................";
    System.out.println(dots+dots);

    while(currentSize > 0)
      {
      if(column == 0)
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
// -------------------------------------------------------------
  public void displayArray()
    {
    for(int j=0; j<maxSize; j++)
      System.out.print(heapArray[j].getKey() + " ");
    System.out.println("");
    }
// -------------------------------------------------------------
  public void insertAt(int index, Node newNode)
    { heapArray[index] = newNode; }
// -------------------------------------------------------------
  public void incrementSize()
    { currentSize++; }
// -------------------------------------------------------------
  }
/////////////////////////////////////////////////////////////////
class HeapSort
  {
  public static void main(String[] args) throws IOException
    {
    int size, j;
    System.out.print("Enter number of items: ");
    size = getInt();
    Heap theHeap = new Heap(size);

    // Fill the array with random nodes
    for(j=0; j<size; j++)
      {
      int random = (int)(java.lang.Math.random()*100);
      Node newNode = new Node(random);
      theHeap.insertAt(j, newNode);
      theHeap.incrementSize();
      }
 
    // Display the array in its random form
    System.out.print("Random: ");
      theHeap.displayArray();

    // Make random array into a heap
    // We don't need to address bottom row
    // This is nice because we only need to call trickleDown() N/2 times
    for(j=size/2-1; j>=0; j--)
      theHeap.trickleDown(j);

    System.out.print("Heap:   ");
    theHeap.displayArray(); // display heap arry as array
    theHeap.displayHeap(); // display heap array as heap

    // Take Node from Top of the Heap
    // Insert it at last element in the array
    for(j=size-1; j>=0; j--)
      {
      Node biggestNode = theHeap.remove();
      theHeap.insertAt(j, biggestNode);
      }

    System.out.print("Sorted: ");
    theHeap.displayArray();
    }
// -------------------------------------------------------------
  public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }

  public static int getInt() throws IOException
    {
    String s = getString();
    return Integer.parseInt(s);
    }
// -------------------------------------------------------------
  }
