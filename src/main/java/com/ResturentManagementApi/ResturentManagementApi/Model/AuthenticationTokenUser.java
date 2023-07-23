package com.ResturentManagementApi.ResturentManagementApi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationTokenUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String token;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationTime;
    @OneToOne
    User user;
   public AuthenticationTokenUser(User user){
       this.token = UUID.randomUUID().toString();
       this.creationTime = LocalDateTime.now();
       this.user =user ;
   }
}
