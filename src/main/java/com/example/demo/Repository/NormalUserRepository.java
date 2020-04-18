package com.example.demo.Repository;

import com.example.demo.Model.NormalUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormalUserRepository extends JpaRepository<NormalUser,String> {

}
