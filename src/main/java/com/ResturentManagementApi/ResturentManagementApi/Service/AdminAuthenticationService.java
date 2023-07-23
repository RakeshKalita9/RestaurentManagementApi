package com.ResturentManagementApi.ResturentManagementApi.Service;

import com.ResturentManagementApi.ResturentManagementApi.Model.AdminUser;
import com.ResturentManagementApi.ResturentManagementApi.Model.AuthenticationTokenAdmin;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IAdminRepo;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IAuthAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticationService {
    @Autowired
    IAuthAdminRepo authAdminRepo;
    @Autowired
    IAdminRepo adminRepo;

    public boolean authenticate(String adminEmail, String token) {
       AuthenticationTokenAdmin authenticationTokenAdmin = authAdminRepo.findFirstByToken(token);
       AdminUser adminUser = authenticationTokenAdmin.getAdminUser();
       AdminUser adminUser1 = adminRepo.findFirstByAdminEmail(adminEmail);
       return adminUser.equals(adminUser1);
    }

    public void logOut(String adminEmail) {
        AdminUser adminUser = adminRepo.findFirstByAdminEmail(adminEmail);
        AuthenticationTokenAdmin authenticationTokenAdmin = authAdminRepo.findFirstByAdminUser(adminUser);
        authAdminRepo.delete(authenticationTokenAdmin);
    }
}
