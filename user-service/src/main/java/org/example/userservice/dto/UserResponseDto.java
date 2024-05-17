package org.example.userservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDto {
    private Long ID;
    private String fullName;
    private String phoneNumber;
    private String note;
}
