package com.sfw.service.impl;

import com.sfw.dto.CustomerDTO;
import com.sfw.entities.Customer;
import com.sfw.exceptions.ResourceNotFoundException;
import com.sfw.repositories.CustomerRepository;
import com.sfw.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer=toCustomerEntity(customerDTO);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        return toCustomerDTO(savedCustomer);
    }
    @Override
    public CustomerDTO getCustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));
        return toCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> allCustomerList = customerRepository.findAll();
        return allCustomerList.stream().map(this::toCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Integer customerId) {
        Customer customerEntity = toCustomerEntity(customerDTO);
        Customer customerFromDb = customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("customer","customerId",customerId));
        customerFromDb.setCustomerName(customerEntity.getCustomerName());
        customerFromDb.setCustomerEmail(customerEntity.getCustomerEmail());
        customerFromDb.setCustomerAddress(customerEntity.getCustomerAddress());
        customerFromDb.setCustomerPhone(customerEntity.getCustomerPhone());
        customerFromDb.setCustomerLoyaltyPoints(customerEntity.getCustomerLoyaltyPoints());
        customerFromDb.setUpdatedAt(LocalDateTime.now());
        Customer afterUpdate = customerRepository.save(customerFromDb);
        return toCustomerDTO(afterUpdate);
    }

    @Override
    public String deleteCustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));
        customerRepository.deleteById(customer.getCustomerId());
        return "Deleted Successfully";
    }



    private Customer toCustomerEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerEmail(customerDTO.getCustomerEmail());
        customer.setCustomerPhone(customerDTO.getCustomerPhone());
        customer.setCustomerAddress(customerDTO.getCustomerAddress());
        customer.setCustomerLoyaltyPoints(customerDTO.getCustomerLoyaltyPoints());
        customer.setCreatedAt(customerDTO.getCreatedAt());
        customer.setUpdatedAt(customerDTO.getUpdatedAt());
        return customer;
    }
    private CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setCustomerEmail(customer.getCustomerEmail());
        customerDTO.setCustomerAddress(customer.getCustomerAddress());
        customerDTO.setCustomerPhone(customer.getCustomerPhone());
        customerDTO.setCustomerLoyaltyPoints(customer.getCustomerLoyaltyPoints());
        customerDTO.setCreatedAt(customer.getCreatedAt());
        customerDTO.setUpdatedAt(customer.getUpdatedAt());
        return customerDTO;
    }
}
