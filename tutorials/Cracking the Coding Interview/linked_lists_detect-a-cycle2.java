
Detect a cycle in a linked list. Note that the head pointer may be 'null' if the list is empty.

A Node is defined as:
    class Node {
        int data;
        Node next;
    }


boolean hasCycle(Node head) {

    if(head == null) // list does not exist..so no loop either.
        return false;

    Node linkIndex;
    linkIndex = head;

    HashMap ref = new HashMap(); // create HashMap
    ref.put(linkIndex.next, linkIndex);

    while(true) {
        linkIndex = linkIndex.next; // traverse to next link

        if(ref.containsKey(linkIndex) == true) // if a previous node points to the next node, we have a loop.
          return true;

        if(linkIndex == null) // if end of linklist found, there is no loop
          return false;

        ref.put(linkIndex.next, linkIndex);

      }

}
