/**
 * Q1.9(String rotation): Assume you have a method isSubstring which checks if
 * a word is a substring of another. Given two strings, s1 and s2, write code
 * to check if s2 is a rotation of s1 using only one call to isSubstring (e.g.,
 * "waterbottle" is a rotation of "erbottlewat").
 */
public class Solution {
  public static void main(String[] args) {
    test("waterbottle", "erbottlewat");
    test("cat", "cat");
    test("cat", "dog");
    test("", "");
    test("", "water");
  }
  
  /**
   * The book solution adds length check to s1. I didn't since I think the
   * empty string can be a rotation of itself.
   * Time complexity: O(n) where n is the length of the strings (in an average
   * case they should be the same)
   */
  public static boolean isRotation(String s1, String s2) {
    if (s1.length() != s2.length()) {
      return false;
    } else {
      String s1s1 = s1 + s1;
      return isSubstring(s1s1, s2);
    }
  }

  /**
   * Returns true if s2 is a substring of s1, false otherwise.
   */
  public static boolean isSubstring(String s1, String s2) {
    return s1.indexOf(s2) != -1;
  }
  
  /**
   * Used to test the solution.
   */
  public static void test(String s1, String s2) {
    String is = isRotation(s1, s2) ? " is " : " is not ";
    System.out.println(s2 + is + "a rotation of " + s1);
  }
}