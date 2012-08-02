// dfs.java
// demonstrates depth-first search
////////////////////////////////////////////////////////////////////
class StackX
  {
  private final int SIZE = 20; // stack is limited to 20 elements
  private int[] st;  // the stack array
  private int top;  // index of element on top of stack
// -----------------------------------------------------------------
  public StackX()
    {
    st = new int[SIZE];
    top = -1;
    }
// -----------------------------------------------------------------
  public void push(int j)
    { st[++top] = j; }
// -----------------------------------------------------------------
  public int pop()
    { return st[top--]; }
// -----------------------------------------------------------------
  public int peek()
    { return st[top]; }
// -----------------------------------------------------------------
  public boolean isEmpty()
    { return (top == -1); }
// -----------------------------------------------------------------
  }
////////////////////////////////////////////////////////////////////
class Vertex
  {
  public char label;          // e.g. 'A', 'B', etc
  public boolean wasVisited;
// -----------------------------------------------------------------
  public Vertex(char lab)
    {
    label = lab;
    wasVisited = false;
    }
// -----------------------------------------------------------------
  }
////////////////////////////////////////////////////////////////////
class Graph
  {
  private final int MAX_VERTS = 20;
  private Vertex vertexList[];       // list of vertices
  private int adjMat[][];            // adjacency matrix
  private int nVerts;                // current number of vertices
  private StackX theStack;
// -----------------------------------------------------------------
  public Graph()             // constructor
    {
    // initialize list of vertexes
    vertexList = new Vertex[MAX_VERTS];
    // initialize adjacency matrix
    adjMat = new int[MAX_VERTS][MAX_VERTS];
    
    nVerts = 0;

    // populate adjacency matrix with zeros for max vertex count
    for(int j=0; j<MAX_VERTS; j++)
      for(int k=0; k<MAX_VERTS; k++)
        adjMat[j][k] = 0;

    theStack = new StackX();
    }
// -----------------------------------------------------------------
  public void addVertex(char lab)
    {
    vertexList[nVerts++] = new Vertex(lab);
    }
// -----------------------------------------------------------------
  public void addEdge(int start, int end)
    {
    adjMat[start][end] = 1;
    adjMat[end][start] = 1;
    }
// -----------------------------------------------------------------
  public void displayVertex(int v)
    {
    System.out.print(vertexList[v].label);
    }
// -----------------------------------------------------------------
  public void dfs()
    {
    // Visit the intial vertex
    vertexList[0].wasVisited = true;
    displayVertex(0);
    theStack.push(0);

    while( !theStack.isEmpty() )
      {
      // get an unvisited vertex adjacent to stack top
      int v = getAdjUnvisitedVertex( theStack.peek() );
      if(v == -1)                        // if none exists, pop it
        theStack.pop();
      else                               // if it exists ...
        {
        vertexList[v].wasVisited = true; // mark it
        displayVertex(v);                // display it
        theStack.push(v);                // push it
        }
      }
      
    // stack is empty, so we're done
    // reset flags
    for(int j=0; j<nVerts; j++)
      vertexList[j].wasVisited = false;
    }
// -----------------------------------------------------------------
  // Find an unvisited vertex adjacent to v
  public int getAdjUnvisitedVertex(int v)
    {
    for(int j=0; j<nVerts; j++)
      if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
        return j;
    return -1;
    }
// -----------------------------------------------------------------
  }    
////////////////////////////////////////////////////////////////////
class DFSApp
  {
  public static void main(String[] args)
    {
    Graph theGraph = new Graph();
    theGraph.addVertex('A');   // 0
    theGraph.addVertex('B');   // 1
    theGraph.addVertex('C');   // 2
    theGraph.addVertex('D');   // 3
    theGraph.addVertex('E');   // 4
    theGraph.addVertex('F');   // 5

    theGraph.addEdge(0, 1);    // AB
    theGraph.addEdge(1, 2);    // BC
    theGraph.addEdge(0, 3);    // AD
    theGraph.addEdge(3, 4);    // DE

    System.out.print("Visits: ");
    theGraph.dfs();           // depth-first search
    System.out.println();
    }
  }
////////////////////////////////////////////////////////////////////
