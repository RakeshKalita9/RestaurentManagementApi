package com.ResturentManagementApi.ResturentManagementApi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")

public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "join_table_order_Food")
    private List<FoodItem> foodItemList;

    @ManyToOne
    @JoinColumn(name = "fk_User_Id")
    private User user;

    private Status status;

}
