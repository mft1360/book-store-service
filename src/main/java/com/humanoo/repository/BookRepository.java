package com.humanoo.repository;

import com.humanoo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Database Access Object for Book table.
 *
 * @author R.fatthi
 * <p/>
 */
public interface BookRepository extends JpaRepository<Book, Long> {

}
