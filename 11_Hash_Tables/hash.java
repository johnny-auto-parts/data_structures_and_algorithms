// hash.java
// hash table with linear probing

import java.io.*;
////////////////////////////////////////////////////////////////////
class DataItem
  {
  private int iData;

  public DataItem(int ii)
    { iData = ii; }

  public int getKey()
    { return iData; }

  }
////////////////////////////////////////////////////////////////////
class HashTable
  {
  private DataItem[] hashArray;  // holds hash table
  private int arraySize;
  private DataItem nonItem;  // for deleted items

// ---------------------------------------------------------------
  public HashTable(int size)
    {
    arraySize = size;
    hashArray = new DataItem[arraySize];
    nonItem = new DataItem(-1);  // deleted item key is -1
    }
// ---------------------------------------------------------------
  public void displayTable()
    {
    System.out.print("Table: ");
    for(int j=0; j<arraySize; j++)
      {
      if(hashArray[j] != null)
        System.out.print(hashArray[j].getKey() + " ");
      else
        System.out.print("** ");
      }
    System.out.println("");
    }
// ---------------------------------------------------------------
  public int hashFunc(int key)
    {
    // our hash function
    return key % arraySize;
    }
// ---------------------------------------------------------------
  public void insert(DataItem item)
    {
    // assumes table not full
    
    // extract the key from the object
    int key = item.getKey();
    // hash the key
    int hashVal = hashFunc(key);

    while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1)
      {
      // go to the next cell
      ++hashVal; 
      // Wraparound if necessary
       /* As long as the index is smaller than the size of the array
          the index % arraySize = the index itself
          once the index reaches the array size,
          index % arraySize = 0          */
      hashVal %= arraySize; 
      
      }
    // perform the actual insert
    hashArray[hashVal] = item;
    }
// ---------------------------------------------------------------
  public DataItem delete(int key)
    {
    int hashVal = hashFunc(key);

    while(hashArray[hashVal] != null)
      {
      if(hashArray[hashVal].getKey() == key)
        {
        DataItem temp = hashArray[hashVal]; // save item
        hashArray[hashVal] = nonItem;       // delete it
        return temp;
        }
      ++hashVal;
      hashVal %= arraySize; // wraparound
      }
    return null;
    }
// ---------------------------------------------------------------
  public DataItem find(int key)
    {
    int hashVal = hashFunc(key);

    while(hashArray[hashVal] != null)
      {
      if(hashArray[hashVal].getKey() == key)
        return hashArray[hashVal];
      ++hashVal;
      hashVal %= arraySize;
      }
    return null;
    }
  }
////////////////////////////////////////////////////////////////////
class HashTableApp
  {
  public static void main(String[] args) throws IOException
    {
    DataItem aDataItem;
    int aKey, size, n, keysPerCell;

    System.out.print("Enter size of hash table: ");
    size = getInt();
    System.out.print("Enter initial number of items: ");
    n = getInt();
    // Ratio of the range of keys to hash table size
    // e.g. if table size is 20, keys range from 0 to 200
    keysPerCell = 10;

    HashTable theHashTable = new HashTable(size);

    for(int j=0; j<n; j++)
      {
      aKey = (int)(java.lang.Math.random() * keysPerCell * size);
      aDataItem = new DataItem(aKey);
      theHashTable.insert(aDataItem);
      }

    while(true)
      {
      System.out.print("Enter first letter of ");
      System.out.print("show, insert, delete or find: ");
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
          theHashTable.insert(aDataItem);
          break;
        case 'd':
          System.out.print("Enter key value to delete: ");
          aKey = getInt();
          theHashTable.delete(aKey);
          break;
        case 'f':
          System.out.print("Enter key value to find: ");
          aKey = getInt();     
          aDataItem = theHashTable.find(aKey);
          if(aDataItem != null)
            {
            System.out.println("Found " + aKey);
            }
          else
            System.out.println("Could not find " + aKey);
          break;
        default:
          System.out.print("Invalid entry\n");
        }
      }
    }
// ---------------------------------------------------------------
  public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }
// ---------------------------------------------------------------
  public static char getChar() throws IOException
    {
    String s = getString();
    return s.charAt(0);
    }
// ---------------------------------------------------------------
  public static int getInt() throws IOException
    {
    String s = getString();
    return Integer.parseInt(s);
    }
  }
////////////////////////////////////////////////////////////////////
