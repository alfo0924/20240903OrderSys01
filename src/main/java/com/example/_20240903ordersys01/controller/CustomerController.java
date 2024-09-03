package com.example._20240903ordersys01.controller;

import com.example._20240903ordersys01.model.Customer;
import com.example._20240903ordersys01.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setCustomerName(customerDetails.getCustomerName());
            customer.setCustomerPhoneNumber(customerDetails.getCustomerPhoneNumber());
            customer.setCustomerEmail(customerDetails.getCustomerEmail());
            customer.setCustomerAddress(customerDetails.getCustomerAddress());
            return customerRepository.save(customer);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
    }
}