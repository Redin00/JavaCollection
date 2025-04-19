package org.redinn;

public class Student {

    // ------- This class represents the object stored in the list -------

    private String name;
    private String surname;
    private int grade;

    public Student(String name, String surname, int grade){
        this.name = name;
        this.surname = surname;
        this.grade = grade;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public int getGrade(){
        return grade;
    }

    // Print infos about a student.
    public void PrintInfos(){

        // Pattern to display infos about a student.
        System.out.println("Name: " + name + " Surname: " + surname + " | " + "Grade: " + grade);
    }

}
