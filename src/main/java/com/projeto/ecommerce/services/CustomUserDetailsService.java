package com.projeto.ecommerce.services;

import com.projeto.ecommerce.entities.UserEntity;
import com.projeto.ecommerce.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("usuário não encontrado com email: " + email));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().name().replace("ROLE_", ""))
                .build();
    }
}
