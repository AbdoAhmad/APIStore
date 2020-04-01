package com.example.demo.Model;

import javax.persistence.*;
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public  class User {

    @Id
    @Column(name = "useremail")
    protected String email;
    @Column(name = "username")
    protected String name;
    @Column(name = "userpassword")
    protected String password;

    public User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.password = password;
        this.email=email;
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
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
