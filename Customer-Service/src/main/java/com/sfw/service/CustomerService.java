package com.sfw.service;

import com.sfw.dto.CustomerDTO;


import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Integer customerId);
    List<CustomerDTO> getAllCustomer();
    CustomerDTO updateCustomer(CustomerDTO customerDTO, Integer customerId);
    String deleteCustomerById(Integer customerId);
}
