package com.projeto.ecommerce.controllers;

import com.projeto.ecommerce.requests.UserRequestDTO;
import com.projeto.ecommerce.responses.UserResponseDTO;
import com.projeto.ecommerce.services.PhotoService;
import com.projeto.ecommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final PhotoService photoService;

    public UserController(UserService userService, PhotoService photoService){
        this.userService = userService;
        this.photoService = photoService;
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<UserResponseDTO> createUser(@RequestParam String name, @RequestParam String email,@RequestParam String phone,@RequestParam String password   , @RequestParam MultipartFile photo) throws IOException {
        String pathPhoto = photoService.savePhoto(photo);
        UserRequestDTO userReq = new UserRequestDTO(name, email, phone, password, pathPhoto);
        UserResponseDTO userRes = userService.createUser(userReq, pathPhoto);
        return ResponseEntity.ok(userRes);
    }


    @GetMapping("/show/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        UserResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/show/alll")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> userRes = userService.getAllUsers();
        return ResponseEntity.ok(userRes);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <UserResponseDTO> updateUserById(@PathVariable UUID id, @RequestBody UserRequestDTO userReq){
        UserResponseDTO updatedUser = userService.updateUserById(id, userReq);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable UUID id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("usuário deletado com sucesso!");
    }
}
