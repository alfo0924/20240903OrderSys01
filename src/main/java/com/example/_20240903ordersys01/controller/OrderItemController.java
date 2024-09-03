package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.OrderItem;
import com.example._20240903ordersys01.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderitems")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public String listOrderItems(Model model) {
        model.addAttribute("orderItems", orderItemService.getAllOrderItems());
        return "orderitems/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("orderItem", new OrderItem());
        return "orderitems/add";
    }

    @PostMapping("/add")
    public String addOrderItem(@ModelAttribute OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
        return "redirect:/orderitems";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("orderItem", orderItemService.getOrderItemById(id).orElseThrow());
        return "orderitems/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateOrderItem(@PathVariable String id, @ModelAttribute OrderItem orderItem) {
        orderItem.setOrderItemsId(id);
        orderItemService.saveOrderItem(orderItem);
        return "redirect:/orderitems";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrderItem(@PathVariable String id) {
        orderItemService.deleteOrderItem(id);
        return "redirect:/orderitems";
    }
}