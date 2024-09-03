package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.FoodItem;
import com.example._20240903ordersys01.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fooditems")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public String listFoodItems(Model model) {
        model.addAttribute("foodItems", foodItemService.getAllFoodItems());
        return "fooditems/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("foodItem", new FoodItem());
        return "fooditems/add";
    }

    @PostMapping("/add")
    public String addFoodItem(@ModelAttribute FoodItem foodItem) {
        foodItemService.saveFoodItem(foodItem);
        return "redirect:/fooditems";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("foodItem", foodItemService.getFoodItemById(id).orElseThrow());
        return "fooditems/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateFoodItem(@PathVariable String id, @ModelAttribute FoodItem foodItem) {
        foodItem.setItemsId(id);
        foodItemService.saveFoodItem(foodItem);
        return "redirect:/fooditems";
    }

    @GetMapping("/delete/{id}")
    public String deleteFoodItem(@PathVariable String id) {
        foodItemService.deleteFoodItem(id);
        return "redirect:/fooditems";
    }
}