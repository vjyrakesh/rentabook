package com.rkasibha.rentabook.controller;

import com.rkasibha.rentabook.dto.BookDto;
import com.rkasibha.rentabook.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Book convertBookDtoToBook(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    public BookDto convertBookToBookDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

}
