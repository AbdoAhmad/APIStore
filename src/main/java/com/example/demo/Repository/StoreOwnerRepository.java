package com.example.demo.Repository;

import com.example.demo.Model.StoreOwner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreOwnerRepository extends JpaRepository<StoreOwner,String> {

}
