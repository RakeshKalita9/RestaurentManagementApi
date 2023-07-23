package com.ResturentManagementApi.ResturentManagementApi.Repository;

import com.ResturentManagementApi.ResturentManagementApi.Model.AuthenticationTokenUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthUserRepo extends JpaRepository<AuthenticationTokenUser,Long> {
    AuthenticationTokenUser findFirstByToken(String token);
}
