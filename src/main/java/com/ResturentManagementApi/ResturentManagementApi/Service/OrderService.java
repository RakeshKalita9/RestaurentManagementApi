package com.ResturentManagementApi.ResturentManagementApi.Service;

import com.ResturentManagementApi.ResturentManagementApi.Model.FoodItem;
import com.ResturentManagementApi.ResturentManagementApi.Model.Order;
import com.ResturentManagementApi.ResturentManagementApi.Repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    IOrderRepo orderRepo;
    public void saveOder(Order order) {
        orderRepo.save(order);
    }
    public List<FoodItem> foodItemList(List<Order> orderList,Long orderId) {
        for (Order order : orderList) {
            if (order.getId().equals(orderId)) {
               return order.getFoodItemList();
            }
        }
        return null;
    }

    public boolean isPresent(Long orderId) {
        Optional<Order> order= orderRepo.findById(orderId);
       return order.isPresent();
    }
}
