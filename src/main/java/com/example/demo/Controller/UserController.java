package com.example.demo.Controller;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.AdministratorUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public interface UserController<T> {

    public List<T> listAll();
    public ResponseEntity<T> getUserByEmail(@PathVariable(value = "email") String userEmail) throws ResourceNotFoundException;
    public T addUser(@Valid @RequestBody T user);
    public ResponseEntity<T> updateUser(@PathVariable(value = "email") String userEmail, @Valid @RequestBody T userDetails) throws ResourceNotFoundException;
    public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String userEmail) throws ResourceNotFoundException;
    public T signUp(@Valid @RequestBody T user) throws ResourceNotFoundException;
    public T logIn(@Valid @RequestBody T user) throws ResourceNotFoundException;

}
