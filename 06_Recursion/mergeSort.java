// mergeSort.java
// recursive merge sort

class DArray
  {
  private long[] theArray;
  private int nElems;

  public DArray(int max)
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
    for(int j=0; j<nElems; j++)
      System.out.print(theArray[j] + " ");
    System.out.println("");
    }

  public void mergeSort()
    {
    long[] workSpace = new long[nElems];
    recMergeSort(workSpace, 0, nElems-1);
    }

  // Divide arrays in half continuously until array is one element
  public void recMergeSort(long[] workSpace, int lowerBound, int upperBound)
    {
    if(lowerBound==upperBound)
      return;
    else
      {
      // 1. Find Middle Element
       int mid = (lowerBound+upperBound) / 2;
      
      // 2. Sort Low Half
       recMergeSort(workSpace, lowerBound, mid);
      
      // 3. Sort High Half
       recMergeSort(workSpace, mid+1, upperBound);
      
      // 4. Merge Them
       merge(workSpace, lowerBound, mid+1, upperBound);
      }
    }

  // Merge Two Sorted Arrays
  public void merge(long[] workSpace, int lowPtr, int highPtr, int upperBound)
    {
    int j = 0;                       // workspace index 
    int lowerBound = lowPtr;
    int mid = highPtr - 1;
    int n = upperBound-lowerBound+1; // # of items

    while(lowPtr <= mid && highPtr <= upperBound)
      if( theArray[lowPtr] < theArray[highPtr])
        workSpace[j++] = theArray[lowPtr++];
      else
        workSpace[j++] = theArray[highPtr++];
      
    while(lowPtr <= mid)
      workSpace[j++] = theArray[lowPtr++];

    while(highPtr <= upperBound)
      workSpace[j++] = theArray[highPtr++];

    for(j=0; j<n; j++)
      theArray[lowerBound+j] = workSpace[j];
    }
  }
///////////////////////////////////////////////////////////////////////////////////
class MergeSortApp
  {
  public static void main(String[] args)
    {
    int maxSize = 100;
    DArray arr;
    arr = new DArray(maxSize);

    arr.insert(64);
    arr.insert(21);
    arr.insert(33);
    arr.insert(70);
    arr.insert(12);
    arr.insert(85);
    arr.insert(44);
    arr.insert(3);
    arr.insert(99);
    arr.insert(0);
    arr.insert(108);
    arr.insert(36);

    arr.display();

    arr.mergeSort();

    arr.display();
    }
  }