package com.ResturentManagementApi.ResturentManagementApi.Repository;

import com.ResturentManagementApi.ResturentManagementApi.Model.Order;
import com.ResturentManagementApi.ResturentManagementApi.Model.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderRepo extends JpaRepository<Order,Long> {
    List<Order> findByUser(User user);

    List<Order> findFirstByUser(User user);


}
