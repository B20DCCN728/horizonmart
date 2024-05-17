package org.example.supplierservice.controller;

import org.example.supplierservice.dto.SupplierResponseDto;
import org.example.supplierservice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    // Add a new method to the SupplierController class that returns all Supplier objects.
    @GetMapping("/all")
    public List<SupplierResponseDto> getAll() {
        return supplierService.getAll();
    }

    // Add a new method to the SupplierController class that returns a Supplier object by its ID.
    @GetMapping("/get/{id}")
    public SupplierResponseDto get(@PathVariable Long id) {
        return supplierService.get(id).orElse(null);
    }

    // Add a new method to the SupplierController class that returns a list of Supplier objects by name.
    @GetMapping("/search/{name}")
    public List<SupplierResponseDto> search(@PathVariable String name) throws InterruptedException {
        return supplierService.search(name);
    }
}
