package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.Order;
import com.example._20240903ordersys01.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    // 獲取所有訂單
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // 根據ID獲取訂單
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 創建新訂單
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    // 更新訂單信息
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order orderDetails) {
        return orderService.getOrderById(id)
                .map(order -> {
                    order.setCustomerId(orderDetails.getCustomerId());
                    order.setRestaurantId(orderDetails.getRestaurantId());
                    order.setDeliveryId(orderDetails.getDeliveryId());
                    order.setOrderDate(orderDetails.getOrderDate());
                    order.setOrderPrice(orderDetails.getOrderPrice());
                    order.setOrderStatus(orderDetails.getOrderStatus());
                    return ResponseEntity.ok(orderService.saveOrder(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 刪除訂單
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        return orderService.getOrderById(id)
                .map(order -> {
                    orderService.deleteOrder(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}