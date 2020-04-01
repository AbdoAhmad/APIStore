package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "storeowner")
@PrimaryKeyJoinColumn(name = "storeowneremail")
public class StoreOwner extends User {
    public StoreOwner() {
        super();
    }

    public StoreOwner(String name, String email, String password) {
        super(name, email,password);
    }
}
