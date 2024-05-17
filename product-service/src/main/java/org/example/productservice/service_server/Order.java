package org.example.productservice.service_server;


import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Long userId;
    private Long total;
    private LocalDateTime orderDate;
    private String note;

    public Order() {
    }

    public Order(Long id, Long userId, Long total, LocalDateTime orderDate, String note) {
        this.id = id;
        this.userId = userId;
        this.total = total;
        this.orderDate = orderDate;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
