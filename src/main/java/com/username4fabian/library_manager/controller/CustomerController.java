package com.username4fabian.library_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.username4fabian.library_manager.entities.Customer;
import com.username4fabian.library_manager.repositories.CustomerRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    @DeleteMapping("/DeleteCustomer/{customerId}")
    public void deleteCustomer(@PathVariable int customerId) {
        customerRepository.deleteById(customerId);
    }

    @GetMapping("/GetAllCustomers")
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @DeleteMapping("/DeleteAllCustomers")
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile() throws IOException {
        Resource resource = new ClassPathResource("static/Beispiel.xlsx");
        Path path = resource.getFile().toPath();
        byte[] data = Files.readAllBytes(path);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Beispiel.xlsx");

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}