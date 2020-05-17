package com.rkasibha.rentabook;

import com.rkasibha.rentabook.model.Book;
import com.rkasibha.rentabook.repository.BookRepository;
import com.rkasibha.rentabook.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class BookServiceUnitTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testGetAllBooks() {
        List<Book> repoBooks = new ArrayList<>();
        Mockito.when(bookRepository.findAll()).thenReturn(repoBooks);

        List<Book> books = bookService.getAllBooks();
        assertThat(books.size()).isEqualTo(0);
    }
}
