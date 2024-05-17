package org.example.supplierservice.service;

import org.example.supplierservice.dto.SupplierResponseDto;
import org.example.supplierservice.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Optional<SupplierResponseDto> get(Long id) {
        return supplierRepository.findById(id)
                .map(
                        supplier -> new SupplierResponseDto(
                                supplier.getId(),
                                supplier.getName(),
                                supplier.getAddress(),
                                supplier.getPhoneNumber(),
                                supplier.getTaxID(),
                                supplier.getDescription()
                        )
                );
    }

    @Override
    public List<SupplierResponseDto> getAll() {
        return supplierRepository.findAll().stream()
                .map(
                        supplier -> new SupplierResponseDto(
                                supplier.getId(),
                                supplier.getName(),
                                supplier.getAddress(),
                                supplier.getPhoneNumber(),
                                supplier.getTaxID(),
                                supplier.getDescription()
                        )
                ).toList();
    }

    @Override
    public List<SupplierResponseDto> search(String name) throws InterruptedException {
        Thread.sleep(1000);
        return supplierRepository.findByNameContainingIgnoreCase(name).stream()
                .map(
                        supplier -> new SupplierResponseDto(
                                supplier.getId(),
                                supplier.getName(),
                                supplier.getAddress(),
                                supplier.getPhoneNumber(),
                                supplier.getTaxID(),
                                supplier.getDescription()
                        )
                ).toList();
    }
}
