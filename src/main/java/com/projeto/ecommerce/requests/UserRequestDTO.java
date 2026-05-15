package com.projeto.ecommerce.requests;

import com.projeto.ecommerce.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserRequestDTO {
    //    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @Size(max = 11)
    private String phone;
    @NotBlank
    private String password;
    private RoleEnum roles;
    private String photo;

//    public UserRequestDTO(UUID id, String name, String email, String phone, String password, RoleEnum roles, String photo) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.phone = phone;
//        this.password = password;
//        this.roles = roles;
//        this.photo = photo;
//    }

    public UserRequestDTO(String name, String email, String phone, String password, String photo) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.photo = photo;
    }
}
