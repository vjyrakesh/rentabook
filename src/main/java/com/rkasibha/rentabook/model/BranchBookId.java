package com.rkasibha.rentabook.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BranchBookId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer branchId;
    private Integer bookId;

    public BranchBookId() { }

    public BranchBookId(Integer branchId, Integer bookId) {
        super();
        this.branchId = branchId;
        this.bookId = bookId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchBookId that = (BranchBookId) o;
        return Objects.equals(branchId, that.branchId) &&
                Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((bookId == null) ? 0 : bookId.hashCode());
        result = prime * result
                + ((branchId == null) ? 0 : branchId.hashCode());
        return result;
    }
}
