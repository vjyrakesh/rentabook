package com.rkasibha.rentabook.dto;

import java.util.ArrayList;
import java.util.List;

public class PublisherDto {
    private Integer id;
    private String name;
    private String contactNumber;
    private String emailId;
    private String city;
    private String country;
    private List<BookDto> publishedBooks = new ArrayList<>();

    public PublisherDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<BookDto> getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(List<BookDto> publishedBooks) {
        this.publishedBooks = publishedBooks;
    }
}
