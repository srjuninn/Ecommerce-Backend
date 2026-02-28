package com.projeto.ecommerce.services;

import com.projeto.ecommerce.entities.UserEntity;
import com.projeto.ecommerce.repositories.UserRepository;
import com.projeto.ecommerce.requests.UserRequest;
import com.projeto.ecommerce.responses.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userReq) {
        if (userReq == null) {
            throw new IllegalArgumentException("os dados inseridos são inválidos");
        }
        if (userRepository.findByEmail(userReq.getEmail()).isPresent()) {
            throw new DuplicateKeyException("já existe um usuário com esse email cadastrado");
        }
        UserEntity newUser = new UserEntity(userReq.getName(), userReq.getEmail(), userReq.getPhone(), userReq.getPassword(), userReq.getRoles());
        userRepository.save(newUser);
        return new UserResponse(newUser.getName(), newUser.getEmail(), newUser.getPhone());
    }

    public UserResponse getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));

        return new UserResponse(user.getName(), user.getEmail(), user.getPhone());
    }



}
