package com.example.demo.Controller;

import com.example.demo.Model.StoreOwner;

import com.example.demo.Repository.StoreOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/storeapi/storeowner/")
public class StoreOwnerController extends UserController<StoreOwner> {


    @Autowired
    private StoreOwnerRepository storeOwnerRepository;

    // sign up as StoreOwner
    @Override
    public ResponseEntity<String> signUp(@Valid @RequestBody StoreOwner user) {
        if (userRepository.existsById(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already taken!");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles("ROLE_STORE_OWNER");
        storeOwnerRepository.save(user);

        return ResponseEntity.ok("User registered successfully as StoreOwner!");

    }
}
