package org.example.supplierservice.service;

import org.example.supplierservice.dto.SupplierResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SupplierService {
    Optional<SupplierResponseDto> get(Long id);
    List<SupplierResponseDto> getAll();
    List<SupplierResponseDto> search(String name) throws InterruptedException;
}
