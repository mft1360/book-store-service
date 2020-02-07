package com.humanoo.mapper;

import com.humanoo.dto.BookAuditDTO;
import com.humanoo.dto.BookDTO;
import com.humanoo.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface BookMapper {

    Book toBook(BookDTO bookDTO);

    BookDTO toBookDTO(Book book);

    BookAuditDTO toBookAuditDTO(Book book);

    default List<BookDTO> toBookDTOs(List<Book> books) {
        return books.stream().map(book -> (toBookDTO(book))).collect(Collectors.toList());
    }
}
