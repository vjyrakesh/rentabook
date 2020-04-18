package com.rkasibha.rentabook.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookBranchCustomerId implements Serializable {

    private Integer bookId;
    private Integer branchId;
    private Integer customerId;

    private static final long serialVersionUID = 1L;

    public BookBranchCustomerId() {
    }

    public BookBranchCustomerId(Integer bookId, Integer branchId, Integer customerId) {
        this.bookId = bookId;
        this.branchId = branchId;
        this.customerId = customerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookBranchCustomerId that = (BookBranchCustomerId) o;
        return bookId.equals(that.bookId) &&
                branchId.equals(that.branchId) &&
                customerId.equals(that.customerId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((bookId == null) ? 0 : bookId.hashCode());
        result = prime * result
                + ((branchId == null) ? 0 : branchId.hashCode());
        result = prime * result
                + ((customerId == null) ? 0 : customerId.hashCode());
        return result;
    }
}
