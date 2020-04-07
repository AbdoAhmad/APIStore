package com.example.demo.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.AdministratorUser;
import com.example.demo.Model.User;
import com.example.demo.Repository.AdministratorUserRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdministratorUserService implements UserService<AdministratorUser> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdministratorUserRepository administratorUserRepository;


    // get all users types
    public List<User> listAllUsers() {
        return this.userRepository.findAll();
    }

    public Long count() {
        return this.userRepository.count();
    }
    // get all administrator users
    @Override
    public List<AdministratorUser> listAll() {
        return this.administratorUserRepository.findAll();
    }

    //get administrator user by email
    @Override
    public Optional<AdministratorUser> getUserByEmail(@PathVariable(value = "email") String userEmail)  {
        Optional<AdministratorUser> administratorUser = administratorUserRepository.findById(userEmail);
        return administratorUser;
    }

    // insert administrator user
    @Override
    public AdministratorUser addUser(@Valid @RequestBody AdministratorUser user) {
        return administratorUserRepository.save(user);
    }

    //update administrator user by email
    @Override
    public ResponseEntity<AdministratorUser> updateUser(@PathVariable(value = "email") String userEmail,
                                     @Valid @RequestBody AdministratorUser userDetails) throws ResourceNotFoundException {
        AdministratorUser administratorUser = administratorUserRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("AdministratorUser not found for this Email :: " + userEmail));
        administratorUser.setName(userDetails.getName());
        administratorUser.setPassword(userDetails.getPassword());
        final AdministratorUser updatedAdministratorUser = administratorUserRepository.save(administratorUser);
        return ResponseEntity.ok(updatedAdministratorUser);
    }


    // delete administrator user by email
    @Override
    public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String userEmail) throws ResourceNotFoundException {
        AdministratorUser administratorUser = administratorUserRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("AdministratorUser not found for this Email :: " + userEmail));
        administratorUserRepository.delete(administratorUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
