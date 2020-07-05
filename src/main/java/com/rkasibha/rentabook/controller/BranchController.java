package com.rkasibha.rentabook.controller;

import com.rkasibha.rentabook.dto.BranchBookDto;
import com.rkasibha.rentabook.dto.BranchBookFetchDto;
import com.rkasibha.rentabook.dto.BranchDto;
import com.rkasibha.rentabook.dto.ErrorDto;
import com.rkasibha.rentabook.exception.DataNotFoundException;
import com.rkasibha.rentabook.model.Branch;
import com.rkasibha.rentabook.model.BranchBook;
import com.rkasibha.rentabook.service.BranchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<BranchDto>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBranchById(@PathVariable Integer id) {
        try {
            BranchDto branchDto = modelMapper.map(branchService.getBranchById(id), BranchDto.class);
            return new ResponseEntity<>(branchDto, HttpStatus.OK);
        } catch (DataNotFoundException ex) {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setError(ex.getMessage());
            return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add books to a branch.
     * @param id id of the branch to which the books have to be added
     * @param branchBookDto JSON object with list of book details to be added
     * @return ResponseEntity with 204 No Content if successful, 404 Not Found if branch is not found.
     */
    @PostMapping(value = "/{id}/books")
    public ResponseEntity<?> addBooksToBranch(@PathVariable Integer id, @RequestBody BranchBookDto branchBookDto) {
        try {
            branchService.addBookToBranch(id, branchBookDto);
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
        } catch (DataNotFoundException ex) {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setError(ex.getMessage());
            return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get list of all books in a branch.
     * @param id id of the branch for which the books have to be fetched
     * @return ResponseEntity with list of Book objects along with their quantity, 404 if not branch not found
     */
    @GetMapping(value = "/{id}/books")
    public ResponseEntity<?> getBranchBooks(@PathVariable Integer id) {
        try {
            Set<BranchBook> branchBooks = branchService.getBranchBooks(id);
            List<BranchBookFetchDto> branchBookFetchDtos = new ArrayList<>();
            for (BranchBook branchBook : branchBooks) {
                branchBookFetchDtos.add(new BranchBookFetchDto(entityDtoMapper.convertBookToBookDto(branchBook.getBook()), branchBook.getQuantity()));
            }
            return new ResponseEntity<>(branchBookFetchDtos, HttpStatus.OK);
        } catch(DataNotFoundException ex) {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setError(ex.getMessage());
            return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.NOT_FOUND);
        }
    }
}
