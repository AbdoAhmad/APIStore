package com.example.demo.Controller;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.StoreOwner;

import com.example.demo.Repository.StoreOwnerRepository;
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
    private StoreOwnerRepository storeOwnerRepository;

    // get all store owners
    @Override
    @GetMapping("listallstoreowners")
    public List<StoreOwner> listAll() {
        return this.storeOwnerRepository.findAll();
    }

    //get store owner by email
    @Override
    @GetMapping("getstoreowner/{email}")
    public Optional<StoreOwner> getUserByEmail(@PathVariable(value = "email") String userEmail) {
        Optional<StoreOwner> storeOwner = storeOwnerRepository.findById(userEmail);
        return storeOwner;
    }

    // insert store owner
    @Override
    @PostMapping("addstoreowner")
    public StoreOwner addUser(@Valid @RequestBody StoreOwner user) {
        return storeOwnerRepository.save(user);
    }

    //update store owner by email
    @Override
    @PutMapping("updatestoreowner/{email}")
    public ResponseEntity<StoreOwner> updateUser(@PathVariable(value = "email") String userEmail,
                                     @Valid @RequestBody StoreOwner userDetails)throws ResourceNotFoundException {
        StoreOwner storeOwner = storeOwnerRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("StorOwner not found for this Email :: " + userEmail));
        storeOwner.setName(userDetails.getName());
        storeOwner.setPassword(userDetails.getPassword());
        final StoreOwner updatedStoreOwner = storeOwnerRepository.save(storeOwner);
        return ResponseEntity.ok(updatedStoreOwner);
    }


    // delete store owner by email
    @Override
    @DeleteMapping("deletestoreowner/{email}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String userEmail)throws ResourceNotFoundException {
        StoreOwner storeOwner = storeOwnerRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("StorOwner not found for this Email :: " + userEmail));
        storeOwnerRepository.delete(storeOwner);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    // sign up as store owner
    @Override
    @PostMapping("signupasstoreowner")
    public boolean signUp(@Valid @RequestBody StoreOwner user)  {
        if(getUserByEmail(user.getEmail()).equals(Optional.empty())){
            addUser(user);
            return true;
        }
        return false;
    }

    // login as store owner
    @Override
    @GetMapping("loginasstoreowner")
    public StoreOwner logIn(@Valid @RequestBody StoreOwner user) {
        if((!getUserByEmail(user.getEmail()).equals(Optional.empty()))
                &&(getUserByEmail(user.getEmail()).get().getPassword().equals(user.getPassword()))) {
            user=getUserByEmail(user.getEmail()).get();
            return user;
        }
        return null;
    }


}
