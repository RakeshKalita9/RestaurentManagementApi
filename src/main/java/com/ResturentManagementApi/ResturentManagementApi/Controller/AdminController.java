package com.ResturentManagementApi.ResturentManagementApi.Controller;

import com.ResturentManagementApi.ResturentManagementApi.Model.AdminUser;
import com.ResturentManagementApi.ResturentManagementApi.Model.Dto.SignInInput;
import com.ResturentManagementApi.ResturentManagementApi.Model.Dto.SignUpOutput;
import com.ResturentManagementApi.ResturentManagementApi.Model.FoodItem;
import com.ResturentManagementApi.ResturentManagementApi.Service.AdminAuthenticationService;
import com.ResturentManagementApi.ResturentManagementApi.Service.AdminService;
import com.ResturentManagementApi.ResturentManagementApi.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
public class AdminController {
 @Autowired
 AdminService adminService;
 @Autowired
 AdminAuthenticationService adminAuthenticationService;
 @Autowired
 FoodService foodService;
  @PostMapping("admin/signUp")
  public SignUpOutput signUp(@RequestParam String systemPassword, @RequestBody AdminUser adminUser) throws NoSuchAlgorithmException {
   return adminService.signUp(adminUser,systemPassword);
  }
  @PostMapping("admin/login")
  public String signIn(@RequestBody SignInInput signInInput) throws NoSuchAlgorithmException {
   return adminService.signIn(signInInput);
  }
  @PostMapping("foodItem")
  public String addFoodItem(@RequestBody FoodItem foodItem,@RequestParam String adminEmail,@RequestParam String token){
   if(adminAuthenticationService.authenticate(adminEmail,token)){
    adminService.addFoodItem(foodItem);
    return "Food Added";
   }
   return "Admin Authentication Failed";
  }
  @DeleteMapping("food/{foodId}")
  public String deleteFood(@PathVariable Long foodId,@RequestParam String adminEmail,@RequestParam String token){
   if(adminAuthenticationService.authenticate(adminEmail,token)){
    foodService.deleteFood(foodId);
    return "food Deleted";
   }
   return "Admin Authentication failed";
  }
  @DeleteMapping("Admin/Logout")
 public String logOut(@RequestParam String adminEmail,@RequestParam String token){
   if(adminAuthenticationService.authenticate(adminEmail,token)){
    adminAuthenticationService.logOut(adminEmail);
    return "Sign Out....";
   }
   return "Authentication Failed";
  }


}
