package com.rkasibha.rentabook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkasibha.rentabook.controller.BookController;
import com.rkasibha.rentabook.controller.EntityDtoMapper;
import com.rkasibha.rentabook.dto.BookDto;
import com.rkasibha.rentabook.exception.DataNotFoundException;
import com.rkasibha.rentabook.model.Book;
import com.rkasibha.rentabook.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(BookController.class)
public class BookControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private EntityDtoMapper entityDtoMapper;

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        Mockito.when(bookService.getAllBooks()).thenReturn(books);

        BookDto mockBookDto = new BookDto();
        Mockito.when(entityDtoMapper.convertBookToBookDto(org.mockito.ArgumentMatchers.any())).thenReturn(mockBookDto);

        mockMvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));

        Mockito.when(bookService.getAllBooks()).thenReturn(null);
        mockMvc.perform(get("/books"))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testAddBook() throws Exception {
        Book bookToAdd = new Book();
        bookToAdd.setTitle("Test");

        BookDto bookDtoToAdd = new BookDto();
        bookDtoToAdd.setTitle("Test");

        Book addedBook = new Book();
        addedBook.setId(1);
        addedBook.setTitle("Test");

        BookDto returnedBookDto = new BookDto();
        returnedBookDto.setId(1);
        returnedBookDto.setTitle("Test");

        Mockito.when(bookService.addBook(org.mockito.ArgumentMatchers.any())).thenReturn(addedBook);
        Mockito.when(entityDtoMapper.convertBookDtoToBook(bookDtoToAdd)).thenReturn(bookToAdd);
        Mockito.when(entityDtoMapper.convertBookToBookDto(addedBook)).thenReturn(returnedBookDto);

        ObjectMapper mapper = new ObjectMapper();
        String mockBookDtoToAddStr = mapper.writeValueAsString(returnedBookDto);
        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content(mockBookDtoToAddStr))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book returnedBook = new Book();
        returnedBook.setId(1);

        BookDto returnedBookDto = new BookDto();
        returnedBookDto.setId(1);

        Mockito.when(bookService.getBookById(1)).thenReturn(returnedBook);
        Mockito.when(entityDtoMapper.convertBookToBookDto(returnedBook)).thenReturn(returnedBookDto);

        mockMvc.perform(get("/books/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

        Mockito.when(bookService.getBookById(2)).thenThrow(DataNotFoundException.class);
        mockMvc.perform(get("/books/2"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetBookByTitle() throws Exception {
        String testTitle = "Test";
        Book foundBook = new Book();
        foundBook.setTitle("Test");
        BookDto returnedBookDto = new BookDto();
        returnedBookDto.setTitle("Test");

        Mockito.when(bookService.getBookByTitle(testTitle)).thenReturn(foundBook);
        Mockito.when(entityDtoMapper.convertBookToBookDto(foundBook)).thenReturn(returnedBookDto);

        mockMvc.perform(get("/books?title=Test"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test"));

        Mockito.when(bookService.getBookByTitle("Error")).thenThrow(DataNotFoundException.class);
        mockMvc.perform(get("/books?title=Error"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
