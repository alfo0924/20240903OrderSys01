package com.example._20240903ordersys01.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Menu {
    @Id
    private String menuId;
    private Integer restaurantId;
    private String menuName;
    private String menuDescribe;
    private String menuStatus;
}