// topo.java
// demonstrates topological sorting
///////////////////////////////////////////////////////////////
class Vertex
  {
  public char label;         // e.g. 'A','B','C', etc
// -----------------------------------------------------------
  public Vertex(char lab)
    { label = lab; }
  }
///////////////////////////////////////////////////////////////
class Graph
  {
  private final int MAX_VERTS = 20;        // max verts allowed
  private Vertex vertexList[];             // list of verts
  private int adjMat[][];                  // adjacency matrix
  private int nVerts;                      // current # of verts
  private char sortedArray[];
// -----------------------------------------------------------
  public Graph()
    {
    vertexList = new Vertex[MAX_VERTS];
    adjMat = new int[MAX_VERTS][MAX_VERTS];
    nVerts = 0;
    
    // set adjacency matrix to 0
    for(int j=0; j<MAX_VERTS; j++)
      for(int k=0; k<MAX_VERTS; k++)
        adjMat[j][k] = 0;
    
    // For sorted vert labes
    sortedArray = new char[MAX_VERTS];
    }
// -----------------------------------------------------------
  public void addVertex(char lab)
    {
    vertexList[nVerts++] = new Vertex(lab);
    }
// -----------------------------------------------------------
  public void addEdge(int start, int end)
    {
    // Since this is directed graph, we only mark row & col
    //  NOT column & row
    adjMat[start][end] = 1;
    }
// -----------------------------------------------------------
  public void displayVertex(int v)
    {
    System.out.print(vertexList[v].label);
    }
// -----------------------------------------------------------
  public void topo()         // topological sort
    {
    int orig_nVerts = nVerts;     // remember how many verts

    while(nVerts > 0)
      {
      // get a vertex with no successors or -1
      int currentVertex = noSuccessors();
      if(currentVertex == -1)  // must be a cycle
        {
        System.out.println("ERROR: Graph has cycles");
        return;
        }
      
      // insert vertex label in sorted array (we begin at end)
      sortedArray[nVerts-1] = vertexList[currentVertex].label;

      // delete vertex
      deleteVertex(currentVertex);
      }

    // Vertices are all gone--> display sorted array
    System.out.print("Topologically sorted order: ");
    for(int j=0; j<orig_nVerts; j++)
      System.out.print( sortedArray[j] );
    System.out.println("");
    }
// -----------------------------------------------------------
  public int noSuccessors()      // returns vert with no successors
    {
    boolean isEdge;           // edge from row to column in adjMat

    /* For each row, go through each column
       If you find a 1, the row is an edge (has successors)
       If you don't find any 1s in a row, is not an edge--return it
       If you don't find any non-edges, there must be a cycle (return -1) */

    for(int row=0; row<nVerts; row++)
      {
      isEdge = false;
      for(int col=0; col<nVerts; col++)
        {
        if( adjMat[row][col] > 0 )
          {
          isEdge = true;
          break;
          }
        }
      if( !isEdge )
        return row;
      }
    return -1;
    }
// -----------------------------------------------------------
  public void deleteVertex(int delVert)
    {
    if(delVert != nVerts-1)
    /* we only need to perform the enclosed operations if
       we're not deleting the last element */
      {
      // shift elements forward that are beyond deleted element
      for(int j=delVert; j<nVerts-1; j++)
        vertexList[j] = vertexList[j+1];
      // Handle Adjacency Matrix
      
      //  Delete Row from Adjacency Matrix         
      for(int row=delVert; row<nVerts-1; row++)
        moveRowUp(row, nVerts);

      // Delete Column from Adjacency Matrix
      for(int col=delVert; col<nVerts-1; col++)
        moveColLeft(col, nVerts-1);
      }
    nVerts--;
    }
// -----------------------------------------------------------
  private void moveRowUp(int row, int length)
    {
    for(int col=0; col<length; col++) // we need all the columns
      adjMat[row][col] = adjMat[row+1][col]; // but not all rows
    }
// -----------------------------------------------------------
  private void moveColLeft(int col, int length)
    {
    for(int row=0; row<length; row++) // we need all the rows
      adjMat[row][col] = adjMat[row][col+1]; // but not all cols
    }
// -----------------------------------------------------------
  }
class TopoApp
  {
  public static void main(String[] args)
    {
    Graph theGraph = new Graph();
    theGraph.addVertex('A');        // 0
    theGraph.addVertex('B');        // 1
    theGraph.addVertex('C');        // 2
    theGraph.addVertex('D');        // 3
    theGraph.addVertex('E');        // 4
    theGraph.addVertex('F');        // 5
    theGraph.addVertex('G');        // 6
    theGraph.addVertex('H');        // 7
    
    theGraph.addEdge(0, 3);         // AD
    theGraph.addEdge(0, 4);         // AE
    theGraph.addEdge(1, 4);         // BE
    theGraph.addEdge(2, 5);         // CF
    theGraph.addEdge(3, 6);         // DG
    theGraph.addEdge(4, 6);         // EG
    theGraph.addEdge(5, 7);         // FH
    theGraph.addEdge(6, 7);         // GH

    theGraph.topo();
    }
  }
///////////////////////////////////////////////////////////////
