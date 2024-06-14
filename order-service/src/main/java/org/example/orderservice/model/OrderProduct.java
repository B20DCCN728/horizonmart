package org.example.orderservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ProductID")
    private Long productId;
    @Column(name = "Quantity")
    private Long quantity;
    @Column(name = "SellingPrice")
    private Long sellingPrice;

    @ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "ID")
    @JsonBackReference
    private Order order;
}
