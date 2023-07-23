package com.ResturentManagementApi.ResturentManagementApi.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInInputUser {
    private String userEmail;
    private String userPassword;
}
