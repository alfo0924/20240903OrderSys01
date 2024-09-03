package com.example._20240903ordersys01.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DeliveryPersonnel {
    @Id
    private String deliveryId;
    private String deliveryName;
    private String deliveryPhone;
    private String deliveryVehicle;
    private String deliveryStatus;
}