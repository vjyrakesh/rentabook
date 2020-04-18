package com.rkasibha.rentabook.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BookBranchCustomer {

    @EmbeddedId
    private BookBranchCustomerId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("branchId")
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customerId")
    private Customer customer;

    private LocalDate allocatedOn;

    public BookBranchCustomer() {
    }

    public BookBranchCustomer(Book book, Branch branch, Customer customer, LocalDate allocatedOn) {
        this.book = book;
        this.branch = branch;
        this.customer = customer;
        this.allocatedOn = allocatedOn;
    }

    public BookBranchCustomerId getId() {
        return id;
    }

    public void setId(BookBranchCustomerId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getAllocatedOn() {
        return allocatedOn;
    }

    public void setAllocatedOn(LocalDate allocatedOn) {
        this.allocatedOn = allocatedOn;
    }
}
