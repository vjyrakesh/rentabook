package com.rkasibha.rentabook.repository;

import com.rkasibha.rentabook.model.BookBranchCustomer;
import com.rkasibha.rentabook.model.BookBranchCustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBranchCustomerRepository extends JpaRepository<BookBranchCustomer, BookBranchCustomerId> {
}
