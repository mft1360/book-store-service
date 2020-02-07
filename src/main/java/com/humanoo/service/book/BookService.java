package com.humanoo.service.book;

import com.humanoo.entity.Book;

import java.util.List;

/**
 * @author R.fatthi
 */
public interface BookService {

    List<Book> findAll();

    Book findById(Long bookId);

    Book create(Book book);

    Book update(Long bookId, Book book);

    void delete(Long bookId);

}
