package com.humanoo.service;

import com.humanoo.entity.Book;
import com.humanoo.exception.EntityNotFoundException;
import com.humanoo.repository.BookRepository;
import com.humanoo.service.book.DefaultBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private DefaultBookService defaultBookService;

    @Test
    public void findOneBookUnitTest_ok() {
        Book book = Book.builder().id((long) 100).bookName("design").author("martin").build();
        Optional<Book> bookOption = Optional.of(book);
        doReturn(bookOption).when(bookRepository).findById(any(Long.class));
        Book finalBook = defaultBookService.findById((long) 152);
        assertTrue(finalBook.getId() == book.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findOneBookUnitTest_ShouldBeThrowException() {
        Optional<Book> bookOption = Optional.empty();
        doReturn(bookOption).when(bookRepository).findById(any(Long.class));
        Book finalBook = defaultBookService.findById((long) 152);
        assertTrue(finalBook.getId() != null);
    }

    @Test
    public void findAllBookUnitTest_ok() {
        List<Book> books = Arrays.asList(Book.builder().id((long) 100).bookName("design").author("martin").build());
        doReturn(books).when(bookRepository).findAll();
        List<Book> finalBooks = defaultBookService.findAll();
        assertTrue(finalBooks == books);
    }

    @Test
    public void createBookUnitTest_ok() {
        Book book = Book.builder().bookName("design").author("martin").build();
        doReturn(book).when(bookRepository).save(any(Book.class));
        Book finalBook = defaultBookService.create(book);
        assertTrue(book == finalBook);
    }
}
