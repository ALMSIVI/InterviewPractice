/**
  * Q2.2(Return Kth to Last): Implement an algorithm to find the kth to last of
  * a singly linked list.
  */
public class Solution {
  public static void main(String[] args) {
    LinkedListNode[] nodes = new LinkedListNode[10];
    nodes[9] = new LinkedListNode(9);
    for (int i = 8; i >= 0; i--) {
      nodes[i] = new LinkedListNode(i);
      // singly linked list so no setNext() which will set prev
      nodes[i].next = nodes[i + 1];
    }

    for (int i = 1; i <= 10; i++) {
      test(nodes[0], i);
    }
  }
  
  /**
   * This solution makes the same assumption as the book solution does: when
   * k = 1 the last elemnt is returned, and so on.
   * Time complexity: O(n) as the second pointer needs to traverse the entire
   * list.
   */
  public static LinkedListNode kthToLast(LinkedListNode node, int k) {
    LinkedListNode runner = node;
    for (int i = 0; i < k; i++) {
      if (runner == null) { // edge case: k is greater than the list size
        return null;
      }
      runner = runner.next;
    }

    while (runner != null) {
      node = node.next;
      runner = runner.next;
    }
    return node;
  }

  /**
   * Used to test the solution.
   */
  public static void test(LinkedListNode node, int k) {
    System.out.println("List is: " + node.printForward());
    System.out.println(k + "th to last element is: " + kthToLast(node, k).data);
    System.out.println("----------");
  }
}