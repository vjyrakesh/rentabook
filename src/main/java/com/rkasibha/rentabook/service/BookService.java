package com.rkasibha.rentabook.service;

import com.rkasibha.rentabook.model.Book;
import com.rkasibha.rentabook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for all Book related operations.
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Fetches all Book objects from repository.
     * @return List of all Book objects
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
