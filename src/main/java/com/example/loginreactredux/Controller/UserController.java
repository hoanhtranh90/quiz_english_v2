package com.example.loginreactredux.Controller;

import com.example.loginreactredux.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id){
        HashMap<String ,Object> data = new HashMap<>();
        data.put("user",userRepository.findUById(id));
        return ResponseEntity.ok(data);
    }
}
