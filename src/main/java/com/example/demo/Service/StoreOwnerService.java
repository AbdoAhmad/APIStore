package com.example.demo.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.StoreOwner;
import com.example.demo.Repository.StoreOwnerRepository;
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
public class StoreOwnerService implements UserService<StoreOwner> {

    @Autowired
    private StoreOwnerRepository storeOwnerRepository;

    // get all store owners
    @Override
    public List<StoreOwner> listAll() {
        return this.storeOwnerRepository.findAll();
    }

    //get store owner by email
    @Override
    public Optional<StoreOwner> getUserByEmail(@PathVariable(value = "email") String userEmail) {
        Optional<StoreOwner> storeOwner = storeOwnerRepository.findById(userEmail);
        return storeOwner;
    }

    // insert store owner
    @Override
    public StoreOwner addUser(@Valid @RequestBody StoreOwner user) {
        return storeOwnerRepository.save(user);
    }

    //update store owner by email
    @Override
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
    public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String userEmail)throws ResourceNotFoundException {
        StoreOwner storeOwner = storeOwnerRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("StorOwner not found for this Email :: " + userEmail));
        storeOwnerRepository.delete(storeOwner);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }




}
