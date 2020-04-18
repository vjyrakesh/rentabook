package com.rkasibha.rentabook.repository;

import com.rkasibha.rentabook.model.BranchAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchAdminRepository extends JpaRepository<BranchAdmin, Integer> {
}
