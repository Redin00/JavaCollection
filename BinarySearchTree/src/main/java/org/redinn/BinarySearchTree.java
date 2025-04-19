package org.redinn;

// BST = Binary Search Tree, a particular type of three that can have a max of 2 sons,
// and where for each node N, the nodes of the left subtree contain values smaller than node N, and the nodes of the right subtree contain only values greater than node N.
public class BinarySearchTree {

    private NodeBST ptr; // Root of the tree

    public BinarySearchTree(){
        ptr = null;
    }

    // Returns the root of the tree
    public NodeBST getPtr(){
        return ptr;
    }


    // Adding node to a BST, then checking each node and comparing its value to the one to be inserted.
    public void addNode(int data){
        NodeBST newNode = new NodeBST(data);
        NodeBST currentNode, previousNode;

        if(ptr == null){
            ptr = new NodeBST(data);
        }
        else{
            previousNode = ptr;
            if(data < previousNode.getData()){
                currentNode = previousNode.getPtrSX();
            }
            else{
                currentNode = previousNode.getPtrDX();
            }

            while(currentNode != null){
                previousNode = currentNode;
                if(data < previousNode.getData()){
                    currentNode = previousNode.getPtrSX();
                }
                else{
                    currentNode = previousNode.getPtrDX();
                }
            }

            if(data < previousNode.getData()){
                previousNode.setPtrSX(newNode);
            }
            else{
                previousNode.setPtrDX(newNode);
            }
        }
    }

    // The preorder visit prints the root first, then the left subtree, and finally the right subtree. It does this by using recursion.
    public void preOrderVisit(NodeBST n){
        if(n == null){
            return;
        }
        System.out.print(n.getData() + " ");
        if(n.getPtrSX() != null){
            preOrderVisit(n.getPtrSX());
        }
        if(n.getPtrDX() != null){
            preOrderVisit(n.getPtrDX());
        }
    }

    // In order visit consists of visiting the left subtree first, then the root, and finally the right subtree. To do this, it uses recursion.
    public void inOrderVisit(NodeBST n){
        if(n == null){
            return;
        }

        if(n.getPtrSX() != null){
            inOrderVisit(n.getPtrSX());
        }
        System.out.print(n.getData() + " ");
        if(n.getPtrDX() != null){
            inOrderVisit(n.getPtrDX());
        }

    }

    // The post order visit consists of visiting the left subtree first, then the right subtree, and finally the root. To do this, it uses recursion.
    public void postOrderVisit(NodeBST n){
        if(n == null){
            return;
        }

        if(n.getPtrSX() != null){
            postOrderVisit(n.getPtrSX());
        }
        if(n.getPtrDX() != null){
            postOrderVisit(n.getPtrDX());
        }
        System.out.print(n.getData() + " ");
    }


    // Method used to find a node in the BST, which returns true or false.
    public boolean findNode(NodeBST n, int valore){
        if(n == null){
            return false;
        }

        if(n.getData() == valore){
            return true;
        }

        if(n.getData() < valore){
            return findNode(n.getPtrDX(), valore);
        }

        if(n.getData() > valore){
            return findNode(n.getPtrSX(), valore);
        }

        return false;
    }
}
