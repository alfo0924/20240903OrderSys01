package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.Servcie.CustomerService;
import com.example._20240903ordersys01.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerApiController {

    @Autowired
    private CustomerService customerService;

    //get 獲取所有顧客
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // get id 根據ID獲取顧客
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // post  創建新顧客
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    //put 更新顧客信息
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customerDetails) {
        return customerService.getCustomerById(id)
                .map(customer -> {
                    customer.setCustomerName(customerDetails.getCustomerName());
                    customer.setCustomerPhoneNumber(customerDetails.getCustomerPhoneNumber());
                    customer.setCustomerEmail(customerDetails.getCustomerEmail());
                    customer.setCustomerAddress(customerDetails.getCustomerAddress());
                    return ResponseEntity.ok(customerService.saveCustomer(customer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 刪除顧客
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        return customerService.getCustomerById(id)
                .map(customer -> {
                    customerService.deleteCustomer(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}