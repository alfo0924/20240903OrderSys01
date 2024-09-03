package com.example._20240903ordersys01.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Restaurant {
    @Id
    private Integer restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhone;
    private String restaurantType;
}