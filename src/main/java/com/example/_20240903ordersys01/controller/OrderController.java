package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.Order;
import com.example._20240903ordersys01.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order());
        return "orders/add";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id).orElseThrow());
        return "orders/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateOrder(@PathVariable String id, @ModelAttribute Order order) {
        order.setOrderId(id);
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}