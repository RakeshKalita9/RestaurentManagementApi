package com.ResturentManagementApi.ResturentManagementApi.Controller;

import com.ResturentManagementApi.ResturentManagementApi.Model.FoodItem;
import com.ResturentManagementApi.ResturentManagementApi.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewerController {
    @Autowired
    FoodService foodService;
    @GetMapping("foods")
    public List<FoodItem> getFoods(){
        return foodService.returnAllFood();
    }
}
