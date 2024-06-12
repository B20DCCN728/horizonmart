package org.example.orderservice.repository;

import jakarta.validation.constraints.NotNull;
import org.example.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT SUM(op.quantity * op.sellingPrice) AS total_revenue FROM Order o Join OrderProduct op ON o.id = op.order.id WHERE  o.orderDate >= ?1 AND o.orderDate <= ?2")
    int calculateTotalRevenue(LocalDateTime start, LocalDateTime end);
    @Query("SELECT SUM(op.quantity) AS total_sold FROM Order o JOIN OrderProduct op ON o.id = op.order.id WHERE o.orderDate >= ?1 AND o.orderDate <= ?2")
    int countProductSold(LocalDateTime start, LocalDateTime end);
    List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
    int countByOrderDateBetween(LocalDateTime start, LocalDateTime end);
    @Query("SELECT op.productId FROM Order o JOIN OrderProduct op ON o.id = op.order.id WHERE o.orderDate >= ?1 AND o.orderDate <= ?2 GROUP BY op.productId ORDER BY SUM(op.quantity) DESC")
    Long getTop1StProductSold(LocalDateTime start, LocalDateTime end);
}
