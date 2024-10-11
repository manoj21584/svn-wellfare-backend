package com.sfw.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Integer customerId;

    @NotBlank(message = "customerName cannot be blank ")
    @Pattern(regexp = "^[\\S]+$", message = "customerName  must not contain any whitespace")
    private String customerName;

    @NotBlank(message = "customerEmail cannot be blank ")
    @Pattern(regexp = "^[\\S]+$", message = "customerEmail  must not contain any whitespace")
    private String customerEmail;

    @NotBlank(message = "customerPhone cannot be blank ")
    @Pattern(regexp = "^[\\S]+$", message = "customerPhone  must not contain any whitespace")
    private String customerPhone;

    @NotBlank(message = "customerAddress cannot be blank ")
//    @Pattern(regexp = "^[\\S]+$", message = "customerAddress  must not contain any whitespace")
    private String customerAddress;
    @NotNull(message = "Customer loyalty points cannot be null")
    private Integer customerLoyaltyPoints=0;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

