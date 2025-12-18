package com.demoapp.demoappbox.model;

public class User {

    private String name;
    private String roll_number;
    private String subject;

    public User() { }

    public User(String name, String roll_number, String subject) {
        this.name = name;
        this.roll_number = roll_number;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public String getSubject() {
        return subject;
    }
}
