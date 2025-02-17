package com.username4fabian.library_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.username4fabian.library_manager.entities.Book;
import com.username4fabian.library_manager.repositories.BookRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/CreateNewBook")
    public Book createNewBook(@RequestBody Book book) {
        return bookRepository.save(book);

    }

    @PostMapping("/CreateMultipleBooks")
    public Iterable<Book> createMultipleBooks(@RequestBody Iterable<Book> books) {
        return bookRepository.saveAll(books);
    }

    @PostMapping("/DeleteBook")
    public void deleteBook(@RequestBody Book book) {
        bookRepository.delete(book);
    }

}
