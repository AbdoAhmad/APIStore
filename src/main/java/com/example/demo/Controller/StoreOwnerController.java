package com.example.demo.Controller;

import com.example.demo.Model.StoreOwner;
import com.example.demo.Model.User;
import com.example.demo.Repository.StoreOwnerRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/storeapi/storeowner/")
public class StoreOwnerController implements UserController<StoreOwner> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreOwnerRepository storeOwnerRepository;

    // get all storeowners
    @Override
    @GetMapping("listallstoreowners")
    public List<StoreOwner> listAll() {
        return this.storeOwnerRepository.findAll();
    }

    //get storeowner by email
    @Override
    @GetMapping("getstoreowner/{email}")
    public ResponseEntity<StoreOwner> getUserByEmail(@PathVariable(value = "email") String userEmail) {
        StoreOwner storeOwner = storeOwnerRepository.findById(userEmail).orElseThrow();
        return ResponseEntity.ok().body(storeOwner);
    }

    // insert user
    @Override
    @PostMapping("addstoreowner")
    public StoreOwner addUser(@Valid @RequestBody StoreOwner user) {
        userRepository.save(user);
        return storeOwnerRepository.save(user);
    }

    //update user by email
    @Override
    @PutMapping("updatestoreowner/{email}")
    public ResponseEntity<StoreOwner> updateUser(@PathVariable(value = "email") String userEmail,
                                     @Valid @RequestBody StoreOwner userDetails) {
        StoreOwner storeOwner = storeOwnerRepository.findById(userEmail).orElseThrow();
        storeOwner.setName(userDetails.getName());
        storeOwner.setPassword(userDetails.getPassword());
        final StoreOwner updatedStoreOwner = storeOwnerRepository.save(storeOwner);
        return ResponseEntity.ok(updatedStoreOwner);
    }


    // delete user by email
    @Override
    @DeleteMapping("deletestoreowner/{email}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String userEmail) {
        StoreOwner storeOwner = storeOwnerRepository.findById(userEmail).orElseThrow();
        userRepository.delete(storeOwner);
        storeOwnerRepository.delete(storeOwner);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
