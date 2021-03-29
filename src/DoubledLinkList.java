package src;

/**
 * a doubled link list to store and order the values from user input
 * last updated 03/28/2021
 * Author(s) Ian Holder,
 */

public class DoubledLinkList {
    class Node{
        String nodeTitle;
        int preferenceValue;
        Node previous;
        Node next;

        public Node(String _nodeTitle, int _preferenceValue){
            nodeTitle = _nodeTitle;
            preferenceValue = _preferenceValue;
        }
    }
    Node head,tail = null;

    public void addNode(String _nodeTitle){
        Node newNode = new Node(_nodeTitle, 1);

        //check if the list is empty
        if(head == null){
            head = tail = newNode;
            head.previous = null;
            tail.next = null;
        }else if(listContains(_nodeTitle)){
            Node current = head;

            while (current != null){
                if(current.nodeTitle == _nodeTitle){
                    current.preferenceValue = current.preferenceValue + 1;
                    orderAscending();
                }
                current = current.next;
            }
        }else{
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
            tail.next = null;
        }
    }

    //search the list for if there is a node with the same title
    protected boolean listContains(String _searchingNodeTitle){
        Node current = head;

        while(current != null){
            if(current.nodeTitle == _searchingNodeTitle){
                return true;
            }
            current = current.next;
        }
        //if the list is empty or the list doesn't have a node that has a matching title
        return false;
    }

    public void removeNode(String _nodeTitle){
        if(listContains(_nodeTitle)){
            Node current = head;

            while(current != null){
                if(current.nodeTitle == _nodeTitle){
                    if(current.preferenceValue > 1){
                        current.preferenceValue = current.preferenceValue - 1;
                        orderDescending();
                    }else{
                        remove(current);
                    }
                }
                current = current.next;
            }
        }
    }

    protected void remove(Node _nodeForRemoval){
        _nodeForRemoval.next.previous = _nodeForRemoval.previous;
        _nodeForRemoval.previous.next = _nodeForRemoval.next;
    }

    protected void orderAscending(){
        Node current = tail;
        if(current != head) {
            while (current != null) {
                if (current.preferenceValue > current.previous.preferenceValue) {
                    swap(current.previous, current);
                }
                current = current.previous;
            }
        }
    }

    protected void orderDescending(){
        Node current = head;
        if(current != tail) {
            while (current != null) {
                if (current.preferenceValue < current.next.preferenceValue) {
                    swap(current, current.next);
                }
                current = current.next;
            }
        }
    }

    //switch the position of two nodes in the list
    protected void swap(Node _lesserValue, Node _greaterValue){
        _greaterValue.previous = _lesserValue.previous;
        _greaterValue.next.previous = _lesserValue;
        _greaterValue.next = _lesserValue;
        _lesserValue.previous = _greaterValue;
    }


}
