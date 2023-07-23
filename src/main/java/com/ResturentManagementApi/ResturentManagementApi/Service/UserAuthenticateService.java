package com.ResturentManagementApi.ResturentManagementApi.Service;

import com.ResturentManagementApi.ResturentManagementApi.Model.AuthenticationTokenUser;
import com.ResturentManagementApi.ResturentManagementApi.Model.User;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IAuthUserRepo;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticateService {
    @Autowired
    IAuthUserRepo authUserRepo;
    @Autowired
    IUserRepo userRepo;


    public boolean authenticate(String userEmail, String token) {
        AuthenticationTokenUser authenticationTokenUser = authUserRepo.findFirstByToken(token);
        User user = authenticationTokenUser.getUser();
        return user.getUserEmail().equals(userEmail);
    }

    public void deleteUser(String userEmail) {
        userRepo.delete(userRepo.findFirstByUserEmail(userEmail));
    }


}
