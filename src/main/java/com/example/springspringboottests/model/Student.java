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

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getValue() {
        return surname;
    }

    public void setValue(String surname) {
        this.surname = surname;
    }
}
