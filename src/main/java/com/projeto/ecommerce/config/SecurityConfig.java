package com.projeto.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// adicionando a config do projeto
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user", "/user/create").permitAll()
                        .requestMatchers("/products", "/products/create", "/products/show/{id}", "/products/show/all",
                                        "/orders", "/orders/create", "/orders/show/", "/orders/show/all").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(
                                "/user/show/{id}", "/user/show/all", "/user/update/{id}", "/user/delete/{id}",
                                 "/products/update/{id}", "/products/delete/{id}",
                                "/orders/{id}", "/orders/delete/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()).httpBasic(withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
