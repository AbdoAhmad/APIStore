package com.example.demo.Model;

public abstract class User {
    String name;
    String email;
    String Password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        Password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    }
