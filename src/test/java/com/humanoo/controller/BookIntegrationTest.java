package com.humanoo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.humanoo.Application;
import com.humanoo.dto.BookDTO;
import com.humanoo.handler.ValidationMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class BookIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;


    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void GetBookById_IsOK() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/api/book/1"))
                .andExpect(status().isOk()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        BookDTO bookDTO = objectMapper.readValue(responseJson, BookDTO.class);
        assertEquals("The author should be martin fowler", "martin fowler", bookDTO.getAuthor());
    }

    @Test
    public void GetBookById_ShouldBeThrowNotFoundException() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/api/book/150"))
                .andExpect(status().isNotFound()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        assertTrue(responseJson != null);
    }

    @Test
    public void when_GetAllBook_IsOK() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/api/book"))
                .andExpect(status().isOk()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        BookDTO[] bookDTOS = objectMapper.readValue(responseJson, BookDTO[].class);
        assertThat(bookDTOS.length).isEqualTo(2);
    }

    @Test
    public void Create_Book_IsOK() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult mvcResult = mvc.perform(post("/api/book").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(BookDTO.builder().bookName("book name").author("author name").isbn("isbn").categories("IT").build())))
                .andExpect(status().isCreated()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        BookDTO bookDTO = objectMapper.readValue(responseJson, BookDTO.class);
        assertTrue(bookDTO.getId() != null);
    }

    @Test
    public void Create_Book_ShouldBeThrowDataIntegrityViolationException() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult mvcResult = mvc.perform(post("/api/book").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(BookDTO.builder().bookName("book name").author("author name").isbn("99921-58-10-7").categories("IT").build())))
                .andExpect(status().isBadRequest()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        assertTrue(responseJson != null);
    }

    @Test
    public void Create_Book_ShouldBeThrowValidationException() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult mvcResult = mvc.perform(post("/api/book").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(BookDTO.builder().build())))
                .andExpect(status().isBadRequest()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        ValidationMessage[] validationMessages = objectMapper.readValue(responseJson, ValidationMessage[].class);
        assertTrue(validationMessages.length == 4);
    }
}
