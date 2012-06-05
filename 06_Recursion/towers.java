// towers.java
// Solve the Towers of Hanoi puzzle

class TowersApp
  {
  static int nDisks = 3;

  public static void main(String[] args)
    {
    doTowers(nDisks, 'A', 'B', 'C');
    }

  /* if the subtree you're trying to move has an odd number of disks, move the topmost
      disk directly to the tower where you want the subtree to go. If you're trying to
      move a subtree with an even number of disks, start by moving the topmost disk to
      the intermediate tower.
  */
  public static void doTowers(int topN, char from, char inter, char to)
    {
    if(topN==1)
      System.out.println("Disk 1 from " + from + " to " + to);
    else
      {
      doTowers(topN-1, from, to, inter);
      System.out.println("Disk " + topN + " from " + from + " to " + to);
      doTowers(topN-1, inter, from, to);
      }
    }

  }