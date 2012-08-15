// mstw.java
// demonstrates minimum spanning tree with weighted graphs
///////////////////////////////////////////////////////////////////
class Edge
  {
  public int srcVert;     // index of a vertex starting edge
  public int destVert;    // index of a vertex ending edge
  public int distance;    // distance from src to dest
// ---------------------------------------------------------------
  public Edge(int sv, int dv, int d)
    {
    srcVert = sv;
    destVert = dv;
    distance = d;
    }
// ---------------------------------------------------------------
  }
///////////////////////////////////////////////////////////////////
class PriorityQ
  {
  private final int SIZE = 20;
  private Edge[] queArray;
  private int size;
// ---------------------------------------------------------------
  public PriorityQ()
    {
    queArray = new Edge[SIZE];
    size = 0;
    }
// ---------------------------------------------------------------
  public void insert(Edge item)   // insert item in sorted order
    {
    int j;

    // Determine Place to Insert
    for(j=0; j<size; j++)
      if( item.distance > queArray[j].distance)
        break;

    // Move Items Up
    for(int k=size-1; k>=j; k--)
      queArray[k+1] = queArray[k];

    // Insert item
    queArray[j] = item;
    size++;
    }
// ---------------------------------------------------------------
  public Edge removeMin()
    { return queArray[--size]; }
// ---------------------------------------------------------------
  public void removeN(int n)  // remove an item at specific index
    {
    for(int j=n; j<size-1; j++)
      queArray[j] = queArray[j+1];  // move items down
    size--;
    }
// ---------------------------------------------------------------
  public Edge peekMin()  // peek at min item in queue
    { return queArray[size-1]; }
// ---------------------------------------------------------------
  public int size()  // return number of items
    { return size; }
// ---------------------------------------------------------------
  public boolean isEmpty()  // returns true if queue is empty
    { return (size==0); }
// ---------------------------------------------------------------
  public Edge peekN(int n)  // peak at a given index
    { return queArray[n]; }
// ---------------------------------------------------------------
  public int find(int findDex)  // find item with specified
    {                           // destVert value
    for(int j=0; j<size; j++)
      if(queArray[j].destVert == findDex)
        return j;
    return -1;
    }
// ---------------------------------------------------------------
  }
///////////////////////////////////////////////////////////////////
class Vertex
  {
  public char label;            // e.g. 'A','B','C'
  public boolean isInTree;
// ---------------------------------------------------------------
  public Vertex(char lab)
    {
    label = lab;
    isInTree = false;
    }
// ---------------------------------------------------------------
  }
