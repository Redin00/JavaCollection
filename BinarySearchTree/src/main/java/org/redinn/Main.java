package org.redinn;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Project for the implementation of a binary search tree.
        // Below is the step-by-step process for inserting elements.

        Scanner input = new Scanner(System.in);
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println(ColorText.GREEN + "Welcome to the awesome Binary Search Trees project!!" + ColorText.RESET);
        System.out.print("Lets start with the creation of the tree..");

        // Small simple "animation" for creating the tree
        for(int i=0; i<5; i++){
            Thread.sleep(500);
            System.out.print(".");
        }
        System.out.println("Binary search tree created\n!");

        // ######### DATA INSERT INTO THE BINARY SEARCH TREE #########
        BinarySearchTree BST = new BinarySearchTree();
        System.out.println(ColorText.YELLOW + "Now let's add the following numbers in order\n:" + ColorText.CYAN + " 23, 45, 12, 57, 42, 15, 87" + ColorText.RESET);
        System.out.print("Press ENTER to enter numbers\n...");
        input.nextLine();

        BST.addNode(23);
        BST.addNode(45);
        BST.addNode(12);
        BST.addNode(57);
        BST.addNode(42);
        BST.addNode(15);
        BST.addNode(87);

        // ######### PRE ORDER VISIT #########
        System.out.println("\nNumbers entered correctly\n!");
        System.out.print("Press enter to see the \"pre-order\" visit of the newly created tree\n!");
        input.nextLine();
        System.out.println();

        System.out.println(ColorText.YELLOW + "Here is the \"pre-order\" visit\n: " + ColorText.CYAN);
        BST.preOrderVisit(BST.getPtr());

        // ######### IN ORDER VISIT #########
        System.out.print(ColorText.RESET + "\nPress enter to view the tree with the \"In-order\" visit instead\n!");
        input.nextLine();
        System.out.println();

        System.out.println(ColorText.YELLOW + "Here is the \"In-order\" visit: " + ColorText.CYAN);
        BST.inOrderVisit(BST.getPtr());

        // ######### POST ORDER VISIT #########
        System.out.println(ColorText.RESET + "\nPress enter to finally see the \"post-order\" visit\n!");
        input.nextLine();

        System.out.println(ColorText.YELLOW + "Here is the \"post-order\" visit: " + ColorText.CYAN);
        BST.postOrderVisit(BST.getPtr());

        // ######### SEARCH NODE #########
        System.out.print(ColorText.RESET + "\nNow enter a number to search in the tree\n: ");
        if(BST.findNode(BST.getPtr(), input.nextInt())){
            System.out.println(ColorText.GREEN + "The entered number exists in the tree\n!" + ColorText.RESET);
        }
        else{
            System.out.println(ColorText.RED + "The entered number was not found in the tree!\n" + ColorText.RESET);
        }

        // Program finished
        String endProgram = "END >:-( ";
        for(int i=0; i<endProgram.length(); i++){
            Thread.sleep(200);
            System.out.print(ColorText.GREEN + endProgram.chars().mapToObj(ch -> (char)ch).toArray(Character[] ::new)[i]);
        }

    }
}