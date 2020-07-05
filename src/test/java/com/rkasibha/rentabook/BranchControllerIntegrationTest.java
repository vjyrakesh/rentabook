package com.rkasibha.rentabook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkasibha.rentabook.dto.BranchBookAddDto;
import com.rkasibha.rentabook.dto.BranchBookDto;
import com.rkasibha.rentabook.dto.BranchDto;
import com.rkasibha.rentabook.model.Book;
import com.rkasibha.rentabook.model.Branch;
import com.rkasibha.rentabook.service.BookService;
import com.rkasibha.rentabook.service.BranchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class BranchControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BranchService branchService;

    @Autowired
    private BookService bookService;

    @Test
    public void testGetBranches() throws Exception{
        mockMvc.perform(get("/branches").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddBranch() throws Exception{
        BranchDto mockBranchDtoToAdd = new BranchDto();
        mockBranchDtoToAdd.setBranchName("TestBranch");
        mockBranchDtoToAdd.setContactNumber("12345");
        mockBranchDtoToAdd.setEmailId("test@abc.com");
        mockBranchDtoToAdd.setCity("TestCity");
        ObjectMapper mapper = new ObjectMapper();
        String mockBranchDtoToAddStr = mapper.writeValueAsString(mockBranchDtoToAdd);

        mockMvc.perform(post("/branches").contentType(MediaType.APPLICATION_JSON).content(mockBranchDtoToAddStr))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.branchName").value("TestBranch"));
    }

    @Test
    public void testGetBranchById() throws Exception{
        Branch mockAddedBranch = new Branch();
        mockAddedBranch.setBranchName("TestBranchObj");
        mockAddedBranch.setContactNumber("12345");
        mockAddedBranch.setEmailId("test@abc.com");
        mockAddedBranch.setCity("TestCity");

        branchService.addBranch(mockAddedBranch);

        mockMvc.perform(get("/branches/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)));
    }

    @Test
    public void testAddBookToBranch() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setTitle("TestBook");
        bookService.addBook(book);


        Branch branch = new Branch();
        branch.setId(1);
        branch.setBranchName("TestBranch");
        branch.setCity("Test");
        branch.setEmailId("test@abc.com");
        branch.setContactNumber("+9199293930");
        branchService.addBranch(branch);

        BranchBookDto branchBookDto = new BranchBookDto();
        BranchBookAddDto branchBookAddDto = new BranchBookAddDto();
        branchBookAddDto.setId(1);
        branchBookAddDto.setQuantity(1);
        Set<BranchBookAddDto> branchBookAddDtos = new HashSet<>();
        branchBookAddDtos.add(branchBookAddDto);
        branchBookDto.setBooks(branchBookAddDtos);

        ObjectMapper mapper = new ObjectMapper();
        String branchBookDtoStr = mapper.writeValueAsString(branchBookDto);

        mockMvc.perform(post("/branches/1/books").contentType(MediaType.APPLICATION_JSON).content(branchBookDtoStr))
        .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    public void testGetBranchBooks() throws Exception {
        BranchBookAddDto branchBookAddDto = new BranchBookAddDto();
        branchBookAddDto.setId(1);
        branchBookAddDto.setQuantity(1);
        branchBookAddDto.setTitle("Test");
        BranchBookDto branchBookDto = new BranchBookDto();
        Set<BranchBookAddDto> branchBookAddDtos = new HashSet<>();
        branchBookAddDtos.add(branchBookAddDto);
        branchBookDto.setBooks(branchBookAddDtos);

        Branch branch = new Branch();
        branch.setId(1);
        branch.setBranchName("TestBranch");
        branch.setCity("Test");
        branch.setEmailId("test@abc.com");
        branch.setContactNumber("+9199293930");
        branchService.addBranch(branch);

        Book book = new Book();
        book.setId(1);
        book.setTitle("Test");
        bookService.addBook(book);

        branchService.addBookToBranch(1, branchBookDto);

        mockMvc.perform(get("/branches/1/books").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
