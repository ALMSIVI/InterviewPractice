/**
 * Q1.6(String compression): Implement a method to perform basic string
 * compression using the counts of repeated characters. For example, the
 * string aabcccccaaa would become a2b1c5a3. If the "compressed" string would
 * not become smaller than the original string, your method should return the
 * original string. You can assume the string has only uppercase and lowercase
 * letters (a-z).
 */
public class Solution {
  public static void main(String[] args) {
    test("aabcccccaaa");
    test("abcd");
    test("aa");
    test("a");
    test("aaAAAbBBBBBCCCCc");
  }
  
  /**
   * Time complexity: O(n) where n is the length of string.
   * This method uses two passes; it is possible to discard countLength() and
   * do it in one pass. This will NOT save time if the compressed string is not
   * shorter than the original one since if it is it would still need one pass
   * to find out. However, this will (possibly) save the time of StringBuilder 
   * reallocation of its inner char array. So, if the string is short, it might
   * be advisable to discard countLength().
   */
  public static String compress(String uncompressed) {
    int length = countLength(uncompressed);
    if (length >= uncompressed.length()) {
      return uncompressed;
    } else {
      StringBuilder builder = new StringBuilder(length);
      int charLength = 0;
      for (int i = 0; i < uncompressed.length(); i++) {
        charLength++;
        if (i == uncompressed.length() - 1 || uncompressed.charAt(i) !=
          uncompressed.charAt(i + 1)) {
          builder.append(uncompressed.charAt(i));
          builder.append(charLength);
          charLength = 0;
        }
      }
      return builder.toString();
    }
  }

  public static int countLength(String uncompressed) {
    int length = 0;

    int charLength = 0;
    for (int i = 0; i < uncompressed.length(); i++) {
      charLength++;
      if (i == uncompressed.length() - 1 || uncompressed.charAt(i) !=
        uncompressed.charAt(i + 1)) {
        length += 1 + Integer.toString(charLength).length();
        charLength = 0;
      }
    }
    return length;
  }

  /**
   * Used to test the solution.
   */
  public static void test(String uncompressed) {
    System.out.println(uncompressed + " -> " + compress(uncompressed));
  }
}