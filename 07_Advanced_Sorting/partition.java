// partitition.java
// partition an array

class ArrayPar
  {
  private long[] theArray;
  private int nElems;

  public ArrayPar(int max)
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

  public int size()
    { return nElems; }

  public int partitionIt(int left, int right, long pivot)
    {
    // We must start outside of the array indexes b/c we increment first
    int leftPtr = left-1;
    int rightPtr = right-1;

    while(true)
      {
      // Elements on the Left should be less than the partition value
      while(leftPtr < right && theArray[++leftPtr] <pivot)
        ; // nop (a.k.a. a no-operation statement)
 
      // Elements on the Right should be greater than the partition value
      while(rightPtr > left && theArray[--rightPtr] > pivot)
        ; // nop

      // If pointers cross, partition is done
      if(leftPtr >= rightPtr)
        break;
      else
        swap(leftPtr, rightPtr);
      }
    return leftPtr;
    }

  public void swap(int dex1, int dex2)  // swap two elements
    {
    long temp;
    temp = theArray[dex1];
    theArray[dex1] = theArray[dex2];
    theArray[dex2] = temp;
    }
  }
//////////////////////////////////////////////////////////////////////////////
class PartitionApp
  {
  public static void main(String[] args)
    {
    int maxSize = 16;
    ArrayPar arr;
    arr = new ArrayPar(maxSize);

    for(int j=0; j<maxSize; j++)
      {
      long n = (int)(java.lang.Math.random()*199);
      arr.insert(n);
      }
    
    // display un-partitioned array
    arr.display();

    long pivot = 99; // pivot value for partitioning
    System.out.print("Pivot is " + pivot);
    int size = arr.size();

    // partition array and find index of partition
    int partDex = arr.partitionIt(0,size-1,pivot);

    System.out.println(", Partition is at index " + partDex);
   
    // display partitioned array
    arr.display();

    }
  }