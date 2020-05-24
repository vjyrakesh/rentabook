package com.rkasibha.rentabook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkasibha.rentabook.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetBookById() throws Exception {
        BookDto bookDtoToAdd = new BookDto();
        bookDtoToAdd.setTitle("Test");

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bookDtoToAdd)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test"));

        mockMvc.perform(get("/books/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test"));
    }
}
