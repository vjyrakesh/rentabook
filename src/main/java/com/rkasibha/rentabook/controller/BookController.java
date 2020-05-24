package com.rkasibha.rentabook.controller;

import com.rkasibha.rentabook.dto.BookDto;
import com.rkasibha.rentabook.model.Book;
import com.rkasibha.rentabook.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for all Book related operations.
 */
@Slf4j
@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    /**
     * Get all books from database.
     * @return JSON array of BookDto objects
     */
    @GetMapping(value = "")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book oneBook : books) {
            bookDtos.add(entityDtoMapper.convertBookToBookDto(oneBook));
        }
        log.info("Retrieved " + bookDtos.size() + " books");
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }

    /**
     * Add book to the database.
     * @param bookDto DTO of Book to add
     * @return DTO of added Book object
     */
    @PostMapping(value = "")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        Book addedBook = bookService.addBook(entityDtoMapper.convertBookDtoToBook(bookDto));
        return new ResponseEntity<>(entityDtoMapper.convertBookToBookDto(addedBook), HttpStatus.CREATED);
    }

    /**
     * Get details of a book by its id.
     * @param id ID of the book to be retrieved
     * @return DTO of fetched Book object
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer id) {
        Book returnedBook = bookService.getBookById(id);
        return new ResponseEntity<>(entityDtoMapper.convertBookToBookDto(returnedBook), HttpStatus.OK);
    }
}
