package org.example.productservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "SupplierID")
    private Long supplierID;
    // Set the relationship between Product and Category
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "CategoryID", referencedColumnName = "ID")
    private Category category;
    @Column(name = "Name")
    private String name;
    @Column(name = "Quantity")
    private Long quantity;
    @Column(name = "PurchasePrice")
    private Long purchasePrice;
    @Column(name = "SellingPrice")
    private Long sellingPrice;
    @Column(name = "CreatedDate")
    private LocalDateTime createdDate;
    @Column(name = "ImagePath")
    private String imagePath;
    @Column(name = "Description")
    private String description;
}
