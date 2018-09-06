/**
 * Q2.8(Loop Detection): Given a circular linked list, implement an algorithm
 * that returns the node at the beginning of the loop.
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer
 * points to an earlier node, so as to make a loop in the linked list.
 * EXAMPLE
 * Input: A->B->C->D->E->C [the same C as earlier]
 * Output: C
 */
public class Solution {
  public static void main(String[] args) {
    int[] a = {1, 2, 3, 4, 5};
    LinkedListNode node = LinkedListUtil.toSinglyLinkedList(a);
    test(node); // no loop

    LinkedListNode last = LinkedListUtil.last(node);
    last.next = node.next.next; // 5->3
    test(node);

    last.next = node; // 5->1
    test(node);
  }
  
  /**
   * Time complexity: O(n) where n is the length of the list.
   */
  public static LinkedListNode detectLoop(LinkedListNode a) {
    LinkedListNode slow = a, fast = a;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) { // collision
        break;
      }
    }

    if (fast == null || fast.next == null) { // error, no loop
      return null;
    }

    slow = a;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }
    return slow; // slow and fast are at loop start.
  }

  /**
   * Used to test the solution.
   */
  public static void test(LinkedListNode a) {
    LinkedListNode result = detectLoop(a);
    System.out.println("Loop start is: " + (result == null ? "null" :
      result.data));
  }
}