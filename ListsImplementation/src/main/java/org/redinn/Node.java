package org.redinn;

public class Node
{
    // A class that represents each individual node in a list. Each node contains data and a link to the next node.


    private Student data;   // Data stored in the node.
    private Node link;  // Stores the next node (will be null if the current one is already the last one)

    public Node(Student oggetto) {
        data = oggetto;
        link = null;

    }

    // Getters & Setters

    public Student getData() {
        return data;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    public void setData(Student data){
        this.data = data;
    }

}
