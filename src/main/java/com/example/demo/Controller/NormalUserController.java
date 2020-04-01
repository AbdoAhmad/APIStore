package com.example.demo.Controller;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.NormalUser;
import com.example.demo.Model.StoreOwner;
import com.example.demo.Model.User;
import com.example.demo.Repository.NormalUserRepository;
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
@RequestMapping("/storeapi/normaluser/")
public class NormalUserController implements UserController<NormalUser> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NormalUserRepository normalUserRepository;

    // get all normalusers
    @Override
    @GetMapping("listallnormalusers")
    public List<NormalUser> listAll() {
        return this.normalUserRepository.findAll();
    }

    //get normaluser by email
    @Override
    @GetMapping("getnormaluser/{email}")
    public ResponseEntity<NormalUser> getUserByEmail(@PathVariable(value = "email") String userEmail) throws ResourceNotFoundException {
        NormalUser normalUser = normalUserRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("NormalUser not found for this Email :: " + userEmail));
        return ResponseEntity.ok().body(normalUser);
    }

    // insert normaluser
    @Override
    @PostMapping("addnormaluser")
    public NormalUser addUser(@Valid @RequestBody NormalUser user) {
        userRepository.save(user);
        return normalUserRepository.save(user);
    }

    //update normaluser by email
    @Override
    @PutMapping("updatenormaluser/{email}")
    public ResponseEntity<NormalUser> updateUser(@PathVariable(value = "email") String userEmail,
                                     @Valid @RequestBody NormalUser userDetails) throws ResourceNotFoundException {
        NormalUser normalUser = normalUserRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("NormalUser not found for this Email :: " + userEmail));
        normalUser.setName(userDetails.getName());
        normalUser.setPassword(userDetails.getPassword());
        final NormalUser updatedNormalUser = normalUserRepository.save(normalUser);
        return ResponseEntity.ok(updatedNormalUser);
    }


    // delete normaluser by email
    @Override
    @DeleteMapping("deletenormaluser/{email}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String userEmail) throws ResourceNotFoundException {
        NormalUser normalUser = normalUserRepository.findById(userEmail).orElseThrow(() -> new ResourceNotFoundException("NormalUser not found for this Email :: " + userEmail));
        userRepository.delete(normalUser);
        normalUserRepository.delete(normalUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public NormalUser signUp(@Valid NormalUser user) throws ResourceNotFoundException {
        return null;
    }


    @Override
    public NormalUser logIn(@Valid NormalUser user) throws ResourceNotFoundException {
        return null;
    }

}
