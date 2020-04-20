package com.rkasibha.rentabook.dto;

public class ReviewDto {
    private Integer id;
    private String content;

    public ReviewDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
