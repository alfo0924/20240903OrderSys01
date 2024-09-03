package com.example._20240903ordersys01.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
//@Table(name = "customers")
public class Customer {
    @Id
    private Integer customerId;
    private String customerName;
    private String customerPhoneNumber;
    private String customerEmail;
    private String customerAddress;
}