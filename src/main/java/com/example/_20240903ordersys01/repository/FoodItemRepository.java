package com.example._20240903ordersys01.repository;

import com.example._20240903ordersys01.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, String> {
}