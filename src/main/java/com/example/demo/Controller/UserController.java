package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get all user
    @GetMapping("listall")
    public List<User>listAll(){
        return this.userRepository.findAll();
    }

    //get user by email
    @GetMapping("getuser/{email}")
    public ResponseEntity<User> getEmployeeById(@PathVariable(value = "email") String userEmail) {
        User user  = userRepository.findById(userEmail).orElseThrow();
        return ResponseEntity.ok().body(user);
    }

    // insert user
    @PostMapping("adduser")
    public User addUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    //update user by email
    @PutMapping("updateuser/{email}")
    public ResponseEntity<User> updateEmployee(@PathVariable(value = "email") String userEmail,
                                                   @Valid @RequestBody User userDetails) {
        User user = userRepository.findById(userEmail).orElseThrow();
        user.setName(userDetails.getName());
        user.setPassword(userDetails.getPassword());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // delete user by email
    @DeleteMapping("deleteuser/{email}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "email") String userEmail) {
        User user = userRepository.findById(userEmail).orElseThrow();
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
