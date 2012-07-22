// hashDouble.java
// hash table with double hashing
import java.io.*;
///////////////////////////////////////////////////////////
class DataItem
  {
  private int iData;
//--------------------------------------------------------
  public DataItem(int ii)
    { iData = ii; }
//--------------------------------------------------------
  public int getKey()
    { return iData; }
  }
///////////////////////////////////////////////////////////
class HashTable
  {
  private DataItem[] hashArray;
  private int arraySize;
  private DataItem nonItem;
//--------------------------------------------------------
  HashTable(int size)
    {
    arraySize = size;
    hashArray = new DataItem[arraySize];
    nonItem = new DataItem(-1);
    }

  public void displayTable()
    {
    System.out.print("Table: ");
    for(int j=0; j<arraySize; j++)
      {
      if(hashArray[j] != null)
        System.out.print(hashArray[j].getKey()+ " ");
      else
        System.out.print("** ");
      }
    System.out.println("");
    }
//--------------------------------------------------------
  public int hashFunc1(int key)
    {
    return key % arraySize;
    }
//--------------------------------------------------------
  public int hashFunc2(int key)
    {
    // non-zero
    // less than array size
    // different than hF1
    // array size must be prime to 5,4,3,2 (the possible outcomes)
    return 5 - key % 5;
    }
//--------------------------------------------------------
  public void insert(int key, DataItem item)
    {
    // assumes table is not full
    int hashVal = hashFunc1(key); //hash the key
    int stepSize = hashFunc2(key); // get step size

    // move the insertion point if necessary
    while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1)
      {
      hashVal += stepSize;  //step this many index points
      hashVal %= arraySize; //wraparound if necessary
      }
    // insert the object
    hashArray[hashVal] = item;
    }
//--------------------------------------------------------
  public DataItem delete(int key)
    {
    int hashVal = hashFunc1(key);
    int stepSize = hashFunc2(key);

    while(hashArray[hashVal] != null)
      {
      if(hashArray[hashVal].getKey() == key)
        {
        DataItem temp = hashArray[hashVal];
        hashArray[hashVal] = nonItem;
        return temp;
        }
      hashVal += stepSize;
      hashVal %= arraySize;
      }
    return null;
    }
//--------------------------------------------------------
  public DataItem find(int key)
    {
    //(assumes table not full)
    int hashVal = hashFunc1(key);
    int stepSize = hashFunc2(key);

    while(hashArray[hashVal] != null)
      {
      if(hashArray[hashVal].getKey() == key)
        return hashArray[hashVal];
      hashVal += stepSize;
      hashVal %= stepSize;
      }
    return null;
    }
//--------------------------------------------------------
  }
///////////////////////////////////////////////////////////
class HashDoubleApp
  {
  public static void main(String[] args) throws IOException
    {
    int aKey;
    DataItem aDataItem;
    int size, n;

    System.out.print("Enter size of hash table: ");
    size = getInt();
    System.out.print("Enter initial number of items: ");
    n = getInt();

    HashTable theHashTable = new HashTable(size);

    for(int j=0; j<n; j++)
      {
      aKey = (int)(java.lang.Math.random() * 2 * size);
      aDataItem = new DataItem(aKey);
      theHashTable.insert(aKey, aDataItem);
      }

    while(true)
      {
      System.out.print("Enter first letter of ");
      System.out.print("show, insert, delete, or find: ");
      char choice = getChar();
      switch(choice)
        {
        case 's':
          theHashTable.displayTable();
          break;
        case 'i':
          System.out.print("Enter key value to insert: ");
          aKey = getInt();
          aDataItem = new DataItem(aKey);
          theHashTable.insert(aKey, aDataItem);
          break;
        case 'd':
          System.out.print("Enter value to delete: ");
          aKey = getInt();
          theHashTable.delete(aKey);
          break;
        case 'f':
          System.out.print("Enter value to find: ");
          aKey = getInt();
          aDataItem = theHashTable.find(aKey);
          if(aDataItem != null)
            System.out.println("Found " + aKey);
          else
            System.out.println("Could not find " + aKey);
          break;
        default:
          System.out.print("Invalid entry\n");
        }
      }
    }

//-------------------------------------------------------- 
  public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }
//-------------------------------------------------------- 
  public static char getChar() throws IOException
    {
    String s = getString();
    return s.charAt(0);
    }
//-------------------------------------------------------- 
  public static int getInt() throws IOException
    {
    String s = getString();
    return Integer.parseInt(s);
    }
//-------------------------------------------------------- 
  }
///////////////////////////////////////////////////////////
