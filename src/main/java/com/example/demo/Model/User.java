package com.example.demo.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public  class User implements Serializable {

    @Id
    @Column(name = "useremail")
    protected String email;
    @Column(name = "username")
    protected String name;
    @Column(name = "userpassword")
    protected String password;
    @Column(name = "role")
    private String roles ;

    public Set<String> getRoles() {
        return Collections.singleton(roles);
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

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
