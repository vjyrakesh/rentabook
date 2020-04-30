package com.rkasibha.rentabook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkasibha.rentabook.controller.BranchController;
import com.rkasibha.rentabook.dto.BranchDto;
import com.rkasibha.rentabook.model.Branch;
import com.rkasibha.rentabook.service.BranchService;
import net.minidev.json.JSONUtil;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(BranchController.class)
public class BranchControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @MockBean
    private ModelMapper mockModelMapper;

    @Test
    public void testGetAllBranches() throws Exception{
        Branch testBranch = new Branch();
        testBranch.setId(1);

        BranchDto mockBranchDto = new BranchDto();
        mockBranchDto.setId(1);

        List<Branch> branches = Arrays.asList(testBranch);
        Mockito.when(branchService.getAllBranches()).thenReturn(branches);
        Mockito.when(mockModelMapper.map(testBranch, BranchDto.class)).thenReturn(mockBranchDto);

        mockMvc.perform(get("/branches").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)));
    }

    @Test
    public void testAddBranch() throws Exception{
        BranchDto mockBranchDtoToAdd = new BranchDto();
        mockBranchDtoToAdd.setBranchName("TestBranch");
        mockBranchDtoToAdd.setContactNumber("12345");
        mockBranchDtoToAdd.setEmailId("test@abc.com");
        mockBranchDtoToAdd.setCity("TestCity");


        Branch mockAddedBranch = new Branch();
        mockAddedBranch.setBranchName("TestBranchObj");
        mockAddedBranch.setContactNumber("12345");
        mockAddedBranch.setEmailId("test@abc.com");
        mockAddedBranch.setCity("TestCity");


        Mockito.when(branchService.addBranch(org.mockito.ArgumentMatchers.any())).thenReturn(mockAddedBranch);
        Mockito.when(mockModelMapper.map(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).thenReturn(mockAddedBranch);


        ObjectMapper mapper = new ObjectMapper();
        String mockBranchDtoToAddStr = mapper.writeValueAsString(mockBranchDtoToAdd);
        System.out.println(mockBranchDtoToAddStr);
        mockMvc.perform(post("/branches").contentType(MediaType.APPLICATION_JSON).content(mockBranchDtoToAddStr))
                .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.branchName").value("TestBranchObj"));
    }

    @Test
    public void testGetBranchById() throws Exception{
        Branch testBranch = new Branch();
        testBranch.setId(1);

        BranchDto mockBranchDto = new BranchDto();
        mockBranchDto.setId(1);

        Mockito.when(branchService.getBranchById(1)).thenReturn(testBranch);
        Mockito.when(mockModelMapper.map(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).thenReturn(mockBranchDto);

        mockMvc.perform(get("/branches/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)));
    }
}
