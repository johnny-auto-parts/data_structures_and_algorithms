// anagram.java
// takes a word and creates anagrams
import java.io.*;

class AnagramApp
  {
  static int size; // the length of the input string
  static int count;
  static char[] arrChar = new char[100]; // We will convert our string into an array

  public static void main(String[] args) throws IOException
    {
    System.out.print("Enter a word: ");
    String input = getString();
    size = input.length();
    
    // When printed, our anagrams will be numbered
    count = 0;
    
    // Put our string in an array
    for(int j=0; j<size; j++)
      arrChar[j] = input.charAt(j);

    doAnagram(size);
    }

  public static void doAnagram(int newSize) // we only need to know the length of the string
    {
    if(newSize == 1)
      return;
    for(int j=0; j<newSize; j++) // for every character in the string...
      {
      doAnagram(newSize-1); // anagram n-1 letters: calls itself with a word one letter smaller than before
      if(newSize==2)
        displayWord();
      rotate(newSize); // rotate the letters
      }
    }

  public static void rotate(int newSize)
    {
    int j;
    int position = size - newSize;
    char temp = arrChar[position];
    for(j=position+1; j<size; j++)
      arrChar[j-1] = arrChar[j];
    arrChar[j-1] = temp;
    }

  public static void displayWord()
    {
    // Once the count goes to double and triple digits,
    //  add spaces in front of the count to keep alignment
    if(count < 99)
      System.out.print(" ");
    if(count < 9)
      System.out.print(" ");
    
    // Increment the count and print a space
    System.out.print(++count + " ");

    // Print the word
    for(int j=0; j<size; j++)
      System.out.print( arrChar[j] );
    
    // Print a bunch of spaces in between words
    System.out.print("   ");
    System.out.flush();

    // Every 6 words, put a carriage return
    if(count%6 == 0)
      System.out.println("");
    }

  public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }

  }

