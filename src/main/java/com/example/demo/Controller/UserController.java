package com.example.demo.Controller;

import com.example.demo.Model.StoreOwner;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/storeapi/user/")
public interface UserController<T> {

    public List<T> listAll();
    public ResponseEntity<T> getUserByEmail(@PathVariable(value = "email") String userEmail);
    public T addUser(@Valid @RequestBody T user);
    public ResponseEntity<T> updateUser
            (@PathVariable(value = "email") String userEmail, @Valid @RequestBody T userDetails);
    public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String userEmail);
}
