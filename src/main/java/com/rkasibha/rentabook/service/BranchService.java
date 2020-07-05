package com.rkasibha.rentabook.service;

import com.rkasibha.rentabook.dto.BranchBookAddDto;
import com.rkasibha.rentabook.dto.BranchBookDto;
import com.rkasibha.rentabook.exception.DataNotFoundException;
import com.rkasibha.rentabook.model.Book;
import com.rkasibha.rentabook.model.Branch;
import com.rkasibha.rentabook.model.BranchBook;
import com.rkasibha.rentabook.repository.BranchBookRepository;
import com.rkasibha.rentabook.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private BranchBookRepository branchBookRepository;

    /**
     * Get list of all branches.
     * @return List of all Branch objects in the database.
     */
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    /**
     * Add a new Branch to the database.
     * @param branch Branch details to be added
     * @return Branch object added successfully
     */
    public Branch addBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    /**
     * Fetch details of a branch given it's id.
     * @param id id of the Branch whose details have to be fetched
     * @return Branch details of the id passed
     * @throws DataNotFoundException raised when the branch with given id is not found
     */
    public Branch getBranchById(Integer id) throws DataNotFoundException {
        Optional<Branch> branchOptional = branchRepository.findById(id);
        if (branchOptional.isPresent())
            return branchOptional.get();
        else {
            throw new DataNotFoundException("Branch with id: " + id + " not found");
        }
    }

    /**
     * Add Book objects to a Branch specified by id.
     * @param branchId id of the branch to which Books have to be added
     * @param branchBookDto details of the books to be added
     * @throws DataNotFoundException raised when given branch or book is not found corresponding to the passed ids
     */
    public void addBookToBranch(Integer branchId, BranchBookDto branchBookDto) throws DataNotFoundException {
        Branch branch = this.getBranchById(branchId);
        Set<BranchBookAddDto> branchBookDtos = branchBookDto.getBooks();
        for (BranchBookAddDto branchBookAddDto : branchBookDtos) {
            Book foundBook = bookService.getBookById(branchBookAddDto.getId());
            BranchBook branchBook = new BranchBook(branch, foundBook, branchBookAddDto.getQuantity());
            branchBookRepository.save(branchBook);

        }
    }

    /**
     * Get list of all books for a given branch specified by the branch id.
     * @param branchId Id of the branch for which the books have to be fetched
     * @return Set of all books belonging to the branch
     * @throws DataNotFoundException raised when the requested branch is not found
     */
    public Set<BranchBook> getBranchBooks(Integer branchId) throws DataNotFoundException {
        Optional<Branch> optionalBranch = branchRepository.findById(branchId);
        if (optionalBranch.isPresent()) {
            return optionalBranch.get().getCatalog();
        } else {
            throw new DataNotFoundException("Branch with id: " + branchId + " not found");
        }
    }
}
