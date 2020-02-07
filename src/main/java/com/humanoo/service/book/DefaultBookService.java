package com.humanoo.service.book;

import com.humanoo.exception.BusinessException;
import com.humanoo.exception.EntityNotFoundException;
import com.humanoo.repository.BookRepository;
import com.humanoo.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some Book specific things.
 *
 * @author R.fatthi
 * <p/>
 */
@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;


    /**
     * get all of records in data base
     */
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + bookId));
    }

    /**
     * create a book
     *
     * @param book
     */
    @Override
    public Book create(Book book) {
        if (book.getId() != null) {
            throw new BusinessException("ID must be null");
        }
        return bookRepository.save(book);
    }

    /**
     * update a book by bookId
     *
     * @param bookId,book
     */
    @Override
    public Book update(Long bookId, Book book) {
        findById(bookId);
        book.setId(bookId);
        return bookRepository.save(book);
    }

    /**
     * delete by bookId
     *
     * @param bookId
     */
    @Override
    public void delete(Long bookId) {
        bookRepository.delete(findById(bookId));
    }
}
