package com.example._20240903ordersys01.repository;

import com.example._20240903ordersys01.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {
}