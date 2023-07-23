package com.ResturentManagementApi.ResturentManagementApi.Controller;

import com.ResturentManagementApi.ResturentManagementApi.Model.Dto.SignInInputUser;
import com.ResturentManagementApi.ResturentManagementApi.Model.Dto.SignUpOutput;
import com.ResturentManagementApi.ResturentManagementApi.Model.Order;
import com.ResturentManagementApi.ResturentManagementApi.Model.User;
import com.ResturentManagementApi.ResturentManagementApi.Service.OrderService;
import com.ResturentManagementApi.ResturentManagementApi.Service.UserAuthenticateService;
import com.ResturentManagementApi.ResturentManagementApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
 public class UserController {
     @Autowired
     UserService userService;
     @Autowired
    UserAuthenticateService userAuthenticateService;
     @Autowired
     OrderService orderService;
     @PostMapping("signUp/user")
     public SignUpOutput signUp(@RequestBody User user) throws NoSuchAlgorithmException {
         return userService.addUser(user);
     }
     @PostMapping("login/user")
     public String signIn(@RequestBody SignInInputUser signInInputUser){
         return userService.signInUser(signInInputUser);
     }
     @PostMapping("order")
     public String placeOrder(@RequestBody Order order, @RequestParam String  userEmail, @RequestParam String token){
         if(userAuthenticateService.authenticate(userEmail,token)){
             orderService.saveOder(order);
             return "Order placed";
         }
         return "Authentication failed";
     }
     @DeleteMapping("user/logout")
    public String logOut(@RequestParam String  userEmail, @RequestParam String token) {
         if (userAuthenticateService.authenticate(userEmail, token)) {
             userAuthenticateService.deleteUser(userEmail);
             return "LoggedOut...........";
         }
         return "Authentication failed";
     }
     @GetMapping("orderTotal/user")
     public String getBill(@RequestParam String  userEmail, @RequestParam String token,@RequestParam Long orderId){
         if(userAuthenticateService.authenticate(userEmail, token)){
            return userService.getBillForUser(orderId,userEmail);
         }
       return "authentication failed";
     }


}
