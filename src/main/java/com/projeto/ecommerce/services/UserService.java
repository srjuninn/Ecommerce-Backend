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

    public UserResponse updateUserById(Long id, UserRequest userReq) {
//      metodo do Jpa repository que retorna um optional(podendo estar vazio ou com objeto)
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("não existe um usuário com esse id"));
//      atualiza o usuario com id passado
        userEntity.setName(userReq.getName());
        userEntity.setEmail(userReq.getEmail());
        userEntity.setPhone(userReq.getPhone());
        userEntity.setPassword(userReq.getPassword());
        userEntity.setRoles(userReq.getRoles());
//      salva como novo usuario
        UserEntity updatedUser = userRepository.save(userEntity);
//      retorna o response
        return new UserResponse(updatedUser.getName(), updatedUser.getEmail(), updatedUser.getPhone());
    }
    public void deleteUserById(Long id){
        userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("não existe um usuário com esse id"));
        userRepository.deleteById(id);
    }
}
