package org.example.supplierservice.repository;

import org.example.supplierservice.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByNameContainingIgnoreCase(String name);
}
