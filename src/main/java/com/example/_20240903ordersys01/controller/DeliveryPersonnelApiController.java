package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.Servcie.DeliveryPersonnelService;
import com.example._20240903ordersys01.model.DeliveryPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-personnel")
public class DeliveryPersonnelApiController {

    @Autowired
    private DeliveryPersonnelService deliveryPersonnelService;

    // 獲取所有外送員
    @GetMapping
    public List<DeliveryPersonnel> getAllDeliveryPersonnel() {
        return deliveryPersonnelService.getAllDeliveryPersonnel();
    }

    // 根據ID獲取外送員
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryPersonnel> getDeliveryPersonnelById(@PathVariable String id) {
        return deliveryPersonnelService.getDeliveryPersonnelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 創建新外送員
    @PostMapping
    public DeliveryPersonnel createDeliveryPersonnel(@RequestBody DeliveryPersonnel deliveryPersonnel) {
        return deliveryPersonnelService.saveDeliveryPersonnel(deliveryPersonnel);
    }

    // 更新外送員信息
    @PutMapping("/{id}")
    public ResponseEntity<DeliveryPersonnel> updateDeliveryPersonnel(@PathVariable String id, @RequestBody DeliveryPersonnel deliveryPersonnelDetails) {
        return deliveryPersonnelService.getDeliveryPersonnelById(id)
                .map(deliveryPersonnel -> {
                    deliveryPersonnel.setDeliveryName(deliveryPersonnelDetails.getDeliveryName());
                    deliveryPersonnel.setDeliveryPhone(deliveryPersonnelDetails.getDeliveryPhone());
                    deliveryPersonnel.setDeliveryVehicle(deliveryPersonnelDetails.getDeliveryVehicle());
                    deliveryPersonnel.setDeliveryStatus(deliveryPersonnelDetails.getDeliveryStatus());
                    return ResponseEntity.ok(deliveryPersonnelService.saveDeliveryPersonnel(deliveryPersonnel));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 刪除外送員
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryPersonnel(@PathVariable String id) {
        return deliveryPersonnelService.getDeliveryPersonnelById(id)
                .map(deliveryPersonnel -> {
                    deliveryPersonnelService.deleteDeliveryPersonnel(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}