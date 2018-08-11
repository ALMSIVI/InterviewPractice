/**
 * Q1.5(One Away): There are three types of edits that can be performed on
 * strings: insert a character, remove a character, or replace a character.
 * Given two strings, write a function to check if they are one edit (or zero
 * edits) away.
 */
public class Solution {
  public static void main(String[] args) {
    test("pale", "ple");
    test("pales", "pale");
    test("pale", "bale");
    test("pale", "bake");
    test("main", "mian");
  }
  
  /**
   * Time complexity: O(n) where n is the length of the longer string. 
   * In fact, it doesn't really matter if we use the shorter string, as if they
   * do not fail the length check, their length difference is at most 1, which
   * could be negligible compared with the strings' lengths.
   */
  public static boolean oneAway(String a, String b) {
    int lengthDiff = Math.abs(a.length() - b.length());
    if (lengthDiff > 1) {
      return false;
    }
    String shorter = a.length() < b.length() ? a : b;
    String longer = shorter.equals(a) ? b : a;
    int sIndex = 0;
    int lIndex = 0;
    boolean edited = false; // if the one edit has been used
    while (sIndex < shorter.length() && lIndex < longer.length()) {
      if (shorter.charAt(sIndex) != longer.charAt(lIndex)) {
        if (edited) {
          return false;
        } else {
          edited = true;
          if (lengthDiff == 0) {
            sIndex++;
          }
        }
      } else {
        sIndex++;
      }
      lIndex++; // it needs to be increased no matter what
    }
    return true;
  }

  /**
   * Used to test the solution.
   */
  public static void test(String a, String b) {
    System.out.println(a);
    System.out.println(b);
    System.out.println("-> " + oneAway(a, b));
    System.out.println("----------");
  }
}