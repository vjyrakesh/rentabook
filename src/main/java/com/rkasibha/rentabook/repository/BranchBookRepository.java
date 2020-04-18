package com.rkasibha.rentabook.repository;

import com.rkasibha.rentabook.model.BranchBook;
import com.rkasibha.rentabook.model.BranchBookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchBookRepository extends JpaRepository<BranchBook, BranchBookId> {
}
