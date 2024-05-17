package org.example.productservice.service_server;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SupplierResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String taxID;
    private String description;
}