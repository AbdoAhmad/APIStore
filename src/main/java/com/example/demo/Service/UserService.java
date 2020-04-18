package com.example.demo.Security.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // list all users
    public List<?> listAll() {
        return this.userRepository.findAll();
    }

    //get user by email
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        User user = userRepository.findById(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with userEmail: " + userEmail));

        return UserDetailsAccess.build(user);
    }

    //update user by email
    public ResponseEntity<User> updateUser(@PathVariable(value = "email") String userEmail,
                                                        @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found for this Email :: " + userEmail));
        user.setName(userDetails.getName());
        user.setPassword(userDetails.getPassword());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }


    // delete user by email
    public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String userEmail) throws ResourceNotFoundException {
        User user = userRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("AdministratorUser not found for this Email :: " + userEmail));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
