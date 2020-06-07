package com.rkasibha.rentabook.controller;

import com.rkasibha.rentabook.dto.BookDto;
import com.rkasibha.rentabook.dto.CustomerDto;
import com.rkasibha.rentabook.model.Book;
import com.rkasibha.rentabook.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean class to handle the various entity to DTO conversions and vice versa.
 */
@Component
public class EntityDtoMapper {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Convert a given BookDto object to Book entity object.
     * @param bookDto BookDto object to be converted
     * @return converted Book object
     */
    public Book convertBookDtoToBook(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    /**
     * Convert a given Book object to BookDto object.
     * @param book Book object to be converted
     * @return converted BookDto object
     */
    public BookDto convertBookToBookDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    /**
     * Convert Customer object to CustomerDto object.
     * @param customer Customer object to be converted
     * @return converted CustomerDto object
     */
    public CustomerDto convertCustomerToCustomerDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    /**
     * Convert CustomerDto object to Customer entity object.
     * @param customerDto CustomerDto object to be converted
     * @return converted Customer object
     */
    public Customer convertCustomerDtoToCustomer(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }

}
