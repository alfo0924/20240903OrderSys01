package com.example._20240903ordersys01.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Review {
    @Id
    private String reviewId;
    private String orderId;
    private Integer customerId;
    private Integer restaurantId;
    private String deliveryId;
    private String comment;
}