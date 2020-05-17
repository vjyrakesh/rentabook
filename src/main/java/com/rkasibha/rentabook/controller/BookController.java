package com.rkasibha.rentabook.controller;

import com.rkasibha.rentabook.dto.BookDto;
import com.rkasibha.rentabook.model.Book;
import com.rkasibha.rentabook.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ModelMapper modelMapper;

    /**
     * Get all books from database.
     * @return JSON array of BookDto objects
     */
    @GetMapping(value = "")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book oneBook : books) {
            bookDtos.add(modelMapper.map(oneBook, BookDto.class));
        }
        log.info("Retrieved " + bookDtos.size() + " books");
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }
}
