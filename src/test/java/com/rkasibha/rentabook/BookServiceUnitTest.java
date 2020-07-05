package com.rkasibha.rentabook;

import com.rkasibha.rentabook.exception.DataNotFoundException;
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
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void testGetBookById() throws DataNotFoundException {
        Book returnedBook = new Book();
        returnedBook.setId(1);

        Mockito.when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(returnedBook));
        assertThat(bookService.getBookById(1).getId()).isEqualTo(1);

        Mockito.when(bookRepository.findById(2)).thenReturn(java.util.Optional.empty());
        DataNotFoundException dataNotFoundException = assertThrows(DataNotFoundException.class, () -> {
            bookService.getBookById(2);
        });
        assertThat(dataNotFoundException.getMessage()).isEqualTo("Book with id: 2 not found");

    }

    @Test
    public void testGetBookByTitle() throws DataNotFoundException {
        Book returnedBook = new Book();
        returnedBook.setTitle("Test");

        Mockito.when(bookRepository.findBookByTitle("Test")).thenReturn(returnedBook);
        assertThat(bookService.getBookByTitle("Test").getTitle()).isEqualTo("Test");

        Mockito.when(bookRepository.findBookByTitle("Error")).thenReturn(null);
        DataNotFoundException dataNotFoundException = assertThrows(DataNotFoundException.class, () -> {
           bookService.getBookByTitle("Error");
        });
        assertThat(dataNotFoundException.getMessage()).isEqualTo("Book with title: Error not found");
    }
}
