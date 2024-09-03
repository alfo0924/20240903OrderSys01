package com.example._20240903ordersys01.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;
    private Integer customerId;
    private Integer restaurantId;
    private String deliveryId;
    private LocalDate orderDate;
    private Double orderPrice;
    private String orderStatus;
}