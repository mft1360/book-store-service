package com.humanoo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.humanoo.dto.BookDTO;
import com.humanoo.entity.Book;
import com.humanoo.mapper.BookMapper;
import com.humanoo.service.book.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class BookAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookMapper bookMapper;

    @Test
    public void when_GetBookById() throws Exception {
        Book book = Book.builder().id((long) 1).bookName("microservice").author("martin").build();
        BookDTO bookDTO = BookDTO.builder().id((long) 1).bookName("microservice").author("martin").build();
        doReturn(book).when(bookService).findById(any(Long.class));
        doReturn(bookDTO).when(bookMapper).toBookDTO(any(Book.class));
        this.mockMvc.perform(get("/api/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookName", is(book.getBookName())));
    }

    @Test
    public void when_GetAllBook() throws Exception {
        List<Book> books = Arrays.asList(Book.builder().id((long) 1).bookName("microservice").author("martin").build());
        List<BookDTO> bookDTOs = Arrays.asList(BookDTO.builder().id((long) 1).bookName("microservice").author("martin").build());
        doReturn(books).when(bookService).findAll();
        doReturn(bookDTOs).when(bookMapper).toBookDTOs(any());
        this.mockMvc.perform(get("/api/book"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookName", is(books.get(0).getBookName())));
    }

    @Test
    public void when_Create_Book() throws Exception {
        Book book = Book.builder().id((long) 1).bookName("microservice").author("martin").build();
        doReturn(book).when(bookMapper).toBook(any(BookDTO.class));
        doReturn(book).when(bookService).create(any(Book.class));
        BookDTO bookDTO = BookDTO.builder().id((long) 1).bookName("microservice").author("martin").build();
        doReturn(bookDTO).when(bookMapper).toBookDTO(any(Book.class));
        ObjectMapper objectMapper = new ObjectMapper();
        this.mockMvc.perform(post("/api/book").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookName", is(book.getBookName())));
    }
}
