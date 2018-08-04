/**
  * Q2.1(Remove Dups): Write code to remove duplicatees from an unsorted linked
  * list. How would you solve this problem if a temporary bufer is not allowed?
  */
import java.util.HashSet;

public class Solution {
  private static LinkedListNode first = new LinkedListNode();
  private static LinkedListNode second = new LinkedListNode();
  private static LinkedListNode third = new LinkedListNode();

  public static void main(String[] args) {
    // Test 1
    first.data = 1;
    second.data = 2;
    third.data = 3;

    resetNodes();
    test(first, true);
    test(first, false);

    second.data = 1;
    test(first, true);
    resetNodes();
    test(first, false);

    resetNodes();
    third.data = 1;
    test(first, true);
    resetNodes();
    test(first, false);

    resetNodes();
    second.data = 5;
    test(first, true);
    resetNodes();
    test(first, false);
  }
  
  /**
   * Time complexity: O(n) where n is the size of the list, since operations on
   * HashSet is O(1).
   */
  public static void removeDuplicatesWithBuffer(LinkedListNode node) {
    HashSet<Integer> buffer = new HashSet<>();
    while (node != null) {
      if (buffer.contains(node.data)) { // duplicate!
        node.prev.setNext(node.next);
      } else {
        buffer.add(node.data);
      }
      node = node.next;
    } 
  }

  /**
   * Time complexity: O(n^2) where n is the size of the list, since for each
   * node we have to scan the entire list to look for duplicates.
   */
  public static void removeDuplicatesWithoutBuffer(LinkedListNode node) {
    LinkedListNode runner = node.next;
    while (node != null) {
      while (runner != null) {
        if (node.data == runner.data) {
          runner.prev.setNext(runner.next);
        }
        runner = runner.next;
      }
      node = node.next;
    }
  }

  /**
   * Used to test the solution.
   */
  public static void test(LinkedListNode node, boolean withBuffer) {
    System.out.print("Before: " + node.printForward());
    if (withBuffer) {
      System.out.println("Remove Duplicates WITH Buffer");
      removeDuplicatesWithBuffer(node);
    } else {
      System.out.println("Remove Duplicates WITHOUT Buffer");
      removeDuplicatesWithoutBuffer(node);
    }
    System.out.print("After: " + node.printForward());
    System.out.println("----------");
  }

  private static void resetNodes() {
    first.setNext(second);
    second.setPrevious(first);
    second.setNext(third);
    third.setPrevious(second);
  }
}
