/**
 * Q2.3(Delete Middle Node): Implement an algorithm to delete a node in the
 * middle (i.e., any node but the first and last node, not necessarily the
 * exact middle) of a singly linked list, given only access to that node.
 * EXAMPLE
 * Input: the node c from the linked list a->b->c->d->e->f
 * Result: nothing is returned, but the new linked list looks like
 * a->b->d->e->f
 */
public class Solution {
  public static void main(String[] args) {
    LinkedListNode[] nodes = new LinkedListNode[10];
    for (int i = 1; i <= 8; i++) {
      resetNodes(nodes);
      test(nodes[0], nodes[i]);
    }
  }
  
  /**
   * The book solution returns a boolean, since it checks if the input is the
   * last node. I here assume (as the problem suggests) that the input is never
   * the last node, and hence return nothing.
   * Time complexity: O(1) as we only deals with three nodes.
   * (node, node.next, node.next.next)
   */
  public static void deleteMiddle(LinkedListNode node) {
    node.data = node.next.data;
    node.next = node.next.next;
  }

  /**
   * Used to test the solution.
   */
  public static void test(LinkedListNode head, LinkedListNode node) {
    System.out.println("Before: " + head.printForward());
    System.out.print("After deleting " + node.data + ": ");
    deleteMiddle(node);
    System.out.println(head.printForward());
    System.out.println("----------");
  }

  private static void resetNodes(LinkedListNode[] nodes) {
    nodes[nodes.length - 1] = new LinkedListNode(9);
    for (int i = nodes.length - 2; i >= 0; i--) {
      nodes[i] = new LinkedListNode(i);
      // singly linked list so no setNext() which will set prev
      nodes[i].next = nodes[i + 1];
    }
  }
}