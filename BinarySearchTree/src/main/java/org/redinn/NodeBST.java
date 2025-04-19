package org.redinn;

// Class that represents each node in the tree.
public class NodeBST {

    private int data;        // For this BST the "int" type is used, to simplify the representation of the tree and understand how it works.
    private NodeBST ptrDX;  // Pointer for left child
    private NodeBST ptrSX;  // Pointer for right child

    public NodeBST(int data) {
        this.data = data;
        ptrDX = null;
        ptrSX = null;
    }

    // Getters
    public int getData(){
        return data;
    }

    public NodeBST getPtrDX(){
        return ptrDX;
    }

    public NodeBST getPtrSX(){
        return ptrSX;
    }

    // Setters

    public void setData(int data) {
        this.data = data;
    }

    public void setPtrDX(NodeBST n){
        ptrDX = n;
    }

    public void setPtrSX(NodeBST n){
        ptrSX = n;
    }
}
