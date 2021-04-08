package Resources;

/**
 * a doubled link list to store and order the values from user input
 * last updated 03/30/2021
 * Author(s) Ian Holder,
 */

public class DoubledLinkList {
    class Node {
        String nodeTitle;
        int preferenceValue;
        Node previous = null;
        Node next = null;

        public Node(String _nodeTitle, int _preferenceValue) {
            nodeTitle = _nodeTitle;
            preferenceValue = _preferenceValue;
        }
    }

    Node head = null;
    Node tail = null;

    public void addElement(String _nodeTitle, int _preferenceValue) {
        Node newNode = new Node(_nodeTitle, _preferenceValue);

        //check if the list is empty
        if (head == null) {
            head = tail = newNode;
            head.previous = null;
            tail.next = null;
        } else if(searchAndPlace(newNode)){
            //does nothing the method will search a place the node if it can't the place the node on the tail
        }else{
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
            tail.next = null;
        }
        if(_preferenceValue > 0){
            orderAscending();
        }else{
            orderDescending();
        }
        printList();

        /**
        else if (listContains(_nodeTitle)) {
            Node current = head;
            Boolean found = false;

            while (current != null && !found) {
                if (current.nodeTitle.equals(_nodeTitle)) {
                    current.preferenceValue = current.preferenceValue + newNode.preferenceValue;
                    //ensure the order of the list is valid
                    if(newNode.preferenceValue == 1) {
                        orderAscending();
                    }else{
                        orderDescending();
                    }
                    found = true;
                }
                current = current.next;
                printList();
            }
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
            tail.next = null;
            printList();
        }
         */
    }

    protected boolean searchAndPlace(Node _node){
        Node current = head;
        Boolean found = false;
        while (current != null && !found) {
            if (current.nodeTitle.equals(_node.nodeTitle)) {
                current.preferenceValue = current.preferenceValue + _node.preferenceValue;
                found = true;
            }
            current = current.next;
            printList();
        }
        return found;
    }

    //search the list for if there is a node with the same title TODO change to return the node searched for or a tagged invalid node
    protected boolean listContains(String _searchingNodeTitle) {
        Node current = head;

        while (current != null) {
            if (current.nodeTitle.equals(_searchingNodeTitle)) {
                return true;
            }
            current = current.next;
        }
        //if the list is empty or the list doesn't have a node that has a matching title
        return false;
    }

    protected void orderAscending() {
        Node current = tail;

        while (current != head) {
            if (current.preferenceValue > current.previous.preferenceValue) {
                swap(current.previous, current);
            }
            current = current.previous;
        }

    }

    protected void orderDescending() {
        Node current = head;

        while (current != tail) {
            if (current.preferenceValue < current.next.preferenceValue) {
                swap(current, current.next);
            }
            current = current.next;
        }

    }

    //switch the position of two nodes in the list that are next to each other
    protected void swap(Node _lesserValue, Node _greaterValue) {
        Node tempNode = new Node(_lesserValue.nodeTitle, _lesserValue.preferenceValue);

        _lesserValue.nodeTitle = _greaterValue.nodeTitle;
        _lesserValue.preferenceValue = _greaterValue.preferenceValue;
        _greaterValue.nodeTitle = tempNode.nodeTitle;
        _greaterValue.preferenceValue = tempNode.preferenceValue;
    }

    //to help debug
    protected void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.nodeTitle + " " + current.preferenceValue);
            current = current.next;
        }
    }


}
