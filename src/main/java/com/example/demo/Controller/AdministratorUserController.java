package com.example.demo.Controller;

import com.example.demo.Model.AdministratorUser;
import com.example.demo.Repository.AdministratorUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/storeapi/administratoruser/")
public class AdministratorUserController extends UserController<AdministratorUser> {

    @Autowired
    private AdministratorUserRepository administratorUserRepository;



    // list all users
    @GetMapping("listall")
    @PreAuthorize("hasRole('ADMIN')")
    public List<?> listAll() {
        return this.userRepository.findAll();
    }

    @Override
    public ResponseEntity<String> signUp(@Valid  @RequestBody AdministratorUser user) {
        if (userRepository.existsById(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already taken!");
        }
        else if(administratorUserRepository.count()>=1){
            return ResponseEntity.badRequest().body("Error: Server already Admin !");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles("ROLE_ADMIN");
        administratorUserRepository.save(user);
        return ResponseEntity.ok("User registered successfully as Admin!");
    }


}
