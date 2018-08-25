import java.util.Stack;

/**
 * Q2.6(Palindrome): Implement a function to check if a linked list is a
 * palindrome.
 */
public class Solution {
  public static void main(String[] args) {
    int[] test = {1, 2, 3, 3, 2, 1}; // even, is palindrome
    test(LinkedListUtil.toSinglyLinkedList(test));
    
    test = new int[] {1, 2, 3, 2, 1}; // odd, is palindrome
    test(LinkedListUtil.toSinglyLinkedList(test));

    test = new int[] {1, 2, 3, 1, 2, 1}; // even, not palindrome
    test(LinkedListUtil.toSinglyLinkedList(test));

    test = new int[] {1, 2, 3, 1, 2}; // odd, not palindrome
    test(LinkedListUtil.toSinglyLinkedList(test));

    test = new int[] {}; // even, empty
    test(LinkedListUtil.toSinglyLinkedList(test));

    test = new int[] {1}; // odd, single element
    test(LinkedListUtil.toSinglyLinkedList(test));
  }

  /**
   * This is the "iterative" solution in the book.
   * Time complexity: O(n) where n is the length of the linked list.
   */
  public static boolean isPalindrome(LinkedListNode node) {
    if (node == null) { // edge case
      return false;
    }

    Stack<LinkedListNode> stack = new Stack<>();
    LinkedListNode fast = node, slow = node;
    while (fast != null && fast.next != null) {
      stack.push(slow);
      slow = slow.next;
      fast = fast.next.next;
    }
    // now slow should hold the (n / 2)th element regardless of whether n is
    // odd or even.
    if (fast != null) { // n is odd, so slow is the "middle" one, skip it
      slow = slow.next;
    }

    while (slow != null) {
      if (slow.data != stack.pop().data) { // different!
        return false;
      }
      slow = slow.next;
    }
    return true;
  }

  /**
   * Used to test the solution.
   */
  public static void test(LinkedListNode node) {
    System.out.println((node == null ? "null" : node.printForward()) +
      (isPalindrome(node) ? " is" : " is not") + " a palindrome");
  }
}