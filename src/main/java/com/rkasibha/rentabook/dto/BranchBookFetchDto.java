package com.rkasibha.rentabook.dto;

import lombok.Data;

@Data
public class BranchBookFetchDto {
    private BookDto book;
    private Integer quantity;

    public BranchBookFetchDto(BookDto book, Integer quantity) {
        this.book = book;
        this.quantity = quantity;
    }
}
