// stackTriangle2.java
// evaluates triangular numbers using a stack instead of recursion
// more efficient version of stackTriangle.java

import java.io.*;

class StackX
  {
  private int maxSize;
  private int[] stackArray;
  private int top;

  public StackX(int s)
    {
    maxSize = s;
    stackArray = new int[maxSize];
    top = -1;
    }

  public void push(int p)
    {  stackArray[++top] = p; }

  public int pop()
    { return stackArray[top--]; }

  public int peek()
    { return stackArray[top]; }

  public boolean isEmpty()
    { return (top == -1); }

  }
///////////////////////////////////////////////////////////////////////
class StackTriangle2App
  {
  static int theNumber;
  static int theAnswer;
  static StackX theStack;

  public static void main(String[] args) throws IOException
    {
    System.out.print("Enter a number: ");
    theNumber = getInt();
    stackTriangle();
    System.out.println("Triangle="+theAnswer);
    }

  public static void stackTriangle()
    {
    theStack = new StackX(10000);  // largest number user is allowed to enter

    theAnswer = 0;

    while(theNumber > 0)
      {
      theStack.push(theNumber);
      --theNumber;
      }

    while( !theStack.isEmpty() )
      {
      int newN = theStack.pop();
      theAnswer += newN; // add to answer
      }
    }

  public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }

  public static int getInt() throws IOException
    {
    String s = getString();
    return Integer.parseInt(s);
    }

  }