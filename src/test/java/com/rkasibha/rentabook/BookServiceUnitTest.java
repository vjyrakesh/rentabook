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

    @Test
    public void testAddBook() {
        Book bookToAdd = new Book();
        bookToAdd.setTitle("Test Title");

        Book addedBook = new Book();
        addedBook.setId(1);
        addedBook.setTitle("Test Title");

        Mockito.when(bookRepository.save(bookToAdd)).thenReturn(addedBook);
        Book returnedBook = bookService.addBook(bookToAdd);
        assertThat(returnedBook.getId()).isEqualTo(1);
        assertThat(returnedBook.getTitle()).isEqualTo("Test Title");
    }

    @Test
    public void testGetBookById() {
        Book returnedBook = new Book();
        returnedBook.setId(1);

        Mockito.when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(returnedBook));

        assertThat(bookService.getBookById(1).getId()).isEqualTo(1);
    }
}
