package com.username4fabian.library_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.username4fabian.library_manager.entities.Customer;
import com.username4fabian.library_manager.repositories.CustomerRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/CreateNewCustomer")
    public Customer createNewCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PostMapping("/CreateMultipleCustomers")
    public Iterable<Customer> createMultipleCustomers(@RequestBody Iterable<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    @PostMapping("/DeleteCustomer")
    public void deleteCustomer(@RequestBody Customer customer) {
        customerRepository.delete(customer);
    }

    @GetMapping("/GetAllCustomers")
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
