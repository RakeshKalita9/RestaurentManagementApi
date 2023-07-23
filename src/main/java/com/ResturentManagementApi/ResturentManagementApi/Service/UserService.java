package com.ResturentManagementApi.ResturentManagementApi.Service;

import com.ResturentManagementApi.ResturentManagementApi.Model.AuthenticationTokenUser;
import com.ResturentManagementApi.ResturentManagementApi.Model.Dto.SignInInputUser;
import com.ResturentManagementApi.ResturentManagementApi.Model.Dto.SignUpOutput;
import com.ResturentManagementApi.ResturentManagementApi.Model.FoodItem;
import com.ResturentManagementApi.ResturentManagementApi.Model.Hashing.Encrypt;
import com.ResturentManagementApi.ResturentManagementApi.Model.Order;
import com.ResturentManagementApi.ResturentManagementApi.Model.User;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IAuthUserRepo;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IFoodItemRepo;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IOrderRepo;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    IAuthUserRepo authUserRepo;

    @Autowired
    IOrderRepo orderRepo;

    @Autowired
    IFoodItemRepo foodItemRepo;

    @Autowired
    OrderService orderService;
    public SignUpOutput addUser(User user) throws NoSuchAlgorithmException {
        String userEmail = user.getUserEmail();
        User user1 = userRepo.findFirstByUserEmail(userEmail);
        if(user1!=null){
            return new SignUpOutput("Email Already registered",false);
        }
        String hexPassWord = Encrypt.encryptPassword(user.getPassword());
        user.setPassword(hexPassWord);
        userRepo.save(user);
        return new SignUpOutput("sign Up SuccessFull",true);
    }

    public String signInUser(SignInInputUser signInInputUser) {
        String email = signInInputUser.getUserEmail();
        String password = signInInputUser.getUserPassword();
        if(email==null) return "Invalid Credential";
        User existingUser = userRepo.findFirstByUserEmail(email);
        if(existingUser==null) return "Email not exist...sign Up first";
        AuthenticationTokenUser authenticationTokenUser = new AuthenticationTokenUser(existingUser);
        authUserRepo.save(authenticationTokenUser);
        return "Signed In";
    }


    public String getBillForUser(Long orderId, String userEmail) {
        User user = userRepo.findFirstByUserEmail(userEmail);
        List<Order> orderList = orderRepo.findByUser(user);
        List<FoodItem> foodsRelatedToOrder = null;
        Double Total = (double) 0;
        if(!orderService.isPresent(orderId)) return "No order is Linked To the user";


        foodsRelatedToOrder = orderService.foodItemList(orderList,orderId);



            if (foodsRelatedToOrder != null) {
                for (FoodItem foodItem : foodsRelatedToOrder) {
                    Total += foodItem.getFoodPrice();
                }
                return "your bill Amount is " + Total + " sir";
            }

                return "You Have No Selected Any Food Yet Sir......Plz Select some food";
        }
    }


