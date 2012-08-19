// path.java
// demonstrates shortest path with weighted, directed graphs
// **Dijkstra's Algorithm**
///////////////////////////////////////////////////////////////////////
class DistPar               // distance and parent
  {                         // objects stored in sPath array
  public int distance;      // distance from start to this vertex
  public int parentVert;    // current parent of this vertex
// -------------------------------------------------------------------
  public DistPar(int pv, int d)
    {
    distance = d;
    parentVert = pv;
    }
  }
///////////////////////////////////////////////////////////////////////
class Vertex
  {
  public char label;        // e.g. 'A','B','C'
  public boolean isInTree;
// -------------------------------------------------------------------
  public Vertex(char lab)
    {
    label = lab;
    isInTree = false;
    }
  }
///////////////////////////////////////////////////////////////////////
class Graph
  {
  private final int MAX_VERTS = 20;
  private final int INFINITY = 1000000;
  private Vertex vertexList[];               // list of vertices
  private int adjMat[][];                    // adjacency matrix
  private int nVerts;                        // current # of verts
  private int nTree;                         // # of verts in tree
  private DistPar sPath[];                   // array for shortest-path data
  private int currentVert;                   // current vertex
  private int startToCurrent;                // distance to currentVert
// -------------------------------------------------------------------
  public Graph()
    {
    vertexList = new Vertex[MAX_VERTS];      // initialize vertex list
    adjMat = new int[MAX_VERTS][MAX_VERTS];  // initialize adjacency matrix
    nVerts = 0;
    nTree = 0;

    // Set adjacency matrix to infinity
    for(int j=0; j<MAX_VERTS; j++)
      for(int k=0; k<MAX_VERTS; k++)
        adjMat[j][k] = INFINITY;

    // Initialize Shortest Path Array
    sPath = new DistPar[MAX_VERTS];
    }
// -------------------------------------------------------------------
  public void addVertex(char lab)
    {
    vertexList[nVerts++] = new Vertex(lab);
    }
// -------------------------------------------------------------------
  public void addEdge(int start, int end, int weight)
    {
    adjMat[start][end] = weight;
    }
// -------------------------------------------------------------------
  public void path()
    {
    // start at first vertex (0)
    int startTree = 0;

    // Put first vertex in the tree
    vertexList[startTree].isInTree = true;
    nTree = 1;

    // Transfer initial row of distances from adj matrix to sPath
    // We'll be updating sPath as we build out our shortest path list    
    for(int j=0; j<nVerts; j++)
      {
      int tempDist = adjMat[startTree][j];
      sPath[j] = new DistPar(startTree, tempDist);
      }

    // Until all vertices are in the tree...
    while(nTree < nVerts)
      {
      // find shortest distance to unvisited vertex
      int indexMin = getMin();                  // find min index
      int minDist = sPath[indexMin].distance;   // store min value

      if(minDist == INFINITY)  // all infinite distances OR
        {                      // all vertexes are in tree
        System.out.println("There are unreachable vertices");
        break;
        }
      else
        {
        currentVert = indexMin;   // set to closest vert
        startToCurrent = sPath[indexMin].distance;
        }

      // Put current vertex in tree
      vertexList[currentVert].isInTree = true;
      nTree++;
      // Update sPath array
      adjust_sPath();           
      }

    // Print output to screen
    displayPaths();

    // Clear Tree
    nTree = 0;
    for(int j=0; j<nVerts; j++)
      vertexList[j].isInTree = false;
    }
// -------------------------------------------------------------------
  public int getMin()
    {
    int minDist = INFINITY;          // assume minimum for now
    int indexMin = 0;                // we'll eventually return this var
    for(int j=1; j<nVerts; j++)      // for each vert
      {  
      // if its not in the tree & smaller than stored min...
      if( !vertexList[j].isInTree && sPath[j].distance < minDist)
        {
        minDist = sPath[j].distance;     // update min distance
        indexMin = j;                    // update index of min
        }
      }
    return indexMin;
    }
// -------------------------------------------------------------------
  public void adjust_sPath()
    {
    // adjust values in shortest-path array sPath
    int column = 1;                 // skip starting vertex
    while(column < nVerts)          // go across columns
      {
      // Skip if column's vertex is already in the tree
      if( vertexList[column].isInTree )
        {
        column++;
        continue;
        }
      
      // The length of current leg of the path
      int currentToFringe = adjMat[currentVert][column];
      // The length of the full path
      int startToFringe = startToCurrent + currentToFringe;

      // Update sPath with smaller values if available
      int sPathDist = sPath[column].distance;
      if(startToFringe < sPathDist)
        {
        sPath[column].parentVert = currentVert;
        sPath[column].distance = startToFringe;
        }
      column++;
      }
    }
// -------------------------------------------------------------------
  public void displayPaths()       // display contents of sPath
    {
    for(int j=0; j<nVerts; j++)
      {
      // Print label + distance
      System.out.print(vertexList[j].label + "=");
      if(sPath[j].distance == INFINITY)
        System.out.print("inf");
      else
        System.out.print(sPath[j].distance);
        
      // Print last vertex touched before destination is reached
      char parent = vertexList[ sPath[j].parentVert ].label;
      System.out.print("(" + parent + ") ");
      }
    System.out.println("");
    }
// -------------------------------------------------------------------
  }
///////////////////////////////////////////////////////////////////////
class PathApp
  {
  public static void main(String[] args)
    {
    Graph theGraph = new Graph();
    theGraph.addVertex('A');           // 0
    theGraph.addVertex('B');           // 1
    theGraph.addVertex('C');           // 2
    theGraph.addVertex('D');           // 3
    theGraph.addVertex('E');           // 4

    theGraph.addEdge(0, 1, 50);        // AB 50
    theGraph.addEdge(0, 3, 80);        // AD 80
    theGraph.addEdge(1, 2, 60);        // BC 60
    theGraph.addEdge(1, 3, 90);        // BD 90
    theGraph.addEdge(2, 4, 40);        // CE 40
    theGraph.addEdge(3, 2, 20);        // DC 20
    theGraph.addEdge(3, 4, 70);        // DE 70
    theGraph.addEdge(4, 1, 50);        // EB 50

    System.out.println("Shortest path");
    theGraph.path();
    System.out.println();
    }
  }
///////////////////////////////////////////////////////////////////////
