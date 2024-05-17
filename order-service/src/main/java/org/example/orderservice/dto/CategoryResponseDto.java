package org.example.orderservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
}
