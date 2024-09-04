package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.FoodItem;
import com.example._20240903ordersys01.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fooditems")
public class FoodItemApiController {

    @Autowired
    private FoodItemService foodItemService;

    // 獲取所有餐點
    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    // 根據ID獲取餐點
    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getFoodItemById(@PathVariable String id) {
        return foodItemService.getFoodItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 創建新餐點
    @PostMapping
    public FoodItem createFoodItem(@RequestBody FoodItem foodItem) {
        return foodItemService.saveFoodItem(foodItem);
    }

    // 更新餐點信息
    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> updateFoodItem(@PathVariable String id, @RequestBody FoodItem foodItemDetails) {
        return foodItemService.getFoodItemById(id)
                .map(foodItem -> {
                    foodItem.setMenuId(foodItemDetails.getMenuId());
                    foodItem.setFoodName(foodItemDetails.getFoodName());
                    foodItem.setFoodPrice(foodItemDetails.getFoodPrice());
                    foodItem.setFoodStatus(foodItemDetails.getFoodStatus());
                    return ResponseEntity.ok(foodItemService.saveFoodItem(foodItem));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 刪除餐點
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable String id) {
        return foodItemService.getFoodItemById(id)
                .map(foodItem -> {
                    foodItemService.deleteFoodItem(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}