package com.example.demo.Controller;

import com.example.demo.Model.NormalUser;
import com.example.demo.Repository.NormalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/storeapi/normaluser/")
public class NormalUserController extends UserController<NormalUser> {

    @Autowired
    private NormalUserRepository normalUserRepository;

    // sign up as normal user
    @Override
    public ResponseEntity<?> signUp(@Valid @RequestBody NormalUser user){
        if (userRepository.existsById(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already taken!");
        }
        user.setRoles("ROLE_NORMAL_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        normalUserRepository.save(user);

        return ResponseEntity.ok("User registered successfully as NormalUser!");
    }

}