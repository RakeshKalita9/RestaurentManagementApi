package com.ResturentManagementApi.ResturentManagementApi.Repository;

import com.ResturentManagementApi.ResturentManagementApi.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodItemRepo extends JpaRepository<FoodItem,Long> {
}
