package com.example._20240903ordersys01.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FoodItem {
    @Id
    private String itemsId;
    private String menuId;
    private String foodName;
    private Double foodPrice;
    private String foodStatus;
}