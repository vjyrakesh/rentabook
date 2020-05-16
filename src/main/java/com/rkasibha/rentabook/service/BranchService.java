package com.rkasibha.rentabook.service;

import com.rkasibha.rentabook.model.Branch;
import com.rkasibha.rentabook.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch addBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch getBranchById(Integer id) {
        return branchRepository.findById(id).get();
    }
}
