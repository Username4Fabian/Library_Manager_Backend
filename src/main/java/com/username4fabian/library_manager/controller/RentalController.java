package com.username4fabian.library_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.username4fabian.library_manager.entities.Book;
import com.username4fabian.library_manager.entities.Customer;
import com.username4fabian.library_manager.repositories.BookRepository;
import com.username4fabian.library_manager.repositories.CustomerRepository;

@RestController
@RequestMapping("/rental")
public class RentalController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/rentBook")
    public void rentBook(@RequestParam int customerId, @RequestParam int bookId) {
        Book book;
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (bookId == 0) {
            book = null;
        } else {
            book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
        }
        customer.setBook(book);
        customerRepository.save(customer);
    }

    @PostMapping("/returnBook")
    public void returnBook(@RequestParam(required = false) Integer customerId,
            @RequestParam(required = false) Integer bookId) {
        if (customerId != null) {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            customer.setBook(null);
            customerRepository.save(customer);
        } else if (bookId != null) {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            Customer customer = customerRepository.findByBook(book);
            if (customer != null) {
                customer.setBook(null);
                customerRepository.save(customer);
            }
        } else {
            throw new RuntimeException("Either customerId or bookId must be provided");
        }
    }
}
