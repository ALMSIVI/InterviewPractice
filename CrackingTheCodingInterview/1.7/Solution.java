/**
  * Q1.7(Rotate matrix): Given an image represented by an NxN matrix, where
  * each pixel in the image is 4 bytes, write a method to rotate the image by
  * 90 degrees. Can you do this in place?
  */
public class Solution {
  public static void main(String[] args) {
    int[][] first = {{1}};
    test(first, true);
    int[][] second = {{1, 2}, {3, 4}};
    test(second, false);
    int[][] third = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
      {13, 14, 15, 16}};
    test(third, true);
    int[][] fourth = {{1, 2, 3, 4, 5}, {10, 9, 8, 7, 6}, {11, 12, 13, 14, 15},
      {20, 19, 18, 17, 16}, {21, 22, 23, 24, 25}};
    test(fourth, false);
  }
  
  /**
   * Time complexity: O(n^2) where n is the length of side of the matrix.
   */
  public static void rotate(int[][] matrix, boolean clockwise) {
    for (int i = 0; i < matrix.length / 2; i++) {
      for (int j = i; j < matrix.length - i - 1; j++) {
        /*
         * The four numbers to shift are:
         * (i, j),
         * (j, matrix.length - i - 1),
         * (matrix.length - i - 1, matrix.length - j - 1),
         * (matrix.length - j - 1, i)
         */
        int temp = matrix[i][j];
        if (clockwise) {
          matrix[i][j] = matrix[matrix.length - j - 1][i];
          matrix[matrix.length - j - 1][i] =
            matrix[matrix.length - i - 1][matrix.length - j - 1];
          matrix[matrix.length - i - 1][matrix.length - j - 1] =
            matrix[j][matrix.length - i - 1];
          matrix[j][matrix.length - i - 1] = temp;
        } else {
          matrix[i][j] = matrix[j][matrix.length - i - 1];
          matrix[j][matrix.length - i - 1] =
            matrix[matrix.length - i - 1][matrix.length - j - 1];
          matrix[matrix.length - i - 1][matrix.length - j - 1] = 
            matrix[matrix.length - j - 1][i];
          matrix[matrix.length - j - 1][i] = temp;
        }
      }
    }
  }

  /**
   * Used to test the solution.
   */
  public static void test(int[][] matrix, boolean clockwise) {
    System.out.println("Original:");
    printMatrix(matrix);
    String clockwiseStr = clockwise ? "Clockwise" : "Counterclockwise";
    System.out.println("Rotated " + clockwiseStr + ":");
    rotate(matrix, clockwise);
    printMatrix(matrix);
    System.out.println("----------");
  }

  /**
   * Assumes the matrix is SQUARE.
   */
  private static void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
}