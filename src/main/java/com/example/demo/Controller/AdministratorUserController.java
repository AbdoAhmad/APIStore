package com.example.demo.Controller;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.AdministratorUser;
import com.example.demo.Model.User;
import com.example.demo.Repository.AdministratorUserRepository;
import com.example.demo.Repository.UserRepository;
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
    UserRepository userRepository;
    @Autowired
    private AdministratorUserRepository administratorUserRepository;


    // get all users types
    @GetMapping("listall")
    public List<User> listAllUsers() {
        return this.userRepository.findAll();
    }

    // get all administrator users
    @Override
    @GetMapping("listalladministratorusers")
    public List<AdministratorUser> listAll() {
        return this.administratorUserRepository.findAll();
    }

    //get administrator user by email
    @Override
    @GetMapping("getadministratoruser/{email}")
    public Optional<AdministratorUser> getUserByEmail(@PathVariable(value = "email") String userEmail)  {
        Optional<AdministratorUser> administratorUser = administratorUserRepository.findById(userEmail);
        return administratorUser;
    }

    // insert administrator user
    @Override
    @PostMapping("addadministratoruser")
    public AdministratorUser addUser(@Valid @RequestBody AdministratorUser user) {
        userRepository.save(user);
        return administratorUserRepository.save(user);
    }

    //update administrator user by email
    @Override
    @PutMapping("updateadministratoruser/{email}")
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
    @DeleteMapping("deleteadministratoruser/{email}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String userEmail) throws ResourceNotFoundException {
        AdministratorUser administratorUser = administratorUserRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("AdministratorUser not found for this Email :: " + userEmail));
        userRepository.delete(administratorUser);
        administratorUserRepository.delete(administratorUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    // log in as admin
    @Override
    @GetMapping("loginasadmin")
    public AdministratorUser logIn(@Valid @RequestBody AdministratorUser user)  {
        if(!getUserByEmail(user.getEmail()).equals(Optional.empty())) {
            user=getUserByEmail(user.getEmail()).get();
            return user;
        }
        return null;
    }

}
