// stackTriangle.java
// evaluates triangular numbers, using a stack instead of recursion

import java.io.*;

class Params
  {
  public int n;
  public int returnAddress;
  
  public Params(int nn, int ra)
    {
    n=nn;
    returnAddress=ra;
    }

  }
////////////////////////////////////////////////////////////////////////
class StackX
  {
  private int maxSize;
  private Params[] stackArray;
  private int top;

  public StackX(int s)
    {
    maxSize = s;
    stackArray = new Params[maxSize];
    top = -1;
    }

  public void push(Params p)
    {
    stackArray[++top] = p;
    }

  public Params pop()
    {
    return stackArray[top--];
    }

  public Params peek()
    {
    return stackArray[top];
    }
  }
///////////////////////////////////////////////////////////////
class StackTriangeApp
  {
  static int theNumber;
  static int theAnswer;
  static StackX theStack;
  static int codePart;
  static Params theseParams;

  public static void main(String[] args) throws IOException
    {
    System.out.print("Enter a number: ");
    theNumber = getInt();
    recTriangle();
    System.out.println("Triangle=" + theAnswer);
    } 

  public static void recTriangle()
    {
    theStack = new StackX(10000);
    codePart = 1;

    // call step() until it's true
    while( step() == false)
      ;
    }

  public static boolean step()
    {
    switch(codePart)
      {
      // initial call
      case 1:  
        theseParams = new Params(theNumber, 6);
        theStack.push(theseParams);
        codePart = 2;
        break;
      // method entry
      case 2:
        theseParams = theStack.peek();
        if(theseParams.n == 1)
          {
          theAnswer = 1;
          codePart = 5;  // exit
          }
        else
          codePart = 3;  // recursive call
        break;
      // method call
      case 3:
        Params newParams = new Params(theseParams.n - 1, 4);
        theStack.push(newParams);
        codePart = 2; // go enter method
        break;
      // calculation
      case 4:    
        theseParams = theStack.peek();
        theAnswer = theAnswer + theseParams.n;
        codePart = 5;
        break;
      // method exit
      case 5:
        theseParams = theStack.peek();
        codePart = theseParams.returnAddress; // (4 or 6)
        theStack.pop();
        break;
      // return point
      case 6:
        return true;
      }
    return false;
    }
//////////////////////////////////////////////////////////////////////////////
  public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }
//////////////////////////////////////////////////////////////////////////////
  public static int getInt() throws IOException
    {
    String s = getString();
    return Integer.parseInt(s);
    }

  }

