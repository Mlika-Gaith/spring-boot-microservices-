package com.ghaithmlika.orderservice.repository;

import com.ghaithmlika.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
