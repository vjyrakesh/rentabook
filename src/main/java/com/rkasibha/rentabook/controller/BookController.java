package com.rkasibha.rentabook.controller;

import com.rkasibha.rentabook.dto.BookDto;
import com.rkasibha.rentabook.dto.ErrorDto;
import com.rkasibha.rentabook.exception.DataNotFoundException;
import com.rkasibha.rentabook.model.Book;
import com.rkasibha.rentabook.service.BookService;
import lombok.Data;
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
     * Get all books from database if title query parameter is not provided.
     * If title query param is provided, the DTO of book object matching the param is returned.
     * @param title Title of the Book to be searched for. Optional param.
     * @return JSON array of BookDto objects
     */
    @GetMapping(value = "")
    public ResponseEntity<?> getAllBooks(@RequestParam(name = "title", required = false) String title) {
        if (title != null && !title.equals("")) {
            try {
                Book foundBook = bookService.getBookByTitle(title);
                return new ResponseEntity<BookDto>(entityDtoMapper.convertBookToBookDto(foundBook), HttpStatus.OK);
            } catch (DataNotFoundException ex) {
                log.error(ex.getMessage());
                ErrorDto errorDto = new ErrorDto();
                errorDto.setError(ex.getMessage());
                return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.NOT_FOUND);
            }
        } else {
            List<Book> books = bookService.getAllBooks();
            if (books != null) {
                List<BookDto> bookDtos = new ArrayList<>();
                for (Book oneBook : books) {
                    bookDtos.add(entityDtoMapper.convertBookToBookDto(oneBook));
                }
                log.info("Retrieved " + bookDtos.size() + " books");
                return new ResponseEntity<List<BookDto>>(bookDtos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
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
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {
        try {
            Book returnedBook = bookService.getBookById(id);
            return new ResponseEntity<>(entityDtoMapper.convertBookToBookDto(returnedBook), HttpStatus.OK);
        } catch (DataNotFoundException ex) {
            log.error(ex.getMessage());
            ErrorDto errorDto = new ErrorDto();
            errorDto.setError(ex.getMessage());
            return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.NOT_FOUND);
        }
    }


}
