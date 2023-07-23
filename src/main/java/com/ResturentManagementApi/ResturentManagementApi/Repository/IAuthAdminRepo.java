package com.ResturentManagementApi.ResturentManagementApi.Repository;

import com.ResturentManagementApi.ResturentManagementApi.Model.AdminUser;
import com.ResturentManagementApi.ResturentManagementApi.Model.AuthenticationTokenAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthAdminRepo extends JpaRepository<AuthenticationTokenAdmin,Long> {
    AuthenticationTokenAdmin findFirstByToken(String token);

    AuthenticationTokenAdmin findFirstByAdminUser(AdminUser adminUser);
}
