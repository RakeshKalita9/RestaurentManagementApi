package com.ResturentManagementApi.ResturentManagementApi.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminUserId;
    @NotBlank
    private String adminUsername;
    @NotBlank
    private String adminPassword;

    @Column(unique = true)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@admin\\.com$",message = "Admin Should Use Admin Email")
    private String adminEmail;
}
