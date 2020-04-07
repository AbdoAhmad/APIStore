package com.example.demo.Controller;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.StoreOwner;

import com.example.demo.Repository.StoreOwnerRepository;
import com.example.demo.Service.StoreOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/storeapi/storeowner/")
public class StoreOwnerController implements UserController<StoreOwner> {

    @Autowired
    private StoreOwnerService storeOwnerService;

    // sign up as store owner
    @Override
    @PostMapping("signupasstoreowner")
    public boolean signUp(@Valid @RequestBody StoreOwner user)  {
        if(storeOwnerService.getUserByEmail(user.getEmail()).equals(Optional.empty())){
            storeOwnerService.addUser(user);
            return true;
        }
        return false;
    }

    // login as store owner
    @Override
    @GetMapping("loginasstoreowner")
    public StoreOwner logIn(@Valid @RequestBody StoreOwner user) {
        if((!storeOwnerService.getUserByEmail(user.getEmail()).equals(Optional.empty()))
                &&(storeOwnerService.getUserByEmail(user.getEmail()).get().getPassword().equals(user.getPassword()))) {
            user=storeOwnerService.getUserByEmail(user.getEmail()).get();
            return user;
        }
        return null;
    }


}
