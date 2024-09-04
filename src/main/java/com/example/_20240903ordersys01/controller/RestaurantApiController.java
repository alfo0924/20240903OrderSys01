package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.Restaurant;
import com.example._20240903ordersys01.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantApiController {

    @Autowired
    private RestaurantService restaurantService;

    // 獲取所有餐廳
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    // 根據ID獲取餐廳
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Integer id) {
        return restaurantService.getRestaurantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 創建新餐廳
    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.saveRestaurant(restaurant);
    }

    // 更新餐廳信息
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Integer id, @RequestBody Restaurant restaurantDetails) {
        return restaurantService.getRestaurantById(id)
                .map(restaurant -> {
                    restaurant.setRestaurantName(restaurantDetails.getRestaurantName());
                    restaurant.setRestaurantAddress(restaurantDetails.getRestaurantAddress());
                    restaurant.setRestaurantPhone(restaurantDetails.getRestaurantPhone());
                    restaurant.setRestaurantType(restaurantDetails.getRestaurantType());
                    return ResponseEntity.ok(restaurantService.saveRestaurant(restaurant));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 刪除餐廳
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Integer id) {
        return restaurantService.getRestaurantById(id)
                .map(restaurant -> {
                    restaurantService.deleteRestaurant(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}