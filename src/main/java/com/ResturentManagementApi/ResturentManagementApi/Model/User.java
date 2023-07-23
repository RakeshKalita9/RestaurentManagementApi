package com.ResturentManagementApi.ResturentManagementApi.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @Column(unique = true)
    @Pattern(regexp = "^(?!.*@.*admin\\.com$).+@.+",message = "Normal User Cant Use Admin Email")
    private String userEmail;


}
