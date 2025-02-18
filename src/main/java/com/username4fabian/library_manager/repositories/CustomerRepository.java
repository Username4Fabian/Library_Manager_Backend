package com.username4fabian.library_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.username4fabian.library_manager.entities.Book;
import com.username4fabian.library_manager.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByBook(Book book);

}
