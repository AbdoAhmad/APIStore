package com.example.demo.Controller;

import com.example.demo.Exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UserController<T> {

    public boolean signUp(@Valid @RequestBody T user);
    public T logIn(@Valid @RequestBody T user);

}
