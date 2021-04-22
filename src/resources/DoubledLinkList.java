package resources;

import java.util.HashMap;

/*
 * a doubled link list to store and order the values from user input
 * last updated 04/08/2021
 * Author(s) Ian Holder,
 */

public class DoubledLinkList {
    private static class Node {
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
        if (this.head == null) {
            this.head = this.tail = newNode;
            this.head.previous = null;
            this.tail.next = null;
            //if the node can't be found in the list, then put on the end
        } else if(!searchAndPlace(newNode)){
            this.tail.next = newNode;
            newNode.previous = this.tail;
            this.tail = newNode;
            this.tail.next = null;
        }
        if(_preferenceValue > 0){
            orderAscending();
        }else{
            orderDescending();
        }
        printList();

    }

    //if the list is not empty it will transverse the list to try and find a node with the same string title
    private boolean searchAndPlace(Node _node){
        Node current = this.head;
        boolean found = false;
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

    //if an element is added/value incremented order from tail up
    private void orderAscending() {
        Node current = this.tail;

        while (current != this.head) {
            if (current.preferenceValue > current.previous.preferenceValue) {
                swap(current.previous, current);
            }
            current = current.previous;
        }

    }

    //order from head down if a value is decremented
    private void orderDescending() {
        Node current = this.head;

        while (current != tail) {
            if (current.preferenceValue < current.next.preferenceValue) {
                swap(current, current.next);
            }
            current = current.next;
        }

    }

    //switch the values of two nodes
    private void swap(Node _lesserValue, Node _greaterValue) {
        Node tempNode = new Node(_lesserValue.nodeTitle, _lesserValue.preferenceValue);

        _lesserValue.nodeTitle = _greaterValue.nodeTitle;
        _lesserValue.preferenceValue = _greaterValue.preferenceValue;
        _greaterValue.nodeTitle = tempNode.nodeTitle;
        _greaterValue.preferenceValue = tempNode.preferenceValue;
    }

    //to help debug
    protected void printList() {
        Node current = this.head;
        while (current != null) {
            System.out.println(current.nodeTitle + " " + current.preferenceValue);
            current = current.next;
        }
    }

    //================= GETTERS ===============
    public String greatestValue(){
        return this.head.nodeTitle;
    }

    public String lowestValue(){
        return this.tail.nodeTitle;
    }

    //for data storage allow for the link lists to be turned into hash maps
    public HashMap<String,Integer> toMap(){
        HashMap<String,Integer> toReturn = new HashMap<>();
        Node current = this.head;

        while(current != this.tail){
            toReturn.put(current.nodeTitle, current.preferenceValue);
            current = current.next;
        }
        return toReturn;
    }

    //================= SETTERS ===============
    public void fromMap(HashMap<String,Integer> _inputMap){

    }
}
