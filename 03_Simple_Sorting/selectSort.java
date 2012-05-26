// selectSort.java

class ArraySel //forget standard arrays, we'll make our own class
  {
  private long [] a;		//an array of long numbers
  private int nElems;		//number of elements in the array

  public ArraySel(int max)	//constructor
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

  public void selectionSort()
    {
    int out, in, min;

    for(out=0; out<nElems-1; out++)	//normal outer loop
      {
      min = out;  			//save min element index
      for(in=out+1; in<nElems; in++)  	//scan the array to the right
        if(a[in] < a[min])		//if an element is lower than the current min
	  min = in;			//alter the min variable
	swap(out,min);			//swap the final min to the location we're on
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

class SelectSortApp 
  {
  public static void main(String[] args)
    {
    int maxSize = 100;		//this will be the array size
    ArraySel arr;
    arr = new ArraySel(maxSize);

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

    arr.selectionSort();

    arr.display();		//display sorted array
    }

  }

