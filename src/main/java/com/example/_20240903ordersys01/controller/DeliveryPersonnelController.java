package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.Servcie.DeliveryPersonnelService;
import com.example._20240903ordersys01.model.DeliveryPersonnel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/delivery-personnel")
public class DeliveryPersonnelController {

    @Autowired
    private DeliveryPersonnelService deliveryPersonnelService;

    @GetMapping
    public String listDeliveryPersonnel(Model model) {
        model.addAttribute("deliveryPersonnels", deliveryPersonnelService.getAllDeliveryPersonnel());
        return "delivery-personnel/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("deliveryPersonnel", new DeliveryPersonnel());
        return "delivery-personnel/add";
    }

    @PostMapping("/add")
    public String addDeliveryPersonnel(@ModelAttribute DeliveryPersonnel deliveryPersonnel) {
        deliveryPersonnelService.saveDeliveryPersonnel(deliveryPersonnel);
        return "redirect:/delivery-personnel";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("deliveryPersonnel", deliveryPersonnelService.getDeliveryPersonnelById(id).orElseThrow());
        return "delivery-personnel/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateDeliveryPersonnel(@PathVariable String id, @ModelAttribute DeliveryPersonnel deliveryPersonnel) {
        deliveryPersonnel.setDeliveryId(id);
        deliveryPersonnelService.saveDeliveryPersonnel(deliveryPersonnel);
        return "redirect:/delivery-personnel";
    }

    @GetMapping("/delete/{id}")
    public String deleteDeliveryPersonnel(@PathVariable String id) {
        deliveryPersonnelService.deleteDeliveryPersonnel(id);
        return "redirect:/delivery-personnel";
    }
}