///////////////////////////////////////////////////////////////////
class Graph
  {
  private final int MAX_VERTS = 20;
  private final int INFINITY = 1000000;
  private Vertex vertexList[];              // list of vertices
  private int adjMat[][];                       // adjacency matrix
  private int nVerts;                       // current # of verts
  private int currentVert;
  private PriorityQ thePQ;
  private int nTree;                        // # of verts in tree
// ---------------------------------------------------------------
  public Graph()
    {
    // Initialize List of Vertices
    vertexList = new Vertex[MAX_VERTS];

    // Initialize Adjacency Matrix
    adjMat = new int[MAX_VERTS][MAX_VERTS];
 
    nVerts = 0;

    // Initialize Adjacency Matrix Values
    /* Unlike adjacency matrix for unweighted graphs, we can't use
       0s, because the value indicates weight, not a boolean */
    for(int j=0; j<MAX_VERTS; j++)
      for(int k=0; k<MAX_VERTS; k++)
        adjMat[j][k] = INFINITY;

    // Initialize Priority Queue
    thePQ = new PriorityQ();
  }
// ---------------------------------------------------------------
  public void addVertex(char lab)
    {
    vertexList[nVerts++] = new Vertex(lab);
    }
// ---------------------------------------------------------------
  public void addEdge(int start, int end, int weight)
    {
    adjMat[start][end] = weight;
    adjMat[end][start] = weight;
    }
// ---------------------------------------------------------------
  public void displayVertex(int v)
    {
    System.out.print(vertexList[v].label);
    }
// ---------------------------------------------------------------
  public void mstw()
    {
    // start at Vertex 0
    currentVert = 0;

    while(nTree < nVerts-1) // while not all vertices in tree
      {
      // put current vert in tree
      vertexList[currentVert].isInTree = true;
      nTree++;

      // insert edges adjacent to currentVert into priority queue
      for(int j=0; j<nVerts; j++)       // for each vertex
        {
        // There are self-to-self cells in the adajacency matrix
        // No need to evaluate these.
        if(j==currentVert)              
          continue;

        // If the vertex we're looking at is already in the tree
        // No need to consider connecting to it
        if(vertexList[j].isInTree)     
          continue;
        
        // Capture the distance from the current vert to the
        // vert the loop is looking at
        int distance = adjMat[currentVert][j];

        // If the vert referenced in the loop is reached
        // via infinity, there isn't an edge.  Go to next
        // loop iteration
        if( distance == INFINITY)       
          continue;

        // Otherwise, pass it to the putInPQ method to 
        // (possibly) insert it into the priority queue
        putInPQ(j, distance);           
        }

      // If the priority queue is still empty after the while loop
      // above executes, the graph is not connected
      if(thePQ.size() == 0)
        {
        System.out.println(" GRAPH NOT CONNECTED");
        return;
        }

      // Remove the edge with minimum distance from priority queue
      Edge theEdge = thePQ.removeMin();
      int sourceVert = theEdge.srcVert;

      // set currentVert to our chosen destination (for next iteration)
      currentVert = theEdge.destVert;       
      
      // Display the Edge from source to destination
      System.out.print( vertexList[sourceVert].label );  // source
      System.out.print( vertexList[currentVert].label ); // dest
      System.out.print(" ");
      }
      
    // mst is complete -- unmark vertices
    for(int j=0; j<nVerts; j++)
      vertexList[j].isInTree = false;
    }
// ---------------------------------------------------------------
  public void putInPQ(int newVert, int newDist)
    {
      // determine if there is already an edge with the same
      // destination vertex in the priority queue
      int queueIndex = thePQ.find(newVert);

      if(queueIndex != -1)
        {
        // Get the Edge that Exists
        Edge tempEdge = thePQ.peekN(queueIndex);
        int oldDist = tempEdge.distance;
        if(oldDist > newDist)
          {
          // Remove Old Edge
          thePQ.removeN(queueIndex);
          // Create New Edge
          Edge theEdge = new Edge(currentVert, newVert, newDist);
          // Add New Edge to PQ
          thePQ.insert(theEdge);
          }
        // else no action--> just leave old vertex there
        }
      else  // no edge with same destination vertex
        {
        // Insert into PQ
        Edge theEdge = new Edge(currentVert, newVert, newDist);
        thePQ.insert(theEdge);
        }
    }
// ---------------------------------------------------------------
  }
///////////////////////////////////////////////////////////////////
class MSTWApp
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

    theGraph.addEdge(0, 1, 6);      // AB 6
    theGraph.addEdge(0, 3, 4);      // AD 4
    theGraph.addEdge(1, 2, 10);     // BC 10
    theGraph.addEdge(1, 3, 7);      // BD 7
    theGraph.addEdge(1, 4, 7);      // BE 7
    theGraph.addEdge(2, 3, 8);      // CD 8
    theGraph.addEdge(2, 4, 5);      // CE 5
    theGraph.addEdge(2, 5, 6);      // CF 6
    theGraph.addEdge(3, 4, 12);     // DE 12
    theGraph.addEdge(4, 5, 7);      // EF 7

    System.out.print("Minimum spanning tree: ");
    theGraph.mstw();          // compute minimum spanning tree
    System.out.println();
    }
  }
///////////////////////////////////////////////////////////////////
