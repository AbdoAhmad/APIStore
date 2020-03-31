package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "normaluser")
@PrimaryKeyJoinColumn(name = "normaluseremail")
public class NormalUser extends User {

    public NormalUser(String name, String email, String password) {
        super(name, email,password);
    }
}
