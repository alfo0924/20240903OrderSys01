package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.Review;
import com.example._20240903ordersys01.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String listReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("review", new Review());
        return "reviews/add";
    }

    @PostMapping("/add")
    public String addReview(@ModelAttribute Review review) {
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("review", reviewService.getReviewById(id).orElseThrow());
        return "reviews/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateReview(@PathVariable String id, @ModelAttribute Review review) {
        review.setReviewId(id);
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }
}