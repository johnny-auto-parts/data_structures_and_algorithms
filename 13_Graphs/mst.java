// mst.java
// demonstrates minimum spanning tree
///////////////////////////////////////////////////////////////////
class StackX
  {
  private final int SIZE = 20;
  private int[] st;
  private int top;
// ---------------------------------------------------------------
  public StackX()
    {
    st = new int[SIZE];   // make array
    top = -1;             // set top to -1 while empty
    }
// ---------------------------------------------------------------
  public void push(int j)
    { st[++top] = j; }
// ---------------------------------------------------------------
  public int pop()
    { return st[top--]; }
// ---------------------------------------------------------------
  public int peek()
    { return st[top]; }
// ---------------------------------------------------------------
  public boolean isEmpty()
    { return (top == -1); }
// ---------------------------------------------------------------
  }
///////////////////////////////////////////////////////////////////
class Vertex
  {
  public char label;             // 'A','B','C', etc
  public boolean wasVisited;
// ---------------------------------------------------------------
  public Vertex(char lab)
    {
    label = lab;
    wasVisited = false;
    }
// ---------------------------------------------------------------
  }
///////////////////////////////////////////////////////////////////
class Graph
  {
  private final int MAX_VERTS = 20;    // vetex limit
  private Vertex vertexList[];         // list of vertices
  private int adjMat[][];              // adjacency matrix
  private int nVerts;                  // current # of verts
  private StackX theStack;             // stack
// ---------------------------------------------------------------
  public Graph()
    {
    vertexList = new Vertex[MAX_VERTS];
    adjMat = new int[MAX_VERTS][MAX_VERTS];
    nVerts = 0;
    for(int j=0; j<MAX_VERTS; j++)
      for(int k=0; k<MAX_VERTS; k++)
        adjMat[j][k] = 0;
    theStack = new StackX();
    }
// ---------------------------------------------------------------
  public void addVertex(char lab)
    {
    vertexList[nVerts++] = new Vertex(lab);
    }
// ---------------------------------------------------------------
  public void addEdge(int start, int end)
    {
    adjMat[start][end] = 1;
    adjMat[end][start] = 1;
    }
// ---------------------------------------------------------------
  public void displayVertex(int v)
    {
    System.out.print(vertexList[v].label);
    }
// ---------------------------------------------------------------
  public void mst() // minimum spanning tree using depth first search
    {
    // start at 0 --> mark and push
    vertexList[0].wasVisited = true;
    theStack.push(0);

    while( !theStack.isEmpty() )
      {
      int currentVertex = theStack.peek();
      // get next unvisited neighbor
      int v = getAdjUnvisitedVertex(currentVertex);
      if(v == -1)            //  no more neighbors
        theStack.pop();
      else
        {
        // mark and push the neighbor
        vertexList[v].wasVisited = true;
        theStack.push(v);
        // print out the path we just traveled
        displayVertex(currentVertex);
        displayVertex(v);
        System.out.print(" ");
        }
      }

      // Once stack is empty, we're done...reset flags
      for(int j=0; j<nVerts; j++)
        vertexList[j].wasVisited = false;
    }
// ---------------------------------------------------------------
  // return an unvisited vertex adj to v
  public int getAdjUnvisitedVertex(int v)
    {
    for(int j=0; j<nVerts; j++)
      if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
        return j;
    return -1;
    }
// ---------------------------------------------------------------
  }
///////////////////////////////////////////////////////////////////
class MSTApp
  {
  public static void main(String[] args)
    {
    Graph theGraph = new Graph();
    theGraph.addVertex('A');        // 0
    theGraph.addVertex('B');        // 1
    theGraph.addVertex('C');        // 2
    theGraph.addVertex('D');        // 3
    theGraph.addVertex('E');        // 4

    // create spiderweb of edges (i.e. more than the mst will need)
    theGraph.addEdge(0, 1);         // AB
    theGraph.addEdge(0, 2);         // AC
    theGraph.addEdge(0, 3);         // AD
    theGraph.addEdge(0, 4);         // AE
    theGraph.addEdge(1, 2);         // BC
    theGraph.addEdge(1, 3);         // BD
    theGraph.addEdge(1, 4);         // BE
    theGraph.addEdge(2, 3);         // CD
    theGraph.addEdge(2, 4);         // CE
    theGraph.addEdge(3, 4);         // DE

    System.out.print("Minimum spanning tree: ");
    theGraph.mst();
    System.out.println();
    }
  }
///////////////////////////////////////////////////////////////////
