// hashChain.java
// hash table using separate chaining

import java.io.*;
/////////////////////////////////////////////////////////////////////
class Link
  {
  private int iData;
  public Link next;
//-------------------------------------------------------------------
  public Link(int it)
    { iData = it; }
//-------------------------------------------------------------------
  public int getKey()
    { return iData; }
//-------------------------------------------------------------------
  public void displayLink()
    { System.out.print(iData + " "); }
  }
/////////////////////////////////////////////////////////////////////
class SortedList
  {
  private Link first;
//-------------------------------------------------------------------
  public void SortedList()
    { first = null; }
//-------------------------------------------------------------------
  public void insert(Link theLink)
    {
    int key = theLink.getKey();
    Link previous = null;
    Link current = first;

    // move right as long as new item bigger than item under examination
    while( current != null && key > current.getKey() )
      {
      previous = current;
      current = current.next;
      }
      
    if(previous==null) // we didn't move
      first = theLink;
    else
      previous.next = theLink;
    theLink.next = current;
    }
//-------------------------------------------------------------------
  public void delete(int key)
    {
    Link previous = null;
    Link current = first;

    while( current != null && key != current.getKey() )
      {
      previous = current;
      current = current.next;
      }
    
    // delete the link
    if(previous==null)
      first = first.next;
    else
      previous.next = current.next;
    }
//-------------------------------------------------------------------
  public Link find(int key)
    {
    Link current = first;

    // since linked list is ordered, we don't need to check every link
    while(current != null && current.getKey() <= key)
      {
      if(current.getKey() == key)
        return current;
      current = current.next;
      }
    return null;
    }
//-------------------------------------------------------------------
  public void displayList()
    {
    System.out.print("List (first-->last): ");
    Link current = first;
    while(current != null)
      {
      current.displayLink();
      current = current.next;
      }
    System.out.println("");
    }
  }
/////////////////////////////////////////////////////////////////////
class HashTable
  {
  private SortedList[] hashArray;  // array of linked lists
  private int arraySize;
//-------------------------------------------------------------------
  public HashTable(int size)
    {
    arraySize = size;
   
    // create empty array to hold the lists
    hashArray = new SortedList[arraySize];

    // fill array with empty linked lists
    for(int j=0; j<arraySize; j++)
      hashArray[j] = new SortedList();
    }
//-------------------------------------------------------------------
  public void displayTable()
    {
    for(int j=0; j<arraySize; j++)
      {
      System.out.print(j + ". "); // display cell number
      hashArray[j].displayList(); // display list within that cell
      }
    }
//-------------------------------------------------------------------
  public int hashFunc(int key)
    {
    return key % arraySize;
    }
//-------------------------------------------------------------------
  public void insert(Link theLink) // insert a Link to the array of Linked Lists
    {
    int key = theLink.getKey();
    // determine what index we need to insert the link
    int hashVal = hashFunc(key);
    // add the link to the linked list at that element
    hashArray[hashVal].insert(theLink);
    }
//-------------------------------------------------------------------
  public void delete(int key)
    {
    int hashVal = hashFunc(key);
    hashArray[hashVal].delete(key); // delete link within linked list stored at hashVal
    }
//-------------------------------------------------------------------
  public Link find(int key)
    {
    // find array index to browse
    int hashVal = hashFunc(key);
    // go to that array index and browse through the links
    Link theLink = hashArray[hashVal].find(key);
    return theLink;
    }
//-------------------------------------------------------------------
  }
/////////////////////////////////////////////////////////////////////
class HashChainApp
  {
  public static void main(String[] args) throws IOException
    {
    int aKey;
    Link aDataItem;
    int size, n, keysPerCell = 100;

    System.out.print("Enter size of the hash table: ");
    size = getInt();
    System.out.print("Enter initial number of items: ");
    n = getInt();

    HashTable theHashTable = new HashTable(size);

    for(int j=0; j<n; j++)
      {
      aKey = (int)(java.lang.Math.random() * keysPerCell * size);
      aDataItem = new Link(aKey);
      theHashTable.insert(aDataItem);
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
          aDataItem = new Link(aKey);
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
            System.out.println("Found " + aKey);
          else
            System.out.println("Could not find " + aKey);
          break;
        default:
          System.out.print("Invalid entry\n");
        }
      }
    }
//-------------------------------------------------------------------
  public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }
//-------------------------------------------------------------------
  public static char getChar() throws IOException
    {
    String s = getString();
    return s.charAt(0);
    }
//-------------------------------------------------------------------
  public static int getInt() throws IOException
    {
    String s = getString();
    return Integer.parseInt(s);
    }
//-------------------------------------------------------------------
  }
/////////////////////////////////////////////////////////////////////
