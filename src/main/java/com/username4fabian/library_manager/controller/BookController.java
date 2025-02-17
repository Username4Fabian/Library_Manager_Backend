package com.username4fabian.library_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.username4fabian.library_manager.entities.Book;
import com.username4fabian.library_manager.repositories.BookRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @DeleteMapping("/DeleteBook/{bookId}")
    public void deleteBook(@PathVariable int bookId) {
        bookRepository.deleteById(bookId);
    }

    @GetMapping("/GetAllBooks")
    public Iterable<Book> getAllBooks() {
        Iterable<Book> books = bookRepository.findAll();
        return books;
    }

}
