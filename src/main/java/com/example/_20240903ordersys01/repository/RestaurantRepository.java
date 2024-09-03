package com.example._20240903ordersys01.repository;

import com.example._20240903ordersys01.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}