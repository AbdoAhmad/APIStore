package com.example.demo.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService<T> {

    public List<T> listAll();
    public Optional<T> getUserByEmail(@PathVariable(value = "email") String userEmail);
    public T addUser(@Valid @RequestBody T user);
    public ResponseEntity<T> updateUser(@PathVariable(value = "email") String userEmail, @Valid @RequestBody T userDetails) throws ResourceNotFoundException;
    public Map<String, Boolean> deleteUser(@PathVariable(value = "email") String userEmail) throws ResourceNotFoundException;
}
