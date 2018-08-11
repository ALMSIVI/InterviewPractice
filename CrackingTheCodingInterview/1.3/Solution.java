/**
 * Q1.3(URLify): Write a method to replace all spaces in a string with '%20'.
 * You may assume that the string has sufficient space at the end to hold the
 * aditional characters, and that you are given the "true" length of the
 * string. (Note: If implementing in Java, please use a character array so
 * that you can perform this operation in place.)
 */
public class Solution {
  public static void main(String[] args) {
    test("Mr John Smith    ", 13);
    test("   ", 1);
    test("i   ", 2);
    test("live      ", 6);
  }
  
  /**
   * Two scans: one to determine final length of string, one to actually URLify.
   * Time complexity: O(n) as scanning taks O(n).
   */
  public static void urlify(char[] string, int length) {
    int numSpaces = 0;
    for (int i = 0; i < length; i++) {
      if (string[i] == ' ') {
        numSpaces++;
      }
    }
    int realLength = length + 2 * numSpaces;
    for (int i = length - 1; i >= 0; i--) {
      if (string[i] == ' ') { // replace
        string[--realLength] = '0';
        string[--realLength] = '2';
        string[--realLength] = '%';
      } else {
        string[--realLength] = string[i];
      }
    }
  }


  /**
   * Used to test the solution.
   */
  public static void test(String string, int length) {
    System.out.println("Before: (length " + length + "):");
    System.out.println(string);
    System.out.println("After:");
    char[] result = string.toCharArray();
    urlify(result, length);
    System.out.println(new String(result));
    System.out.println("------");
  }
}