package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.OrderItem;
import com.example._20240903ordersys01.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderitems")
public class OrderItemApiController {

    @Autowired
    private OrderItemService orderItemService;

    // 獲取所有訂單項目
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    // 根據ID獲取訂單項目
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable String id) {
        return orderItemService.getOrderItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 創建新訂單項目
    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    // 更新訂單項目信息
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable String id, @RequestBody OrderItem orderItemDetails) {
        return orderItemService.getOrderItemById(id)
                .map(orderItem -> {
                    orderItem.setOrderId(orderItemDetails.getOrderId());
                    orderItem.setItemsId(orderItemDetails.getItemsId());
                    orderItem.setCounts(orderItemDetails.getCounts());
                    orderItem.setAmount(orderItemDetails.getAmount());
                    return ResponseEntity.ok(orderItemService.saveOrderItem(orderItem));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 刪除訂單項目
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable String id) {
        return orderItemService.getOrderItemById(id)
                .map(orderItem -> {
                    orderItemService.deleteOrderItem(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}