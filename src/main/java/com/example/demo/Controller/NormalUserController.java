package com.example.demo.Controller;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.NormalUser;
import com.example.demo.Repository.NormalUserRepository;
import com.example.demo.Service.NormalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/storeapi/normaluser/")
public class NormalUserController implements UserController<NormalUser> {

    @Autowired
    private NormalUserService normalUserService;
    // sign up as normal user
    @Override
    @PostMapping("signupasnormaluser")
    public boolean  signUp(@Valid @RequestBody NormalUser user){
        if(normalUserService.getUserByEmail(user.getEmail()).equals(Optional.empty())){
            normalUserService.addUser(user);
            return true;
        }
        return false;
    }

    // login as normal user
    @Override
    @GetMapping("loginasnormaluser")
    public NormalUser logIn(@Valid @RequestBody NormalUser user)  {
        if((!normalUserService.getUserByEmail(user.getEmail()).equals(Optional.empty()))
                &&(normalUserService.getUserByEmail(user.getEmail()).get().getPassword().equals(user.getPassword()))) {
            user=normalUserService.getUserByEmail(user.getEmail()).get();
            return user;
        }
    return null;
    }

}
