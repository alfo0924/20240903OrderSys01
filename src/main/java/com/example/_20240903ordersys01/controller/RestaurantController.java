package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.Restaurant;
import com.example._20240903ordersys01.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public String listRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        return "restaurants/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "restaurants/add";
    }

    @PostMapping("/add")
    public String addRestaurant(@ModelAttribute Restaurant restaurant) {
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/restaurants";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("restaurant", restaurantService.getRestaurantById(id).orElseThrow());
        return "restaurants/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateRestaurant(@PathVariable Integer id, @ModelAttribute Restaurant restaurant) {
        restaurant.setRestaurantId(id);
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/restaurants";
    }

    @GetMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable Integer id) {
        restaurantService.deleteRestaurant(id);
        return "redirect:/restaurants";
    }
}