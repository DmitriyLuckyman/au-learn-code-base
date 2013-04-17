package ru.spbau.bandurin.task7;

/**
 * Simple bean represent Student
 * @author Dmitriy Bandurin
 *         Date: 08.04.13
 */
public class Student {
    private String name;
    private String surname;
    private int age1;
    private double avgGrade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age1;
    }

    public void setAge(int age1) {
        this.age1 = age1;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age1=" + age1 +
                ", avgGrade=" + avgGrade +
                '}';
    }
}
