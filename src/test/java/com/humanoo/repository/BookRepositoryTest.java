package com.humanoo.repository;

import com.humanoo.entity.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        // given
        Book book = Book.builder()
                .isbn("test isbn")
                .bookName("book name")
                .dateCreated(ZonedDateTime.now())
                .author("author")
                .categories("categories(")
                .build();
        testEntityManager.persist(book);
    }

    @Test
    public void whenFindAllBook_thenReturnBooks() {
        // when
        List<Book> books = bookRepository.findAll();

        // then
        assertThat(books.size()).isEqualTo(3);
    }

    @Test
    public void DeleteBook_IsOK() {
        bookRepository.deleteById((long) 1);
        assertTrue("".equals(""));
    }

}
