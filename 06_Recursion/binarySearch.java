// binarySearch.java
// recursive binary search

class ordArray
  {
  // the array and its count of elements
  private long[] a;
  private int nElems;

  // constructor
  public ordArray(int max)
    {
    a = new long[max];
    nElems = 0;
    }

  public int size()
    { return nElems; }

  // check to see if element exists
  public int find(long searchKey)
    {
    return recFind(searchKey, 0, nElems -1);
    }

  private int recFind(long searchKey, int lowerBound, int upperBound)
    {
    int curIn;

    curIn = (lowerBound + upperBound) / 2;
    if(a[curIn]==searchKey)
      return curIn;  // found it
    else if(lowerBound > upperBound)
      return nElems; // can't find it
    else
      {
      if(a[curIn] < searchKey)
        // if the searchKey is higher, its in the top half
        return recFind(searchKey, curIn+1, upperBound);
      else
        // if the searchKey is lower, its in the bottom half
        return recFind(searchKey, lowerBound, curIn-1);
      }
    }

  // insert element into array (its an ordered array)
  public void insert(long value)
    {
    int j;
    // walk through each element until you find one greater than what you're inserting
    for(j=0; j<nElems; j++)
      if(a[j] > value) // the jth position is where the new value should go
        break;
    // before you insert it, shift all elements up one place
    for(int k=nElems; k>j; k--)
      a[k] = a[k-1];
    // insert new value
    a[j] = value;
    // keep track of count
    nElems++;
    }  

  public void display()
    {
    for(int j=0; j<nElems; j++)
      System.out.print(a[j] + " ");
    System.out.println("");
    }
  }
///////////////////////////////////////////////////////////////////////////
class BinarySearchApp
  {
  public static void main(String[] args)
    {
    int maxSize = 100;
    ordArray arr;
    arr = new ordArray(maxSize);

    arr.insert(72);
    
    arr.insert(90);
    arr.insert(45);
    arr.insert(126);
    arr.insert(54);
    arr.insert(99);
    arr.insert(144);
    arr.insert(27);
    arr.insert(135);
    arr.insert(81);
    arr.insert(18);
    arr.insert(108);
    arr.insert(9);
    arr.insert(117);
    arr.insert(63);
    arr.insert(36);
    
    arr.display();

    int searchKey = 27;
    if( arr.find(searchKey) != arr.size() ) // remember, the find method returns nElements if key not found
      System.out.println("Found " + searchKey);
    else
      System.out.println("Can't find " + searchKey);
    }
  }