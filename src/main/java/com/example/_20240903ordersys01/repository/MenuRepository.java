package com.example._20240903ordersys01.repository;

import com.example._20240903ordersys01.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, String> {
}