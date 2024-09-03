package com.example._20240903ordersys01.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
    @Id
    private String orderItemsId;
    private String orderId;
    private String itemsId;
    private Integer counts;
    private Double amount;
}