package com.ResturentManagementApi.ResturentManagementApi.Service;

import com.ResturentManagementApi.ResturentManagementApi.Model.FoodItem;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    IFoodItemRepo foodItemRepo;
    public void deleteFood(Long foodId) {
        Optional<FoodItem> foodItem = foodItemRepo.findById(foodId);
        if(foodItem.orElse(null)!=null) foodItemRepo.delete(foodItem.orElse(null));
    }

    public List<FoodItem> returnAllFood() {
        return foodItemRepo.findAll();
    }
}
