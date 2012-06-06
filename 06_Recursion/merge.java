// merge.java
// merge two arrays into a third

class MergeApp
  {
  public static void main(String[] args)
    {
    int[] arrayA = {23, 47, 81, 95};
    int[] arrayB = {7, 14, 39, 55, 62, 74};
    int[] arrayC = new int[10];
    
    merge(arrayA, 4, arrayB, 6, arrayC);
    display(arrayC, 10);
    }

  public static void merge( int[] arrayA, int sizeA,
                            int[] arrayB, int sizeB,
                            int[] arrayC )
    {
    int aDex=0, bDex=0, cDex=0; // to keep track of where we are in each array

    while(aDex < sizeA && bDex < sizeB) // while neither array is empty
      if( arrayA[aDex] < arrayB[bDex] ) // if the smallest element in A is smaller than that of B
        arrayC[cDex++] = arrayA[aDex++];
      else
        arrayC[cDex++] = arrayB[bDex++]; // b must be smaller

    // When A or B becomes empty...
    while(aDex < sizeA) // 'A' still has elements
      arrayC[cDex++] = arrayA[aDex++];
  
    while(bDex < sizeB) // 'B' still has elements
      arrayC[cDex++] = arrayB[bDex++];
    }
////////////////////////////////////////////////////////////////////////////////////
  public static void display(int[] theArray, int size)
    {
    for(int j=0; j<size; j++)
      System.out.print(theArray[j] + " ");
    System.out.println("");
    }
  }