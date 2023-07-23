package com.ResturentManagementApi.ResturentManagementApi.Service;

import com.ResturentManagementApi.ResturentManagementApi.Model.AdminUser;
import com.ResturentManagementApi.ResturentManagementApi.Model.AuthenticationTokenAdmin;
import com.ResturentManagementApi.ResturentManagementApi.Model.Dto.SignInInput;
import com.ResturentManagementApi.ResturentManagementApi.Model.Dto.SignUpOutput;
import com.ResturentManagementApi.ResturentManagementApi.Model.FoodItem;
import com.ResturentManagementApi.ResturentManagementApi.Model.Hashing.Encrypt;
import com.ResturentManagementApi.ResturentManagementApi.Model.Utilities.AdminValidator;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IAdminRepo;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IAuthAdminRepo;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminService{
    @Autowired
    AdminValidator validator;
    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    IAuthAdminRepo authAdminRepo;
    @Autowired
    IFoodItemRepo foodItemRepo;
    public SignUpOutput signUp(AdminUser adminUser, String systemPassword) throws NoSuchAlgorithmException {
       String sysPass = validator.get();
       String adminEmail = adminUser.getAdminEmail();
       AdminUser existingAdmin = adminRepo.findFirstByAdminEmail(adminEmail);
       if(existingAdmin!=null){
           return new SignUpOutput("Email Already Exist", false);
       }

       if(adminEmail==null){
           return new SignUpOutput("Some Error Occurred",false);
       }
       if(systemPassword==null){
           return new SignUpOutput("must provide System Password",false);
       }
       if(!systemPassword.equals(sysPass)){
           return new SignUpOutput("wrong System PassWord",false);
       }
       String hexPassword = Encrypt.encryptPassword(adminUser.getAdminPassword());
       adminUser.setAdminPassword(hexPassword);
       adminRepo.save(adminUser);
       return new SignUpOutput("SignUp SuccessFull",true);
    }

    public String signIn(SignInInput signInInput) throws NoSuchAlgorithmException {
        String inputEmail = signInInput.getAdminEmail();
        String inputPassword = signInInput.getAdminPassword();
        if(inputEmail==null){
            return "Invalid Sign In credentials";
        }
        AdminUser adminUser = adminRepo.findFirstByAdminEmail(inputEmail);

        if(adminUser == null ){
            return "Email not Exist ....Sign Up needed";
        }
        String hexValue = Encrypt.encryptPassword(inputPassword);
        if(!adminUser.getAdminPassword().equals(hexValue)){
            return "Invalid Sign In credentials";
        }
        AuthenticationTokenAdmin authenticationTokenAdmin = new AuthenticationTokenAdmin(adminUser);
        authAdminRepo.save(authenticationTokenAdmin);
        return "Sign In SuccessFull";
    }

    public void addFoodItem(FoodItem foodItem) {
     foodItemRepo.save(foodItem);
    }


}
