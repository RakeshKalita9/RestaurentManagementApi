package com.ResturentManagementApi.ResturentManagementApi.Model;

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
public class AuthenticationTokenAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime time;

    @OneToOne
    @JoinColumn(name = "fk_AdminId")
    private AdminUser adminUser;

   public AuthenticationTokenAdmin(AdminUser adminUser){
       this.token = UUID.randomUUID().toString();
       this.time = LocalDateTime.now();
       this.adminUser = adminUser;
   }


}
