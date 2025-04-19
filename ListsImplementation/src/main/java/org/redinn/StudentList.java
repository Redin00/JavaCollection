package org.redinn;

// Class for a list that handles student management. The implemented methods are obviously similar to those used in the default list of java
// With some changes to the list printing and the addition of the "insertInOrder" method.

import java.util.Objects;

public class StudentList
{

    private Node head;
    private int numberOfElements;

    public StudentList()
    {
        head = null;
        numberOfElements = 0;
    }

    // Returns the head node, which can be used for recursive list viewing or other purposes.
    public Node getHead(){
        return head;
    }

    // Returns the length of the list
    public int getLenght(){
        return numberOfElements;
    }

    // Returns whether the list is empty or not
    public boolean isEmpty(){
        return numberOfElements == 0;
    }

    public void printList()
    {
        Node n = head;
        while(n != null)
        {
            // We print the information of the list by calling the method of the Student class
            n.getData().PrintInfos();

            n = n.getLink();
        }
    }


    // Method that allows us to display the contents of the list, but using recursion
    public void viewRecursive(Node n){

        if(n == null){
            return;
        }

        n.getData().PrintInfos();
        viewRecursive(n.getLink());
    }

    // Inserting an object at the head of the list
    public void insertInHead(Student data) {

        Node n = new Node(data);
        n.setLink(head);

        head = n;
        numberOfElements++;
    }

    // Inserting an object into the queue
    public void insertInQueue(Student data)
    {

        if(head == null){
            head = new Node(data);
            numberOfElements++;
            return;
        }

        Node c = new Node(data);
        Node n = head;

        while(n.getLink() != null)
        {
            n = n.getLink();
        }

        n.setLink(c);
        c.setLink(null);
        numberOfElements++;
    }

    // Method to enter in order the data of each student. The ordering is done in decreasing order starting from the grade
    public void InsertInOrder(Student data){

        // If the list is empty, we insert the data without using controls and various cycles
        if(head == null){
            head = new Node(data);
            return;
        }

        Node n = new Node(data);    // New node to enter.
        Node previous = head;
        Node next = head.getLink();


        // scrolling until we reach the end or until we find a grade lower than the grade we want to enter
        while(next != null && next.getData().getGrade() > data.getGrade()){

            previous = next;
            next = next.getLink();
        }

        
        if(next == null){
            insertInQueue(data);
        }
        else{
            n.setLink(next);
            previous.setLink(n);
            numberOfElements++;
        }

    }


    // Removing the element at the top of the list
    public void deleteInHead()
    {
        if(head != null)
        {
            head = head.getLink();
            numberOfElements--;
        }
    }


    // Removing the element at the end of the list
    public void deleteInQueue()
    {

        if(numberOfElements > 1) {
            Node previous = head;
            Node next = head.getLink();

            while (next.getLink() != null) {
                previous = next;
                next = next.getLink();
            }

            previous.setLink(null);
            numberOfElements--;
        }
        else{
            deleteInHead();
        }
    }


    // Deletion based on input parameters. The parameters used for this deletion are the first name and last name.
    public boolean deleteInOrder(String name, String surname){

        // The method returns "true" if a match is found, "false" if no element with the entered parameters exists.

        if(head != null) {
            // Method to delete an element from the list of our choice, without knowing the position of the latter
            Node previous = head;
            Node next = head.getLink();

            // First the head node is checked to see if the entered parameters match it, otherwise the list is cycled..
            if (Objects.equals(head.getData().getName(), name) && Objects.equals(head.getData().getSurname(), surname)) {
                deleteInHead();
                return true;
            }
            else if(next != null)
            {
                // We scroll through the list until we reach a node in which the name and surname passed as parameters have been found or until we reach the end of the list.
                while (next.getLink() != null && (!Objects.equals(next.getData().getName(), name) || !Objects.equals(next.getData().getSurname(), surname))) {
                    previous = next;
                    next = next.getLink();
                }

                // Let's check that the current node has the past parameters, if so let's delete it.
                if(Objects.equals(next.getData().getName(), name) && Objects.equals(next.getData().getSurname(), surname)){
                    previous.setLink(next.getLink());  // This allows us to move the link to the node after the one we want to delete.
                    numberOfElements--;
                    return true;
                }

            }
        }

        return false;

    }


    // Method to delete all elements from the list
    public void deleteAll(){

        if(head != null){
            head.setLink(null);
            head = null;
        }

    }


    // Returns the data contained in the first element
    public Student getFirst(){
        return head.getData();
    }


    // Returns the data contained in the last element
    public Student getLast(){

        Node n = head;

        while(n.getLink() != null){
            n = n.getLink();
        }

        return n.getData();
    }


    // Method used to set the value of an element in the list, iterating based on the index.
    public void set(int index, Student data){

        Node n = head;

        for(int i=index; i>0; i--){
            n = n.getLink();
        }

        n.setData(data);
    }

}
