package com.example.demo.Controller;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.NormalUser;
import com.example.demo.Repository.NormalUserRepository;
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
    private NormalUserRepository normalUserRepository;
    // get all normal users
    @Override
    @GetMapping("listallnormalusers")
    public List<NormalUser> listAll() {
        return this.normalUserRepository.findAll();
    }

    //get normal user by email
    @Override
    @GetMapping("getnormaluser/{email}")
    public Optional<NormalUser> getUserByEmail(@PathVariable(value = "email") String userEmail)  {
        Optional<NormalUser> normalUser = normalUserRepository.findById(userEmail);
        return normalUser;
    }

    // insert normal user
    @Override
    @PostMapping("addnormaluser")
    public NormalUser addUser(@Valid @RequestBody NormalUser user) {
        return normalUserRepository.save(user);
    }

    //update normal user by email
    @Override
    @PutMapping("updatenormaluser/{email}")
    public ResponseEntity<NormalUser> updateUser(@PathVariable(value = "email") String userEmail, @Valid @RequestBody NormalUser userDetails) throws ResourceNotFoundException {
        NormalUser normalUser = normalUserRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("NormalUser not found for this Email :: " + userEmail));
        normalUser.setName(userDetails.getName());
        normalUser.setPassword(userDetails.getPassword());
        final NormalUser updatedNormalUser = normalUserRepository.save(normalUser);
        return ResponseEntity.ok(updatedNormalUser);
    }


    // delete normal user by email
    @Override
    @DeleteMapping("deletenormaluser/{email}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String userEmail) throws ResourceNotFoundException {
        NormalUser normalUser = normalUserRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("NormalUser not found for this Email :: " + userEmail));
        normalUserRepository.delete(normalUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    // sign up as normal user
    @PostMapping("signupasnormaluser")
    public boolean  signUp(@Valid @RequestBody NormalUser user){
        if(getUserByEmail(user.getEmail()).equals(Optional.empty())){
            addUser(user);
            return true;
        }
        return false;
    }

    // login as normal user
    @Override
    @GetMapping("loginasnormaluser")
    public NormalUser logIn(@Valid @RequestBody NormalUser user)  {
        if(!getUserByEmail(user.getEmail()).equals(Optional.empty())){
            user=getUserByEmail(user.getEmail()).get();
            return user;
        }
    return null;
    }

}
