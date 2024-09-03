package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.DeliveryPersonnel;
import com.example._20240903ordersys01.repository.DeliveryPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-personnel")
public class DeliveryPersonnelController {

    @Autowired
    private DeliveryPersonnelRepository deliveryPersonnelRepository;

    @GetMapping
    public List<DeliveryPersonnel> getAllDeliveryPersonnel() {
        return deliveryPersonnelRepository.findAll();
    }

    @GetMapping("/{id}")
    public DeliveryPersonnel getDeliveryPersonnelById(@PathVariable String id) {
        return deliveryPersonnelRepository.findById(id).orElse(null);
    }

    @PostMapping
    public DeliveryPersonnel createDeliveryPersonnel(@RequestBody DeliveryPersonnel deliveryPersonnel) {
        return deliveryPersonnelRepository.save(deliveryPersonnel);
    }

    @PutMapping("/{id}")
    public DeliveryPersonnel updateDeliveryPersonnel(@PathVariable String id, @RequestBody DeliveryPersonnel deliveryPersonnelDetails) {
        DeliveryPersonnel deliveryPersonnel = deliveryPersonnelRepository.findById(id).orElse(null);
        if (deliveryPersonnel != null) {
            deliveryPersonnel.setDeliveryName(deliveryPersonnelDetails.getDeliveryName());
            deliveryPersonnel.setDeliveryPhone(deliveryPersonnelDetails.getDeliveryPhone());
            deliveryPersonnel.setDeliveryVehicle(deliveryPersonnelDetails.getDeliveryVehicle());
            deliveryPersonnel.setDeliveryStatus(deliveryPersonnelDetails.getDeliveryStatus());
            return deliveryPersonnelRepository.save(deliveryPersonnel);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDeliveryPersonnel(@PathVariable String id) {
        deliveryPersonnelRepository.deleteById(id);
    }

}