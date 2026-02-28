package com.projeto.ecommerce.controllers;

import com.projeto.ecommerce.requests.UserRequest;
import com.projeto.ecommerce.responses.UserResponse;
import com.projeto.ecommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("create")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userReq){
        UserResponse userRes = userService.createUser(userReq);
        return ResponseEntity.ok(userRes);
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }


}
