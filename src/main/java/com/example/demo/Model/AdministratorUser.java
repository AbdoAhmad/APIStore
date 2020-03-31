package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "administratoruser")
@PrimaryKeyJoinColumn(name = "administratoruser")
public class AdministratorUser extends User {


    public AdministratorUser(String name,String email ,String password) {

        super(name,email, password);
    }

}
