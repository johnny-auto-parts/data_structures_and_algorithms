// bubbleSort.java

class ArrayBub			//forget standard arrays, we'll make our own class
  {
  private long [] a;		//an array of long numbers
  private int nElems;		//number of elements in the array

  public ArrayBub(int max)	//constructor
    {
    a = new long[max]; 		//create the array declared earlier
    nElems = 0;			//the number of elements starts at 0
    }

  public void insert(long value) //adds element into the array
    {
    a[nElems] = value;		// insert it
    nElems++;			// keep track of the size
    }

  public void display()		//display array contents
    {
    for(int j=0; j<nElems; j++)
      System.out.print(a[j] + " ");
    System.out.println("");
    }

  public void bubbleSort()
    {
    int out, in;

    for(out=nElems-1; out>1; out--)	//outer loop (backwards b/c sorts bubble to the right)
      for(in=0; in<out; in++)		//compare leftmost to its righthand neighbor
        if( a[in] > a[in+1] )		//if left element > right element
	  swap(in, in+1);		//swap them using our home made method
    }

  private void swap(int one, int two)
    {
    long temp = a[one];
    a[one] = a[two];
    a[two] = temp;
    }

  }
/////////////////////////////////////////////////////////////////////
  class BubbleSortApp
    {
    public static void main(String[] args)
      {
      int maxSize = 100;		//this will be the array size
      ArrayBub arr;
      arr = new ArrayBub(maxSize);

      arr.insert(77);
      arr.insert(99);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      arr.insert(88);
      arr.insert(11);
      arr.insert(00);
      arr.insert(66);
      arr.insert(33);

      arr.display();		//display unsorted array

      arr.bubbleSort();

      arr.display();		//display sorted array
      }

    }

