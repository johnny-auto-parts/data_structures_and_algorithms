// tree234.java
// demonstrates 234 tree
import java.io.*;
///////////////////////////////////////////////////////////////////////////
class DataItem
  {
  public long dData;
//------------------------------------------------------------------------
  public DataItem(long dd)  // constructor
    { dData = dd; }
//------------------------------------------------------------------------
  public void displayItem()
    { System.out.print("/"+dData); } 
  }
///////////////////////////////////////////////////////////////////////////
class Node
  {
  private static final int ORDER = 4; // maximum number of children a node can have
  private int numItems;
  private Node parent;
  private Node childArray[] = new Node[ORDER]; // reference to the node's children
                                               // a node can have up to 4 children
  private DataItem itemArray[] = new DataItem[ORDER-1]; // reference to the data items within the node itself
                                                        // a node can have up to three data items within itself
//------------------------------------------------------------------------
  // connect child to this node
  public void connectChild(int childNum, Node child)
    {
    childArray[childNum] = child;
    if(child != null)
      child.parent = this; // whatever object is invoking this method should be the parent
    }
//------------------------------------------------------------------------
  // disconnect child from node (and return it)
  public Node disconnectChild(int childNum)
    {
    Node tempNode = childArray[childNum];
    childArray[childNum] = null;
    return tempNode;
    }
//------------------------------------------------------------------------
  public Node getChild(int childNum)
    { return childArray[childNum]; }
//------------------------------------------------------------------------
  public Node getParent()
    { return parent; }
//------------------------------------------------------------------------
  public boolean isLeaf()
    { return (childArray[0]==null) ? true : false; }
//------------------------------------------------------------------------
  public int getNumItems()
    { return numItems; }
//------------------------------------------------------------------------
  public DataItem getItem(int index) // get DataItem at a given index
    { return itemArray[index]; }
//------------------------------------------------------------------------
  public boolean isFull()
    { return (numItems==ORDER-1) ? true : false; }
//------------------------------------------------------------------------
  public int findItem(long key) // we're searching just one node here!
    {
    for(int j=0; j<ORDER-1; j++)
      {
      if(itemArray[j] == null) // once we see a null element, nothing remains
        break;                 // since data items must occupy lower indexes
      else if(itemArray[j].dData == key)
        return j;
      }
    return -1;
    }
//------------------------------------------------------------------------
  public int insertItem(DataItem newItem)
    {
    // assumes node is not full
    numItems++;
    long newKey = newItem.dData;

    for(int j=ORDER-2; j>=0; j--) // start on right at max element (2)
      {
      if(itemArray[j] == null)
        continue; // empty space
      else
        {
        long itsKey = itemArray[j].dData; // this is a key already present in the node
        if(newKey < itsKey)
          itemArray[j+1] = itemArray[j]; // shift it right
        else
          {
          itemArray[j+1] = newItem; // insert new item
          return j+1;
          }
        }
      }
    // We'll only reach this block if nothing is in the node at all
    itemArray[0] = newItem;
    return 0;
    }
//------------------------------------------------------------------------
  public DataItem removeItem()  // remove largest item
    {
    // assumes node not empty
    DataItem temp = itemArray[numItems-1];  // save it for the return value
    itemArray[numItems-1] = null;           // disconnect it
    numItems--;
    return temp;
    }
//------------------------------------------------------------------------
  public void displayNode()  // format: /24/56/76/
    {
    for(int j=0; j<numItems; j++)
      itemArray[j].displayItem();          // "/56"
    System.out.println("/");               // final "/"
    }
//------------------------------------------------------------------------
  }
