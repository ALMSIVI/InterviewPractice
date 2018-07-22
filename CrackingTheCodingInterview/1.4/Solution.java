/**
  * Q1.4(Paidronme Permutation): Given a string, write a function to check if
  * it is a permutation of a palindrome. A palindrome is a word or phrase that
  * is the same forwards and backwards. A permutation is a rearrangement of
  * letters. The palindrome does not need to be limited to just dictionary
  * words.
  */
public class Solution {
  public static void main(String[] args) {
    test("Tact Coa");
    test("lazy");
    test("Belleb");
    test("#$%!abba");
    test("palindromemrolia");
  }
  
  /**
   * I assume that the word is ASCII-encoded. Based on example input I also
   * assume that the word only consists of English alphabet and space, and the
   * solution should be case insensitive.
   * Time complexity: O(n).
   */
  public static boolean isPermutation(String word) {
    char[] charArray = word.toLowerCase().toCharArray();
    int bitmask = 0;
    for (char character : charArray) {
      if (character != ' ') { // not including spaces
        int numBits = getCharCode(character);
        if (numBits != -1) {
          bitmask ^= (1 << numBits);
        }
      }
    }
    return bitmask == 0 || (bitmask & (bitmask - 1))== 0;
  }

  private static int getCharCode(char character) {
    int a = Character.getNumericValue('a');
    int z = Character.getNumericValue('z');
    int charVal = Character.getNumericValue(character);
    if (charVal >= a && charVal <= z) {
      return charVal - a;
    } else {
      return -1;
    }
  }

  /**
   * Used to test the solution.
   */
  public static void test(String word) {
    System.out.println("Word: " + word + " is palindrome permutation: " + isPermutation(word));
  }
}