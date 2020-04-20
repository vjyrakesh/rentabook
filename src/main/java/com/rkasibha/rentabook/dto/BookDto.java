package com.rkasibha.rentabook.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookDto {
    private Integer id;
    private String title;
    private List<ReviewDto> reviews = new ArrayList<>();
    private Set<AuthorDto> authors = new HashSet<>();

}
