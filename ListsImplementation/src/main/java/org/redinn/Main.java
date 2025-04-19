package org.redinn;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // This program is designed to recreate lists in java, to understand how they work and how to manage them.
        // The list in this program is used to store student data, through an interactive menu.
        // Two main classes are used: Node, StudentList and Student, that represents the Object stored.


        System.out.println("===========================================");
        System.out.println("Welcome to the List Management Program!");
        System.out.println("===========================================");
        System.out.println();

        StudentList list = new StudentList();
        String ch = ""; // Variable for choosing the command in the interactive menu used for managing the list.

        Scanner input = new Scanner(System.in);

        System.out.println("######### IMPLEMENTING LISTS IN JAVA\n #########\n");

        boolean loop = true;
        // Loop for the program
        while(loop){

            System.out.println("Decide what operations to perform on the student list! (PROGRAM OUTPUT: 0)");
            System.out.println("1 <> Print student list");
            System.out.println("2 <> Add a student to the list");
            System.out.println("3 <> Delete a student from the list\n");

            System.out.print("Operation to perform\n >> ");
            ch = input.nextLine();

            switch(ch){
                case "0":
                    loop = false;

                    System.out.println("Program exit in progress....");
                    Thread.sleep(500);

                    break;
                case "1":

                    if(list.isEmpty()){
                        System.out.println("The list is currently empty.");
                    }
                    else{
                        System.out.println("Student list:");
                        list.printList();
                    }
                    System.out.println("\n");
                    break;
                case "2":

                    System.out.print("\nEnter new student name\n >> ");
                    String tempName = input.nextLine(); // Temp variable for storing name
                    System.out.print("Enter surname >> ");
                    String tempSurname = input.nextLine();  // Temp variable for storing surname
                    System.out.print("Grade >> ");  // Temp variable for storing grade
                    int tempGrade = input.nextInt();

                    input.nextLine(); // Let's remove the "\n" that is dropped by the nextInt() function and which can create an input jump later.


                    System.out.println("\nChoose the type of insertion you want:");
                    System.out.println("1 - Queue entry");
                    System.out.println("2 - Head insertion");
                    System.out.println("3 - Placement in order (based on highest grade)");

                    System.out.print("Operation to perform >> ");
                    ch = input.nextLine();

                    if(Objects.equals(ch, "1")){
                        list.insertInQueue(new Student(tempName, tempSurname, tempGrade));
                    }
                    else if (Objects.equals(ch, "2")) {
                        list.insertInHead(new Student(tempName, tempSurname, tempGrade));
                    }
                    else if(Objects.equals(ch, "3")){
                        list.InsertInOrder(new Student(tempName, tempSurname, tempGrade));
                    }

                    System.out.println("Student successfully entered!\n\n");

                    break;

                case "3":

                    System.out.println("\nChoose the type of deletion:");
                    System.out.println("1 - Queue deletion");
                    System.out.println("2 - Elimination in the head");
                    System.out.println("3 - Deleting all items");
                    System.out.println("4 - Delete in order (based on the name and surname entered)");

                    System.out.print("Operation to perform >> ");
                    ch = input.nextLine();

                    // You could also implement a switch case, but I preferred to use if and else-if to make it less redundant.
                    if(Objects.equals(ch, "1")){
                        list.deleteInQueue();
                        System.out.println("Student successfully removed from list.");
                    }
                    else if(Objects.equals(ch, "2")){
                        list.deleteInHead();
                        System.out.println("Student successfully removed from list.");
                    }
                    else if(Objects.equals(ch, "3")){
                        list.deleteAll();
                        System.out.println("Student successfully removed from list.");
                    }
                    else if(Objects.equals(ch, "4")){
                        System.out.print("Enter the name of the student to remove from the list >> ");
                        String nome = input.nextLine();
                        System.out.print("Enter the last name now >> ");
                        String cognome = input.nextLine();

                        if(list.deleteInOrder(nome, cognome)){
                            System.out.println("Student successfully removed from list.");
                        }
                        else{
                            System.out.println("The student you were looking for was not found.");
                        }
                    }
                    else{
                        System.out.println("Error! The number entered is not included in the range of choices (1-4).");
                    }

                    System.out.println("\n");


                    break;
                default:
                    System.out.println("\nError! The number entered is not compressed in the range of choices (1-3).");

            }

            if(loop){
                System.out.print("Press enter to continue...");
                input.nextLine();
                System.out.println();
            }


        }

        input.close();
        // End of the program.

    }
}