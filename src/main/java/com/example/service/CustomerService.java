package com.example.service;

import com.example.entity.Customer;
import com.example.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomersFromBangalore() {
        return customerRepository.findAll().stream()
                .filter(c -> "Bangalore".equalsIgnoreCase(c.getCity()))
                .collect(Collectors.toList());
    }

    public void updateCustomerAddress(Long id, String newCity, String newState) throws Exception {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            throw new Exception("No customer found for given unique Id");
        }
        customer.setCity(newCity);
        customer.setState(newState);
        customerRepository.update(customer);
    }

    public List<Customer> getAllCustomers() throws Exception {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new Exception("No data found in customer table");
        }
        return customers;
    }
}
