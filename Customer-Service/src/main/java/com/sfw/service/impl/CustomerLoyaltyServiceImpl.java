package com.sfw.service.impl;

import com.sfw.dto.CustomerLoyaltyDTO;
import com.sfw.entities.Customer;
import com.sfw.entities.CustomerLoyalty;
import com.sfw.exceptions.ResourceNotFoundException;
import com.sfw.repositories.CustomerLoyaltyRepository;
import com.sfw.repositories.CustomerRepository;
import com.sfw.service.CustomerLoyaltyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerLoyaltyServiceImpl implements CustomerLoyaltyService {
    private CustomerLoyaltyRepository customerLoyaltyRepository;
    private CustomerRepository customerRepository;
    @Override
    public CustomerLoyaltyDTO createCustomerLoyalty(CustomerLoyaltyDTO customerLoyaltyDTO) {
        Customer customer = customerRepository.findById(customerLoyaltyDTO.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Customer","customerId", customerLoyaltyDTO.getCustomerId()));
        CustomerLoyalty customerLoyalty = toCustomerLoyalty(customerLoyaltyDTO);
        customerLoyalty.setCustomer(customer);
        CustomerLoyalty savedCustomerLoyalty = customerLoyaltyRepository.save(customerLoyalty);
        return toCustomerLoyaltyDTO(savedCustomerLoyalty);
    }


    @Override
    public List<CustomerLoyaltyDTO> getAllCustomerLoyalty() {
        List<CustomerLoyalty> allCustomerLoyalty = customerLoyaltyRepository.findAll();
        return allCustomerLoyalty.stream().map(this::toCustomerLoyaltyDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerLoyaltyDTO getCustomerLoyaltyById(Integer loyaltyId) {
        CustomerLoyalty customerLoyalty = customerLoyaltyRepository.findById(loyaltyId).orElseThrow(() -> new ResourceNotFoundException("CustomerLoyalty", "loyaltyId", loyaltyId));
        return toCustomerLoyaltyDTO(customerLoyalty);
    }

    @Override
    public String deleteCustomerLoyaltyById(Integer loyaltyId) {
        CustomerLoyalty customerLoyalty = customerLoyaltyRepository.findById(loyaltyId).orElseThrow(() -> new ResourceNotFoundException("CustomerLoyalty", "loyaltyId", loyaltyId));
        customerLoyaltyRepository.deleteById(customerLoyalty.getLoyaltyId());
        return "Deleted successfully";
    }

//    @Override
//    public CustomerLoyaltyDTO updateCustomerLoyaltyById(CustomerLoyaltyDTO customerLoyaltyDTO, Integer loyaltyId) {
//        CustomerLoyalty customerLoyalty = customerLoyaltyRepository.findById(loyaltyId).orElseThrow(() -> new ResourceNotFoundException("CustomerLoyalty", "loyaltyId", loyaltyId));
//        CustomerLoyalty customerLoyalty1 = toCustomerLoyalty(customerLoyaltyDTO);
//        customerLoyalty.setCustomer(customerLoyalty1.getCustomer());
//        customerLoyalty.setTransactionId(customerLoyalty1.getTransactionId());
//        customerLoyalty.setPointsRedeemed(customerLoyalty1.getPointsRedeemed());
//        customerLoyalty.setPointsEarned(customerLoyalty1.getPointsEarned());
//        customerLoyalty.setUpdatedAt(LocalDateTime.now());
//        CustomerLoyalty save = customerLoyaltyRepository.save(customerLoyalty);
//        return toCustomerLoyaltyDTO(save);
//    }

    private CustomerLoyalty toCustomerLoyalty(CustomerLoyaltyDTO customerLoyaltyDTO){
        CustomerLoyalty customerLoyalty = new CustomerLoyalty();
        customerLoyalty.setLoyaltyId(customerLoyaltyDTO.getLoyaltyId());
//        customerLoyalty.setCustomerId(customerLoyaltyDTO.getCustomerId());
        customerLoyalty.setPointsEarned(customerLoyaltyDTO.getPointsEarned());
        customerLoyalty.setPointsRedeemed(customerLoyaltyDTO.getPointsRedeemed());
        customerLoyalty.setTransactionId(customerLoyaltyDTO.getTransactionId());
        customerLoyalty.setCreatedAt(customerLoyaltyDTO.getCreatedAt());
        customerLoyalty.setUpdatedAt(customerLoyaltyDTO.getUpdatedAt());
        return customerLoyalty;
    }

    private  CustomerLoyaltyDTO toCustomerLoyaltyDTO(CustomerLoyalty customerLoyalty) {
        CustomerLoyaltyDTO dto = new CustomerLoyaltyDTO();
        dto.setLoyaltyId(customerLoyalty.getLoyaltyId());
        dto.setCustomerId(customerLoyalty.getCustomer().getCustomerId());
        dto.setPointsEarned(customerLoyalty.getPointsEarned());
        dto.setPointsRedeemed(customerLoyalty.getPointsRedeemed());
        dto.setTransactionId(customerLoyalty.getTransactionId());
        dto.setCreatedAt(customerLoyalty.getCreatedAt());
        dto.setUpdatedAt(customerLoyalty.getUpdatedAt());

        return dto;
    }
}
