package com.username4fabian.library_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.username4fabian.library_manager.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
