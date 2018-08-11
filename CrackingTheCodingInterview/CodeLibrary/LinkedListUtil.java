/**
 * Utility class for LinkedListNode.
 */
public class LinkedListUtil {
  public static LinkedListNode toSinglyLinkedList(int[] array) {
    LinkedListNode[] nodes = new LinkedListNode[array.length];
    nodes[array.length - 1] = new LinkedListNode(array[array.length - 1]);
    for (int i = array.length - 2; i >= 0; i--) {
      nodes[i] = new LinkedListNode(array[i]);
      // singly linked list so no setNext() which will set prev
      nodes[i].next = nodes[i + 1];
    }
    return nodes[0];
  }
}