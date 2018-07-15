/**
  * Q1.1(Is Unique): Implement an algorithm to determine if a string has all
  * unique characters. What if you cannot use aditional data structures?
  */
 
import java.util.HashSet;
import java.util.Arrays;
  
public class Solution {
  public static void main(String[] args) {
    test("abcde");
    test("aabbcc");
    test("");
    test("abcdea");
    test(null);
  }
  
  // I suppose the string might not be ASCII encoded. Therefore, solutions
  // given by the book would be inappropriate as there are far more characters
  // in Unicode.
  // Also, I suppose that if input is null the method would return false.
  
  /**
   * Time complexity: O(n) where n is the length of input.
   * In the worst case put() is called n times and each put() is O(1).
   */
  public static boolean isUniqueWithDataStructure(String input) {
    if (input == null) {
      return false;
    }
    HashSet<Character> resultSet = new HashSet<>();
    for (char c : input.toCharArray()) {
      boolean result = resultSet.add(c);
      if (result ==false) { // exists non unique char
        return false;
      }
    }
    return true;
  }
  
  /**
   * Time complexity: O(nlogn) where n is the length of input.
   * Sorting takes O(nlogn) and the comparison takes O(n).
   */
  public static boolean isUniqueWithoutDataStructure(String input) {
    if (input == null) {
      return false;
    }
    char[] charArray = input.toCharArray();
    Arrays.sort(charArray);
    for (int i = 0; i < charArray.length - 1; i++) {
      if (charArray[i] == charArray[i + 1]) { // exists non unique char
        return false;
      }
    }
    return true;
  }

  /**
   * Used to test the solution.
   */
  public static void test(String input) {
    System.out.println(input + " " + isUniqueWithDataStructure(input) + " " +
      isUniqueWithoutDataStructure(input));
  }
}