///////////////////////////////////////////////////////////////////////////
class Tree234
  {
  private Node root = new Node();         // make root node
//------------------------------------------------------------------------
  public int find(long key)
    {
    Node curNode = root;
    int childNumber;
    while(true)
      {
      if(( childNumber=curNode.findItem(key) ) != -1 )
        return childNumber;              // found it!
      else if( curNode.isLeaf() )
        return -1;                       // can't find it and we can't go deeper
      else
        curNode = getNextChild(curNode, key);  // method defined further down
      }
    }
//------------------------------------------------------------------------
  public void insert(long dValue)
    {
    Node curNode = root;
    DataItem tempItem = new DataItem(dValue);

    while(true)
      {
      if (curNode.isFull() )
        {
        split(curNode);
        // Once we perform a split, current level changes significantly so we have to go back up one level
        curNode = curNode.getParent();
        curNode = getNextChild(curNode, dValue);
        }
      else if( curNode.isLeaf() )
        break; // go insert
      else
        curNode = getNextChild(curNode, dValue);
      }
      // Actually perform the insert
      curNode.insertItem(tempItem);
    }
//------------------------------------------------------------------------
  public void split(Node thisNode)
    {
    // assumes node is full
    DataItem itemB, itemC;
    Node parent, child2, child3;
    int itemIndex;

    // Remove rightmost 2 data items from Node
    itemC = thisNode.removeItem();
    itemB = thisNode.removeItem();
    // Remove rightmost 2 children from Node
    child2 = thisNode.disconnectChild(2);
    child3 = thisNode.disconnectChild(3);
    // Create new Node
    Node newRight = new Node();

    if(thisNode==root)
      {
      root = new Node();  // we'll need an extra new node
      parent = root;
      root.connectChild(0, thisNode); //node we're splitting becomes left child of new root
      }
    else
      parent = thisNode.getParent();

    // Item B always go to the parent
    itemIndex = parent.insertItem(itemB);

    int n = parent.getNumItems();

    // Shift the parent's child connection one to the right
    for(int j=n-1; j>itemIndex; j--)
      {
      Node temp = parent.disconnectChild(j);
      parent.connectChild(j+1, temp);
      }    
    // Connect new node to the far right
    parent.connectChild(itemIndex+1, newRight);

    // Polish our new right node
    newRight.insertItem(itemC);
    newRight.connectChild(0, child2);
    newRight.connectChild(1, child3);
    }
//------------------------------------------------------------------------
  public Node getNextChild(Node theNode, long theValue)
    {
    // Moves to appropriate child during a search for a value that isn't in that node
    // Assumption: node not empty, not full, not a leaf
    int j;
    int numItems = theNode.getNumItems();
    
    for(j=0; j<numItems; j++)
      {
      // If the value is less than the node we're iterating on,
      // the child number we want to return is the same as j's value
      if( theValue < theNode.getItem(j).dData )
        return theNode.getChild(j);
      }
    // If the value is greater than everything, return rightmost child
    return theNode.getChild(j);
    }
//------------------------------------------------------------------------
  public void displayTree()
    {
    recDisplayTree(root,0,0);
    }
//------------------------------------------------------------------------
  public void recDisplayTree(Node thisNode, int level, int childNumber)
    {
    System.out.print("level="+level+" child="+childNumber+" ");
    thisNode.displayNode();

    // call ourselves for each child of this node
    int numItems = thisNode.getNumItems();
    // each node will have one more child than its number of items
    for(int j=0; j<numItems+1; j++)
      {
      Node nextNode = thisNode.getChild(j);
      if(nextNode != null)
        recDisplayTree(nextNode, level + 1, j); // since level is argument to the method,
                                                // it will incremental continually as we
                                                // drill down to each level
      else
        return;
      }
    }
//------------------------------------------------------------------------
  }
///////////////////////////////////////////////////////////////////////////
class Tree234App
  {
  public static void main(String[] args) throws IOException
    {
    long value;
    Tree234 theTree = new Tree234();

    theTree.insert(50);
    theTree.insert(40);
    theTree.insert(60);
    theTree.insert(30);
    theTree.insert(70);

    while(true)
      {
      System.out.print("Enter first letter of ");
      System.out.print("show, insert, or find: ");
      char choice = getChar();
      switch(choice)
        {
        case 's':
          theTree.displayTree();
          break;
        case 'i':
          System.out.print("Enter value to insert: ");
          value = getInt();
          theTree.insert(value);
          break;
        case 'f':
          System.out.print("Enter value to find: ");
          value = getInt();
          int found = theTree.find(value);
          if(found != -1)
            System.out.println("Found "+value);
          else
            System.out.println("Could not find "+value);
          break;
        default:
          System.out.print("Invalid entry\n");
        }
      }
    }
//------------------------------------------------------------------------
  public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }
//------------------------------------------------------------------------
  public static char getChar() throws IOException
    {
    String s = getString();
    return s.charAt(0);
    }
//------------------------------------------------------------------------
  public static int getInt() throws IOException
    {
    String s = getString();
    return Integer.parseInt(s);
    }
//------------------------------------------------------------------------
  }
///////////////////////////////////////////////////////////////////////////
