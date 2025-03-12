/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
class Person {
    private String name;
    private int age;

    public String getName() { 
        return name;
     }
    public void setName(String name) { 
        this.name = name;
     }

    public int getAge() { 
        return age;
     }
    public void setAge(int age) { 
        this.age = age;
     }

    public void displayPerson() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

public class task3 {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("ahmad ali");
        person.setAge(19);
        person.displayPerson();
    }
}
