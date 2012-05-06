// stack.java

class StackX
  {
  private int maxSize;			//size of the stack array
  private long[] stackArray;
  private int top;			//top of the stack

  public StackX(int s)			//constructor
    {
    maxSize = s;
    stackArray = new long[maxSize];
    top = -1;				//stack is empty to begin
    }

  public void push(long j)		//put item on top of stack
    {
    stackArray[++top] = j;		//increment top, insert item
    }

  public long pop()			//take item from top of stack
    {
    return stackArray[top--];		//access item, decrement top
    }

  public long peek()			//peek at top of stack
    {
    return stackArray[top];
    }

  public boolean isEmpty()		//true if stack is empty
    {
    return (top == -1);
    }

  public boolean isFull()		//true if stack is full
    {
    return (top == maxSize-1);
    }

  }
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////

class StackApp
  {
  public static void main(String[] args)
    {
  
    StackX theStack = new StackX(10);	//create new stack
    theStack.push(20);
    theStack.push(40);
    theStack.push(60);
    theStack.push(80);

    while (!theStack.isEmpty() )		//until it's empty
      {
      long value = theStack.pop();
      System.out.print(value);
      System.out.print(" ");
      }
  
    System.out.println("");
  
    }

  }
