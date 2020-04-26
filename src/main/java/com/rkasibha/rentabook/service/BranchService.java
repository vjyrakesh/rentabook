package com.rkasibha.rentabook.service;

import com.rkasibha.rentabook.dto.BranchDto;
import com.rkasibha.rentabook.model.Branch;
import com.rkasibha.rentabook.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAllBooks() {
        return branchRepository.findAll();
    }

    public Branch addBranch(Branch branch) {
        return branchRepository.save(branch);
    }
}
