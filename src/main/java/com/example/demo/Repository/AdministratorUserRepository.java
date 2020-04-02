package com.example.demo.Repository;

import com.example.demo.Model.AdministratorUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorUserRepository extends JpaRepository<AdministratorUser,String> {

}
