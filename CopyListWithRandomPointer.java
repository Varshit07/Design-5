/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
/*
 * TC: O(N)
 * SC: O(1)
 * Approach: Input Linked List: a -> b -> c -> d
    Step 1: Create all new nodes and attached in the way: a -> a' -> b -> b' -> c -> c' -> d -> d'
    Step 2: Adjust random ponters: current.next.random = current.random.next
    Step 3: Restore the original and new required list by breaking the interweaved list
     a -> b -> c -> d
     a' -> b' -> c' -> d'
 */
class Solution {
    Map<Node, Node> copies;
    public Node copyRandomList(Node head) {
        // base case
        if(head == null) {
            return head;
        }
    
        Node current = head;
        
        // Step 1: Create all new nodes and attached in the way: a -> a' -> b -> b' -> c -> c' -> d -> d'
        while(current !=  null) {
            Node temp = current.next;
            Node newNode = new Node(current.val);
            current.next = newNode;
            current.next.next = temp;
            current = current.next.next;
        }
        
        current = head;
        
        // Step 2: Adjust random ponters: current.next.random = current.random.next
        while(current != null) {
            
            if(current.random != null) {
                current.next.random = current.random.next;
            }
            
            current = current.next.next;
        }
        current = head;
        Node newHead = head.next;
        Node newCurrent = newHead;
        
        // Step 3: Restore the original and new required list by breaking the interweaved list
        while(current != null) {
            Node temp = current.next.next;
            
            if(newCurrent.next != null) {
                newCurrent.next = newCurrent.next.next; 
            }
            
            current.next = temp;
            current = temp;
            newCurrent = newCurrent.next;
        }
        
        return newHead;
    }
}
