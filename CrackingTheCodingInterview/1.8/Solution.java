/**
  * Q1.8(Zero Matrix): Write an algorithm such that if an element in an MxN
  * matrix is 0, its entire row and column are set to 0.
  */
public class Solution {
  public static void main(String[] args) {
    int[][] zero = {{0}};
    test(zero);
    int[][] one = {{1, 2}, {0, 3}};
    test(one);
    int[][] two = {{1, 0, 2}, {0, 3, 5}, {4, 9, 0}};
    test(two);
    int[][] three = {{0, 0, 1, 2}, {0, 0, 3, 4}, {5, 6, 0, 7}, {8, 9, 10, 11}};
    test(three);
  }
  
  /**
   * Time complecity: O(MN), as each cell is traversed only once.
   */
  public static void zero(int[][] matrix) {
    boolean rowZero = false;
    boolean colZero = false;

    /*
     * The 0th row and column are used to mark the position of zeros.
     */
    for (int row = 0; row < matrix.length; row++) {
      if (matrix[row][0] == 0) {
        colZero = true;
        break;
      }
    }

    for (int col = 0; col < matrix[0].length; col++) {
      if (matrix[0][col] == 0) {
        rowZero = true;
        break;
      }
    }

    for (int row = 1; row < matrix.length; row++) {
      for (int col = 1; col < matrix[0].length; col++) {
        if (matrix[row][col] == 0) {
          matrix[row][0] = 0;
          matrix[0][col] = 0;
        }
      }
    }

    /*
     * Starting from 1. The 0th (marking) row and column cannot be zerofied yet.
     */
    for (int row = 1; row < matrix.length; row++) {
      if (matrix[row][0] == 0) {
        zerofyRow(matrix, row);
      }
    }

    for (int col = 1; col < matrix[0].length; col++) {
      if (matrix[0][col] == 0) {
        zerofyCol(matrix, col);
      }
    }

    if (rowZero) {
      zerofyRow(matrix, 0);
    }
    
    if (colZero) {
      zerofyCol(matrix, 0);
    }
  }

  private static void zerofyRow(int[][] matrix, int row) {
    for (int col = 0; col < matrix[0].length; col++) {
      matrix[row][col] = 0;
    }
  }

  private static void zerofyCol(int[][] matrix, int col) {
    for (int row = 0; row < matrix.length; row++) {
      matrix[row][col] = 0;
    }
  }
  
  /**
   * Used to test the solution.
   */
  public static void test(int[][] matrix) {
    System.out.println("Before setZeros:");
    printMatrix(matrix);
    System.out.println("After setZeros:");
    zero(matrix);
    printMatrix(matrix);
    System.out.println("----------");
  }


  private static void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
}