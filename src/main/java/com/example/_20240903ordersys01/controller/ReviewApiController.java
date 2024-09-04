package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.Review;
import com.example._20240903ordersys01.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewApiController {

    @Autowired
    private ReviewService reviewService;

    // 獲取所有評論
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // 根據ID獲取評論
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 創建新評論
    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }

    // 更新評論信息
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable String id, @RequestBody Review reviewDetails) {
        return reviewService.getReviewById(id)
                .map(review -> {
                    review.setOrderId(reviewDetails.getOrderId());
                    review.setCustomerId(reviewDetails.getCustomerId());
                    review.setRestaurantId(reviewDetails.getRestaurantId());
                    review.setDeliveryId(reviewDetails.getDeliveryId());
                    review.setComment(reviewDetails.getComment());
                    return ResponseEntity.ok(reviewService.saveReview(review));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 刪除評論
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        return reviewService.getReviewById(id)
                .map(review -> {
                    reviewService.deleteReview(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}