package com.example._20240903ordersys01.repository;

import com.example._20240903ordersys01.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}