// insertSort.java

class ArrayIns 			//forget standard arrays, we'll make our own class
  {
  private long [] a;		//an array of long numbers
  private int nElems;		//number of elements in the array

  public ArrayIns(int max)	//constructor
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

  public void insertionSort()
    {
    int in, out;

    for(out=1; out<nElems; out++)	//out is dividing line btwn sorted & unsorted
      {
      long temp = a[out];		//save the item we're removing for reassignment
      in = out;				//this is where we'll start shifting everything right
      while(in>0 && a[in-1] >= temp) 	//Walk left as long as temp item is smaller or we have room to walk
        {
        a[in] = a[in-1];		//shift element to the right
	--in;				//go left one position
	}
	a[in] = temp;			//insert item we've been holding
      }

    }


  private void swap(int one, int two)
    {
    long temp = a[one];
    a[one] = a[two];
    a[two] = temp;
    }

  }
/////////////////////////////////////////////////////////////////////

class InsertSortApp 
  {
  public static void main(String[] args)
    {
    int maxSize = 100;		//this will be the array size
    ArrayIns arr;
    arr = new ArrayIns(maxSize);

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

    arr.insertionSort();

    arr.display();		//display sorted array
    }

  }

