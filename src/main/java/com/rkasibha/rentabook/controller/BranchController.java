package com.rkasibha.rentabook.controller;

import com.rkasibha.rentabook.dto.BranchDto;
import com.rkasibha.rentabook.model.Branch;
import com.rkasibha.rentabook.service.BranchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<BranchDto>> getAllBranches() {
        List<Branch> branches = branchService.getAllBooks();
        List<BranchDto> branchDtos = new ArrayList<>();
        for(Branch branch : branches) {
            branchDtos.add(modelMapper.map(branch, BranchDto.class));
        }
        return new ResponseEntity<>(branchDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "", method=RequestMethod.POST)
    public ResponseEntity<BranchDto> addBranch(@RequestBody BranchDto branchDto) {
        Branch branch = modelMapper.map(branchDto, Branch.class);
        Branch addedBranch = branchService.addBranch(branch);
        return new ResponseEntity<>(modelMapper.map(addedBranch, BranchDto.class), HttpStatus.CREATED);
    }
}
