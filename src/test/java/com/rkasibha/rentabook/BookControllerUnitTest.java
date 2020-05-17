package com.rkasibha.rentabook;

import com.rkasibha.rentabook.controller.BookController;
import com.rkasibha.rentabook.dto.BookDto;
import com.rkasibha.rentabook.model.Book;
import com.rkasibha.rentabook.service.BookService;
import com.rkasibha.rentabook.service.BranchService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(BookController.class)
public class BookControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        Mockito.when(bookService.getAllBooks()).thenReturn(books);

        BookDto mockBookDto = new BookDto();
        Mockito.when(modelMapper.map(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).thenReturn(mockBookDto);

        mockMvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
    }
}
