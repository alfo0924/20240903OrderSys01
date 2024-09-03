package com.example._20240903ordersys01.repository;

import com.example._20240903ordersys01.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}