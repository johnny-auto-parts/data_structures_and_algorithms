// quicksort1.java
// simple version of quicksort

class ArrayIns
  {
  private long[] theArray;
  private int nElems;

  public ArrayIns(int max)
    {
    theArray = new long[max];
    nElems = 0;
    }

  public void insert(long value)
    {
    theArray[nElems] = value;
    nElems++;
    }

  public void display()
    {
    System.out.print("A=");
    for(int j=0; j<nElems; j++)
      System.out.print(theArray[j] + " ");
    System.out.println("");
    }

  public void quickSort()
    {
    recQuickSort(0, nElems-1);
    }

  public void recQuickSort(int left, int right)
    {
    // base case: only one element to sort
    if(right-left <= 0)
      return;
    else
      {
      // always pivot on the rightmost element
      long pivot = theArray[right];
      
      // capture location where pivot element ends up residing
      int partition = partitionIt(left,right,pivot);
      
      // sort left side of partition
      recQuickSort(left,partition-1);
      // sort right side of partition
      recQuickSort(partition+1,right);
      }
    }

  public int partitionIt(int left, int right, long pivot)
    {
    int leftPtr = left - 1;
    int rightPtr = right; // right element is pivot, so we start pointer one element inward

    while(true)
      {
      // Scan for item bigger than the pivot
      while( theArray[++leftPtr] < pivot) // unlike right side scan, no need to
        ;                                 // check for end of the array b/c we know we'll hit the pivot
      // Scan for item smaller than the pivot
      while( rightPtr > 0 && theArray[--rightPtr] > pivot)
        ;

      if(leftPtr >= rightPtr) // if pointers cross, partition is done
        break;
      else
        swap(leftPtr,rightPtr);
      }
    // Put the pivot in its proper position
    swap(leftPtr,right);
    // Return location of the pivot
    return leftPtr;
    }

  public void swap(int dex1, int dex2)
    {
    long temp = theArray[dex1];
    theArray[dex1] = theArray[dex2];
    theArray[dex2] = temp;
    }
  }
/////////////////////////////////////////////////////////////////////////
class QuickSort1App
  {
  public static void main(String[] args)
    {
    int maxSize = 16;
    ArrayIns arr;
    arr = new ArrayIns(maxSize);

    for(int j=0; j<maxSize; j++)
      {
      long n = (int)(java.lang.Math.random()*99);
      arr.insert(n);
      }
    arr.display();
    arr.quickSort();
    arr.display();
    }
  }