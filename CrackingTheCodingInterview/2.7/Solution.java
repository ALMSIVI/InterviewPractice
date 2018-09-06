/**
 * Q2.7(Intersection): Given two (singly) linked lists, determine if the two
 * lists intersect. Return the intersecting node. Note that the intersection is
 * defined based on reference, not value. That is, if the kth node of the first
 * linked list is the exact same node (by reference) as the jth node of the
 * second linked list, then they are intersecting.
 */
public class Solution {
  public static void main(String[] args) {
    int[] a = {1, 2, 3, 4, 5};
    LinkedListNode nodeA = LinkedListUtil.toSinglyLinkedList(a);
    int[] b = {6, 7};
    LinkedListNode nodeB = LinkedListUtil.toSinglyLinkedList(b);
    nodeB.next.next = nodeA.next.next; // so they intersect at node 3
    test(nodeA, nodeB);
    
    b = new int[] {1, 2, 3, 4, 5}; 
    nodeB = LinkedListUtil.toSinglyLinkedList(b); // no intersection
    test(nodeA, nodeB);

    nodeA = null; // edge case
    test(nodeA, nodeB);
  }
  
  /**
   * Time compexity: O(a + b) where a is the length of the first list and b is
   * the length of the second list.
   */
  public static LinkedListNode intersection(LinkedListNode a, LinkedListNode b)
  {
    if (a == null || b == null) {
      return null;
    }

    LinkedListNode runnerA = a, runnerB = b;
    int lengthA = 0, lengthB = 0;
    while (runnerA.next != null) {
      lengthA++;
      runnerA = runnerA.next;
    }
    while (runnerB.next != null) {
      lengthB++;
      runnerB = runnerB.next;
    }
    // Now runnerA and runnerB are the tail elements.
    if (runnerA != runnerB) {
      return null;
    }

    LinkedListNode shorter = lengthA > lengthB ? b : a;
    LinkedListNode longer = lengthA > lengthB ? a : b;
    int difference = Math.abs(lengthA - lengthB);
    longer = LinkedListUtil.getNth(longer, difference);
    while (shorter != longer) {
      shorter = shorter.next;
      longer = longer.next;
    }
    return shorter;
  }
  
  /**
   * Used to test the solution.
   */
  public static void test(LinkedListNode a, LinkedListNode b) {
    System.out.println(a == null ? "null" : a.printForward());
    System.out.println(b == null ? "null" : b.printForward());
    LinkedListNode result = intersection(a, b);
    System.out.println("Intersecting node is: " + (result == null ? "null" :
      result.data));
    System.out.println("----------");
  }
}