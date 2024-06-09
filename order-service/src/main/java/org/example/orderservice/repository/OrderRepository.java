package org.example.orderservice.repository;

import org.example.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT SUM(op.quantity * op.sellingPrice) AS total_revenue FROM Order o Join OrderProduct op ON o.id = op.order.id WHERE  o.orderDate >= ?1 AND o.orderDate <= ?2")
    int calculateTotalRevenue(LocalDateTime start, LocalDateTime end);
    @Query("SELECT SUM(op.quantity) AS total_sold FROM Order o JOIN OrderProduct op ON o.id = op.order.id WHERE o.orderDate >= ?1 AND o.orderDate <= ?2")
    int countProductSold(LocalDateTime start, LocalDateTime end);
}
