package com.sfw.dto;

import com.sfw.entities.Customer;
import com.sfw.entities.CustomerLoyalty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoyaltyDTO {

    private Integer loyaltyId;

    @NotNull(message = "Customer ID cannot be null")
    private Integer customerId;

    private Integer pointsEarned;

    private Integer pointsRedeemed;

    private String transactionId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
