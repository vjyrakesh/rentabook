package com.rkasibha.rentabook.service;

import com.rkasibha.rentabook.exception.DataNotFoundException;
import com.rkasibha.rentabook.model.Book;
import com.rkasibha.rentabook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    /**
     * Add a book to the repository.
     * @param book Book object to be added
     * @return Book object added successfully
     */
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Get a book by id.
     * @param id Id of the book to be retrieved
     * @return Book object matching the id
     */
    public Book getBookById(Integer id) throws DataNotFoundException {
        try {
            return bookRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            DataNotFoundException dataNotFoundException = new DataNotFoundException();
            dataNotFoundException.setMessage("Book with id: " + id + " not found");
            throw dataNotFoundException;
        }
    }

    /**
     * Get a book by title.
     * @param title Title of the Book to retrieve
     * @return Book object matching the given title
     */
    public Book getBookByTitle(String title) throws DataNotFoundException{
        Book foundBook = bookRepository.findBookByTitle(title);
        if (foundBook == null) {
            DataNotFoundException dataNotFoundException = new DataNotFoundException();
            dataNotFoundException.setMessage("Book with title: " + title + " not found");
            throw dataNotFoundException;
        } else {
            return bookRepository.findBookByTitle(title);
        }
    }
}
