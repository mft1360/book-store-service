package com.humanoo.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * @author R.fatthi
 */
@Data
@SuperBuilder
@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(name = "uc_isbn", columnNames = {"isbn"}))
public class Book extends BaseEntity<Long> {

    public Book() {
    }

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String categories;

}
