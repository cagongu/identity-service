package com.spring6framework.identityservice.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileCreationRequest {
    UUID userId;
    String firstName;
    String lastName;
    LocalDate dob;
    String phoneNumber;
    String email;
    String city;
}
