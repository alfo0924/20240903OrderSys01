package com.example._20240903ordersys01.Servcie;

import com.example._20240903ordersys01.model.DeliveryPersonnel;
import com.example._20240903ordersys01.repository.DeliveryPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryPersonnelService {

    @Autowired
    private DeliveryPersonnelRepository deliveryPersonnelRepository;

    public List<DeliveryPersonnel> getAllDeliveryPersonnel() {
        return deliveryPersonnelRepository.findAll();
    }

    public Optional<DeliveryPersonnel> getDeliveryPersonnelById(String id) {
        return deliveryPersonnelRepository.findById(id);
    }

    public DeliveryPersonnel saveDeliveryPersonnel(DeliveryPersonnel deliveryPersonnel) {
        return deliveryPersonnelRepository.save(deliveryPersonnel);
    }

    public void deleteDeliveryPersonnel(String id) {
        deliveryPersonnelRepository.deleteById(id);
    }
}