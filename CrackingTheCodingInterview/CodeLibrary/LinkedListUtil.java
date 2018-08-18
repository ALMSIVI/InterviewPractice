/**
 * Utility class for LinkedListNode.
 */
public class LinkedListUtil {
  /**
   * Converts an integer array to a singly linked list.
   * @param array the integer array to be converted
   * @return the head of the converted linked list
   */
  public static LinkedListNode toSinglyLinkedList(int[] array) {
    LinkedListNode[] nodes = new LinkedListNode[array.length];
    nodes[array.length - 1] = new LinkedListNode(array[array.length - 1]);
    for (int i = array.length - 2; i >= 0; i--) {
      nodes[i] = new LinkedListNode(array[i]);
      // singly linked list so no setNext() which will set prev
      nodes[i].next = nodes[i + 1];
      nodes[i].last = nodes[array.length - 1];
    }
    return nodes[0];
  }

  /**
   * Converts an integer array to a doubly linked list.
   * @param array the integer array to be converted
   * @return the head of the converted linked list
   */
  public static LinkedListNode toDoublyLinkedList(int[] array) {
    LinkedListNode head = toSinglyLinkedList(array);
    LinkedListNode runner = head;
    while (runner.next != null) {
      runner.next.prev = runner;
      runner = runner.next;
    }
    return head;
  }

  /**
   * Calculates the length of a LinkedListNode.
   * @param a the head of a linked list of unknown length.
   * @return length of the list
   */
  public static int length(LinkedListNode a) {
    int length = 0;
    while (a != null) {
      length++;
      a = a.next;
    }
    return length;
  }

  /**
   * Gets the last element of the linked list node.
   * Adds edge case checks.
   */
  public static LinkedListNode last(LinkedListNode a) {
    if (a == null) {
      return null;
    }
    if (a.last == null) {
      LinkedListNode runner = a;
      while (runner.next != null) {
        runner = runner.next;
      }
      a.last = runner;
    }
    return a.last;
  }
}