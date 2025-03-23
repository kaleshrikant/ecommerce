package com.alibou.ecommerce.customer;

import com.alibou.ecommerce.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    public String createCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("No Customer found with provided ID")
        ));
        mergeCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstName())) { customer.setFirstName(customerRequest.firstName()); }
        if(StringUtils.isNotBlank(customerRequest.lastName())) { customer.setLastName(customerRequest.lastName()); }
        if(StringUtils.isNotBlank(customerRequest.email())) { customer.setEmail(customerRequest.email()); }
        if(Objects.nonNull(customerRequest.address())) { customer.setAddress(customerRequest.address()); }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                                            .stream()
                                            .map(customerMapper::fromCustomer)
                                            .collect(Collectors.toList());
    }
}
