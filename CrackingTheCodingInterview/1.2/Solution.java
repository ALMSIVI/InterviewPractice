/**
  * Q1.2(Check permutation): Give ntwo strings, write a method to decide if one
  * is a permutation of the other.
  */
import java.util.Arrays;

public class Solution {
  /**
   * To compile, use
   * javac -encoding UTF8 Solution.java
   * as the file contains non-ASCII chars.
   */
  public static void main(String[] args) {
    test("a", "a", true); // same string
    test("a", "a", false);
    test("", "b", true); // empty string
    test("", "b", false);
    test("abc", "cba", true); // permutation
    test("abc", "cba", false);
    test("abc", "dba", true); // non permutation
    test("abc", "dba", false);
    test("ジャバ", "ジャバ", false); // non-ascii
    test("ジャバ", "バジャ", false);
    test("ジャバ", "パイソン", false);
  }
  
  /**
   * If the strings contain non-ASCII characters, it would be best to sort the
   * string as using a count array is space expensive.
   */
  // 
  public static boolean checkPermutationUnicode(String a, String b) {
    if (a.length() != b.length() || a == null || b == null) {
      return false;
    }
    String newA = sortString(a);
    String newB = sortString(b);
    return newA.equals(newB);
  }

  private static String sortString(String a) {
    char[] array = a.toCharArray();
    Arrays.sort(array);
    return new String(array);
  }

  /**
   * ASCII characters are limited so it is possible to store them into
   * a count array.
   */
  public static boolean checkPermutationASCII(String a, String b) {
    if (a.length() != b.length() || a == null || b == null) {
      return false;
    }
    int[] charCount = new int[128]; // standard ASCII
    char[] aArray = a.toCharArray();
    for (char c : aArray) {
      charCount[c]++;
    }
    
    char[] bArray = b.toCharArray();
    for (char c : bArray) {
      charCount[c]--;
      if (charCount[c] < 0) {
        return false;
      }
    }

    return true;
  }

  /**
   * Used to test the solution.
   */
  public static void test(String a, String b, boolean isASCII) {
    boolean result = isASCII ? checkPermutationASCII(a, b) :
    checkPermutationUnicode(a, b);
    System.out.print(isASCII ? "ASCII - " : "Non-ASCII - ");
    System.out.println(a + " " + b + ": " + result);
  }
}