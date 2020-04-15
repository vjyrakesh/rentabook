package com.rkasibha.rentabook.model;

import javax.persistence.*;

@Entity
public class BranchBook {

    @EmbeddedId
    private BranchBookId id = new BranchBookId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("branchId")
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;

    private Integer quantity;

    public BranchBook() {
    }

    public BranchBook(Branch branch, Book book, Integer quantity) {
        this.branch = branch;
        this.book = book;
        this.quantity = quantity;
        this.id = new BranchBookId(branch.getId(), book.getId());
    }

    public BranchBookId getBranchBookId() {
        return id;
    }

    public void setBranchBookId(BranchBookId id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
