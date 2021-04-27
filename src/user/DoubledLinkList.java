package user;

/*
 * a doubled link list to store and order the values from user input
 * last updated 04/24/2021
 * Author(s) Ian Holder,
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DoubledLinkList {
    public static class Node {
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
        if (empty()) {
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
        //order the list after adding/changing the value of an element
        if(_preferenceValue > 0){
            orderAscending();
        }else{
            orderDescending();
        }
        printList();

    }

    //ordering

    //if the list is not empty it will transverse the list to try and find a node with the same string title
    private boolean searchAndPlace(Node _node){
        //create a pointer node starting at the head
        Node current = this.head;
        //use a variable to get out of the loop when the element is found
        boolean found = false;
        //continue till the element is found or hitting the end of the list
        while (current != null && !found) {
            if (current.nodeTitle.equals(_node.nodeTitle)) {
                current.preferenceValue = current.preferenceValue + _node.preferenceValue;
                found = true;
            }
            current = current.next;
        }
        return found;
    }

    //if an element is added/value incremented order from tail up
    private void orderAscending() {
        //starting from the bottom up as if an element value was increased the element before it could have a lower value
        Node current = this.tail;

        //keep going till the current pointer is the list head
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
    private void printList() {
        Node current = this.head;
        while (current != null) {
            System.out.println(current.nodeTitle + " " + current.preferenceValue);
            current = current.next;
        }
    }

    //just checks if a given node matches a node in this list
    private boolean hasNode(Node _toCompare){
        boolean found = false;
        Node current = this.head;
        while(current != null && !found){
            if(_toCompare.nodeTitle.equals(current.nodeTitle) && _toCompare.preferenceValue == current.preferenceValue){
                found = true;
            }
            current = current.next;
        }
        return found;
    }

    //to evaluate if two link lists have the same content
    public Boolean isEqual(DoubledLinkList _toCompare){
        boolean doesMatch = false;
        if(this.empty() && _toCompare.empty()){
            return true;
        }
        //check that every element in the input list is in this list
        Node current = _toCompare.head;
        while(current != null){
            if(hasNode(current)){
                doesMatch = true;
                current = current.next;
            }else{
                doesMatch = false;
                current = null;
            }
        }
        return doesMatch;
    }

    //================= GETTERS ===============
    public String greatestValue(){
        //as the list is ordered then the head node should be the highest value in the list
        return this.head.nodeTitle;
    }

    public String lowestValue(){
        return this.tail.nodeTitle;
    }

    public boolean empty(){
        return this.head == null;
    }
}
