package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.Restaurant;
import com.example._20240903ordersys01.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Integer id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Integer id, @RequestBody Restaurant restaurantDetails) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant != null) {
            restaurant.setRestaurantName(restaurantDetails.getRestaurantName());
            restaurant.setRestaurantAddress(restaurantDetails.getRestaurantAddress());
            restaurant.setRestaurantPhone(restaurantDetails.getRestaurantPhone());
            restaurant.setRestaurantType(restaurantDetails.getRestaurantType());
            return restaurantRepository.save(restaurant);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Integer id) {
        restaurantRepository.deleteById(id);
    }
}