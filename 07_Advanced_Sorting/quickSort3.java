// quickSort3.java
// quick sort using insertion sort for smaller partitions
class ArrayIns
  {
  private long[] theArray;
  private int nElems;
//-----------------------------------------------------------------
  public ArrayIns(int max)
    {
    theArray = new long[max];
    nElems = 0;
    }
//-----------------------------------------------------------------
  public void insert(long value)
    {
    theArray[nElems] = value;
    nElems++;
    }
//-----------------------------------------------------------------
  public void display()
    {
    System.out.print("A=");
    for(int j=0; j<nElems; j++)
      System.out.print(theArray[j] + " ");
    System.out.println("");
    }
//-----------------------------------------------------------------
  public void quickSort()
    {
    recQuickSort(0, nElems-1);
    }
//-----------------------------------------------------------------
  public void recQuickSort(int left, int right)
    {
    // Determine number of elements to sort
    int size = right - left + 1;
    if(size < 10)
      insertionSort(left, right);
    else
      {
      // find median of 3
      long median = medianOf3(left, right);
      // partition the elements using the median of 3 as the pivot
      int partition = partitionIt(left, right, median);
      // sort the left and right side of the partition
      recQuickSort(left, partition-1);
      recQuickSort(partition+1, right);
      }
    }
//-----------------------------------------------------------------
  public long medianOf3(int left, int right)
    {
    // determine center element
    int center = (left+right)/2;
    // order left and center
    if( theArray[left] > theArray[center] )
      swap(left, center);
    // order left and right
    if( theArray[left] > theArray[right] )
      swap(left, right);
    // order center and right
    if( theArray[center] > theArray[right] )
      swap(center, right);

    /* The middle element (center) should become the pivot
     However, we know rightmost element is >= center...
     So we can store pivot at right-1 to save us a swap */
     swap(center, right-1);
     return theArray[right-1];
    }
//-----------------------------------------------------------------
  public void swap(int dex1, int dex2)
    {
    long temp = theArray[dex1];
    theArray[dex1] = theArray[dex2];
    theArray[dex2] = temp;
    }
//-----------------------------------------------------------------
  public int partitionIt(int left, int right, long pivot)
    {
    /* Since we already know the first element, the last element, and the pivot (2nd to last)
       don't need to be moved, we can start the pointers inside of all 3 of these elements. */
    int leftPtr = left;
    int rightPtr = right-1;
       
    while(true)
      {
      // advance left pointer until out of place element is found
      while( theArray[++leftPtr] < pivot )
        ;
      // advance right pointer until out of place element is found
      while( theArray[--rightPtr] > pivot )
        ;
      if(leftPtr >= rightPtr) // partition is done
        break;
      else
        swap(leftPtr, rightPtr);
      }
      // put pivot in between the partitions
      swap(leftPtr, right-1);
      // index at leftPtr is where the pivot now resides
      return leftPtr;
    }
//-----------------------------------------------------------------
  public void insertionSort(int left, int right)
    {
    int in, out;

    //first element on the left is sorted on its own, so we start at left + 1
    for(out=left+1; out<=right; out++)
      {
      long temp = theArray[out]; // store value of element we're assessing
      in = out; // store index of element we're assessing

      /* As long as:
         1. we're assessing an element to the right of where we ultimately started
         2. the element we're assessing is less than the element we're comparing it to
      */
      while(in>left && theArray[in-1] >= temp)
        {
        theArray[in] = theArray[in-1]; // shift existing item to the right (e.g. element 0 becomes element 1)
                                       // NOTE: in this statement we're shifting existing items...not placing the item in question
        --in; // go left to look at the next element within the sorted range
        }
      theArray[in] = temp; // insert the item in question where it belongs within the sorted region
      }
    }
//-----------------------------------------------------------------
  }
/////////////////////////////////////////////////////////////////////
class QuickSort3App
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