// reverse.java
// use a stack to reverse a string

import java.io.*;		//necessary for I/O

class StackX
  {
  private int maxSize;
  private char[] stackArray;
  private int top;

  public StackX(int max)	//constructor
    {
    maxSize = max;
    stackArray = new char[maxSize];
    top = -1;
    }

  public void push(char j)	//put item on top of stack
    {
    stackArray[++top] = j;
    }

  public char pop()		//take item from top of stack
    {
    return stackArray[top--];
    }

  public char peek()		//peek at top of stack
    {
    return stackArray[top];
    }

  public boolean isEmpty()	//true if stack is empty
    {
    return (top == -1);
    }

  }

///////////////////////////////////////////////////////////////

class Reverser
  {
  private String input;		
  private String output;

  public Reverser(String in)	//constructor
    { input = in; }

  public String doRev()		//reverse the string
    {
    int stackSize = input.length();	//get max stack size
    StackX theStack = new StackX(stackSize);

    for(int j=0; j<input.length(); j++)
      {
      char ch = input.charAt(j);	//get a character from input
      theStack.push(ch);		//push it to the stack
      }

    output = "";
    
    while( !theStack.isEmpty() )
      {
      char ch = theStack.pop();		//pop a character
      output = output + ch;		//append to output
      }

    return output;
    }
  }

/////////////////////////////////////////////////////////////////

class ReverseApp
  {
  public static void main(String[] args) throws IOException
  {
  
  String input, output;
  
  while (true)
    {
    System.out.print("Enter a string: ");
    System.out.flush();
    input = getString();		//see method below
    if( input.equals("") )		//quit if [Enter]
      break;

    Reverser theReverser = new Reverser(input);
    output = theReverser.doRev();
    System.out.println("Reversed: " + output);
    }

  }

  public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }

  }
