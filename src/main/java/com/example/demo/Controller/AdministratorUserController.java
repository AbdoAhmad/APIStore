package com.example.demo.Controller;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.AdministratorUser;
import com.example.demo.Model.User;
import com.example.demo.Repository.AdministratorUserRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.AdministratorUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/storeapi/administratoruser/")
public class AdministratorUserController implements UserController<AdministratorUser> {

    @Autowired
    private AdministratorUserService administratorUserService;


    // get all users types
    @GetMapping("listall")
    public List<User> listAllUsers() {
        return this.administratorUserService.listAllUsers();
    }

    @Override
    @PostMapping("signupasadmin")
    public boolean signUp(@Valid  @RequestBody AdministratorUser user) {
        if(administratorUserService.getUserByEmail(user.getEmail()).equals(Optional.empty())&&administratorUserService.count()<1){
            administratorUserService.addUser(user);
            return true;
        }
        return false;
    }

    // log in as admin
    @Override
    @GetMapping("loginasadmin")
    public AdministratorUser logIn(@Valid @RequestBody AdministratorUser user)  {
        if((!administratorUserService.getUserByEmail(user.getEmail()).equals(Optional.empty()))
                &&(administratorUserService.getUserByEmail(user.getEmail()).get().getPassword().equals(user.getPassword()))) {
            user=administratorUserService.getUserByEmail(user.getEmail()).get();
            return user;
        }
        return null;
    }

}
