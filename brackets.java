// brackets.java
// Use a Stack to Check Matching Brackets

import java.io.*;		//for I/O

class StackX
  {
  private int maxSize;
  private char[] stackArray;
  private int top;

  public StackX(int s)		//constructor
    {
    maxSize = s;
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
///////////////////////////////////////////////////////////////

class BracketChecker
  {
  private String input;

  public BracketChecker(String in)	//constructor
    { input = in; }

  public void check()
    {

    int stackSize = input.length();	//for setting max length
    StackX theStack = new StackX(stackSize);

    for(int j=0; j<input.length(); j++)	//get characters one by one
      {
      char ch = input.charAt(j);
      switch(ch)
        {
	case '{':
	case '[':
	case '(':
	  theStack.push(ch);		//push opening brackets to stack
	  break;

	case '}':
	case ']':
	case ')':
	  if( !theStack.isEmpty() )
	    {
	    char chx = theStack.pop();	//pop and check
	      if( (ch=='}' && chx!='{') ||
	          (ch==']' && chx!='[') ||
		  (ch==')' && chx!='(') )
	        System.out.println("Error: "+ch+" at "+j);
	    }
	  else			//prematurely empty
	    System.out.println("Error: "+ch+" at "+j);
	  break;
	default:		//no action on other characters
	  break;
	}
      }

      //After processing all characters...
      if ( !theStack.isEmpty() )
        System.out.println("Error: missing right delimiter");
    }

  }


//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

class BracketApp
  {
  public static void main(String[] args) throws IOException
    {
    String input;
    while(true)
      {
      System.out.println(
      		"Enter a string containing delimiters: ");
      System.out.flush();
      input = getString();		//see method below
      if( input.equals("") )		//quit if [Enter]
        break;

      BracketChecker theChecker = new BracketChecker(input);
      theChecker.check();
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
