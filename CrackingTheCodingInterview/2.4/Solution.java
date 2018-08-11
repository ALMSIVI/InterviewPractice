/**
 * Q2.4(Partition): Write code to partition a linked list around a value x,
 * such that all nodes less than x come before all nodes greater than or equal
 * to x. If x is contained within the list, the values of x only need to be
 *the after the elements less than x (see below). The partition element x can
 * appear anywhere in the "right partition", it does not need to appear
 * between the left and right partitions.
 * EXAMPLE
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5]
 * Output: 3 -> 1 -> 2- > 10 -> 5 -> 5 -> 8
 */
public class Solution {
  public static void main(String[] args) {
    int[] arr = {3, 5, 8, 5, 10, 2, 1};
    LinkedListNode node = LinkedListUtil.toSinglyLinkedList(arr);
    test(node, 5);
    arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    node = LinkedListUtil.toSinglyLinkedList(arr);
    test(node, 10);
    arr = new int[] {1, 10, 1, 10, 1, 10, 1, 10, 1, 10};
    node = LinkedListUtil.toSinglyLinkedList(arr);
    test(node, 5);
  }
  
  /**
   * The book solutions all assume this is a singly linked list, so I would
   * stick to this assumption.
   * Time complexity: O(n) where n is the length of the list, since it has to
   * traverse every node.
   */
  public static LinkedListNode partition(LinkedListNode node, int partition) {
    LinkedListNode head = node;
    LinkedListNode runner = node;
    while (runner.next != null) {
      if (runner.next.data < partition) { // move
        LinkedListNode temp = runner.next;
        runner.next = runner.next.next;
        temp.next = head;
        head = temp;
      } else {
        runner = runner.next;
      }
    }
    return head;
  }

  /**
   * Used to test the solution.
   */
  public static void test(LinkedListNode node, int partition) {
    System.out.println("Before: " + node.printForward());
    System.out.println("After partitioning around " + partition + ": " + partition(node, partition).printForward());
    System.out.println("----------");
  }
}