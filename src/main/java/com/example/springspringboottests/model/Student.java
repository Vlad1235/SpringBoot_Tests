package com.example.springspringboottests.model;

public class Student {
    private String name;
    private String surname;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
