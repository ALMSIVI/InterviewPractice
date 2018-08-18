/**
 * Q2.5(Sum Lists): You have two numbers represented by a linked list, where
 * each node contains a single digit. The digits are stored in reverse oreder,
 * such that the 1's digit is at the head of the list. Write a function that
 * adds the two numbetrs and returns the sum as a linked list.
 * EXAMPLE
 * Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295.
 * Output: 2 -> 1 -> 9. That is, 912.
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * EXAMPLE
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is , 617 + 295.
 * Output: 9 -> 1 -> 2. That is, 912.
 */
public class Solution {
  public static void main(String[] args) {
    /* Same length */
    int[] a = { 7, 1, 6 };
    int[] b = { 5, 9, 2 };
    LinkedListNode nodeA = LinkedListUtil.toSinglyLinkedList(a);
    LinkedListNode nodeB = LinkedListUtil.toSinglyLinkedList(b);
    test(nodeA, nodeB, 0);

    a = new int[] {6, 1, 7};
    b = new int[] {2, 9, 5};
    nodeA = LinkedListUtil.toDoublyLinkedList(a);
    nodeB = LinkedListUtil.toDoublyLinkedList(b);
    test(nodeA, nodeB, 1);
    test(nodeA, nodeB, 2);

    /* Different length */
    a = new int[] { 3, 1, 5 };
    b = new int[] { 7, 8 };
    nodeA = LinkedListUtil.toSinglyLinkedList(a);
     nodeB = LinkedListUtil.toSinglyLinkedList(b);
    test(nodeA, nodeB, 0);

    a = new int[] {5, 1, 3};
    b = new int[] {8, 7};
    nodeA = LinkedListUtil.toDoublyLinkedList(a);
    nodeB = LinkedListUtil.toDoublyLinkedList(b);
    test(nodeA, nodeB, 1);
    test(nodeA, nodeB, 2);

    /* One is null */
    a = new int[] { 1, 2, 3 };
    nodeA = LinkedListUtil.toSinglyLinkedList(a);
    nodeB = null;
    test(nodeA, nodeB, 0);
    test(nodeA, nodeB, 1);
    test(nodeA, nodeB, 2);

    /* Both are null */
    nodeA = null;
    test(nodeA, nodeB, 0);
    test(nodeA, nodeB, 1);
    test(nodeA, nodeB, 2);
  }

  /**
   * The book solution is recursive; mine here is iterative.
   * Time complexity: O(n) where n is the length of the longer list.
   */
  public static LinkedListNode sumReverse(LinkedListNode a, LinkedListNode b) {
    int carry = 0;
    LinkedListNode head = null;
    LinkedListNode runner = head;

    while (a != null || b != null) {
      LinkedListNode node = new LinkedListNode();

      int value = 0;

      if (a != null) {
        value += a.data;
        a = a.next;
      }
      if (b != null) {
        value += b.data;
        b = b.next;
      }

      node.data = (value + carry) % 10;
      carry = (value + carry) / 10;

      if (head == null) { // not initialized
        head = node;
        runner = head;
      } else {
        runner.next = node;
        runner = node;
      }
    }
    return head;
  }

  /**
   * We have to "defer" calculation until we have reached the tail. There is no
   * mention of a doubly linked list where we can easily access the previous
   * element. So iteration is not a good way for this follow up. Recursion is
   * applied instead.
   * Time complexity: O(n) where n is the length of the longer list.
   */
  public static LinkedListNode sumForward(LinkedListNode a, LinkedListNode b) {
    int lengthA = LinkedListUtil.length(a);
    int lengthB = LinkedListUtil.length(b);
    if (lengthA > lengthB) {
      b = fillZeros(b, lengthA - lengthB);
    } else if (lengthA < lengthB) {
      a = fillZeros(a, lengthB - lengthA);
    }

    PartialSum sum = sumForwardRecursive(a, b);
    if (sum.carry == 0) {
      return sum.sum;
    } else {
      LinkedListNode node = new LinkedListNode(sum.carry);
      node.next = sum.sum;
      return node;
    }
  }

  private static LinkedListNode fillZeros(LinkedListNode a, int length) {
    LinkedListNode head = a;
    for (int i = 0; i < length; i++) {
      LinkedListNode node = new LinkedListNode();
      node.setNext(head);
      head = node;
    }
    return head;
  }

  private static PartialSum sumForwardRecursive(LinkedListNode a,
  LinkedListNode b) {
    if (a == null && b == null) {
      return new PartialSum();
    }

    PartialSum sum = sumForwardRecursive(a.next, b.next);

    int value = sum.carry + a.data + b.data;
    
    LinkedListNode result = new LinkedListNode(value % 10);
    result.next = sum.sum;
    sum.sum = result;
    sum.carry = value / 10;
    return sum;
  }

  private static class PartialSum {
    public LinkedListNode sum;
    public int carry;
  }

  /**
   * Now suppose the lists are doubly linked lists. This is an iterative
   * solution.
   * Time complexity: O(n) where n is the length of the longer list.
   */
  public static LinkedListNode sumForwardDoubly(LinkedListNode a,
  LinkedListNode b) {
    LinkedListNode tailA = LinkedListUtil.last(a);
    LinkedListNode tailB = LinkedListUtil.last(b);

    int carry = 0;
    LinkedListNode head = null;

    while (tailA != null || tailB != null) {
      LinkedListNode node = new LinkedListNode();

      int value = 0;

      if (tailA != null) {
        value += tailA.data;
        tailA = tailA.prev;
      }
      if (tailB != null) {
        value += tailB.data;
        tailB = tailB.prev;
      }

      node.data = (value + carry) % 10;
      carry = (value + carry) / 10;

      if (head == null) { // not initialized
        head = node;
      } else {
        head.setPrevious(node);
        head = node;
      }
    }
    return head;
  }

  /**
   * Used to test the solution.
   * @param param represents the method to be tested.
   * 0: sumReverse, 1: sumForward, 2: sumForwardDoubly
   */
  public static void test(LinkedListNode a, LinkedListNode b, int param) {
    String aWord = a == null ? "null" : a.printForward();
    String bWord = b == null ? "null" : b.printForward();
    LinkedListNode result;
    String methodName;
    if (param == 0) {
      result = sumReverse(a, b);
      methodName = " with sumReverse";
    } else if (param == 1) {
      result = sumForward(a, b);
      methodName = " with sumForward";
    } else {
      result = sumForwardDoubly(a, b);
      methodName = " with sumForwardDoubly";
    }

    String resultWord = result == null ? "null" : result.printForward();
    System.out.println(aWord + " + " + bWord + " = " + resultWord + methodName);
  }
}