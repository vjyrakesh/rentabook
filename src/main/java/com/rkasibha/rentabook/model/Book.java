package com.rkasibha.rentabook.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String title;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    private Publisher publisher;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BranchBook> availability = new HashSet<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BookBranchCustomer> allocations = new HashSet<>();

    public Book() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<BranchBook> getAvailability() {
        return availability;
    }

    public void setAvailability(Set<BranchBook> availability) {
        this.availability = availability;
    }

    public Set<BookBranchCustomer> getAllocations() {
        return allocations;
    }

    public void setAllocations(Set<BookBranchCustomer> allocations) {
        this.allocations = allocations;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
