package com.rkasibha.rentabook;


import com.rkasibha.rentabook.model.Branch;
import com.rkasibha.rentabook.repository.BranchRepository;
import com.rkasibha.rentabook.service.BranchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class BranchServiceUnitTest {

    @InjectMocks
    private BranchService branchService;

    @Mock
    private BranchRepository branchRepository;

    @Test
    public void testAddNewBranch() {
        Branch testBranch = new Branch();
        testBranch.setBranchName("TestBranch");
        testBranch.setCity("TestCity");
        testBranch.setContactNumber("TestContactNumber");
        testBranch.setEmailId("TestEmailId");
        Mockito.when(branchRepository.save(testBranch)).thenReturn(testBranch);
        Branch addedBranch = branchService.addBranch(testBranch);
        assertThat(addedBranch.getCity()).isEqualTo("TestCity");
    }

    @Test
    public void findBranchById() {
        Branch testBranch = new Branch();
        testBranch.setId(1);
        testBranch.setBranchName("TestBranch");
        testBranch.setCity("TestCity");
        testBranch.setContactNumber("TestContactNumber");
        testBranch.setEmailId("TestEmailId");
        Mockito.when(branchRepository.findById(testBranch.getId())).thenReturn(java.util.Optional.of(testBranch));
        Branch foundBranch = branchService.getBranchById(1);
        assertThat(foundBranch.getId()).isEqualTo(1);
    }

    @Test
    public void testGetAllBranches() {
        Branch testBranch1 = new Branch();
        testBranch1.setId(1);
        testBranch1.setBranchName("TestBranch");
        testBranch1.setCity("TestCity");
        testBranch1.setContactNumber("TestContactNumber");
        testBranch1.setEmailId("TestEmailId");

        Branch testBranch2 = new Branch();
        testBranch2.setId(2);
        testBranch2.setBranchName("TestBranch");
        testBranch2.setCity("TestCity");
        testBranch2.setContactNumber("TestContactNumber");
        testBranch2.setEmailId("TestEmailId");

        List<Branch> branches = Arrays.asList(testBranch1,testBranch2);
        Mockito.when(branchRepository.findAll()).thenReturn(branches);
        List<Branch> returnedBranches = branchService.getAllBranches();
        assertThat(returnedBranches.size()).isEqualTo(2);
//        assertThat(returnedBranches.get(0).getId()).isEqualTo(1);
    }
}
