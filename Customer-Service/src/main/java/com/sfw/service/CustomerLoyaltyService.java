package com.sfw.service;

import com.sfw.dto.CustomerLoyaltyDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerLoyaltyService {
    CustomerLoyaltyDTO createCustomerLoyalty(CustomerLoyaltyDTO customerLoyaltyDTO);
    List<CustomerLoyaltyDTO> getAllCustomerLoyalty();
    CustomerLoyaltyDTO getCustomerLoyaltyById(Integer loyaltyId);
//    CustomerLoyaltyDTO updateCustomerLoyaltyById(CustomerLoyaltyDTO customerLoyaltyDTO,Integer loyaltyId);
    String deleteCustomerLoyaltyById(Integer loyaltyId);

}
