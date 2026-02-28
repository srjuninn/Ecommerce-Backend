package com.projeto.ecommerce.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String email;
    private String phone;
}
