package com.humanoo.controller;

import com.humanoo.aspect.CheckBindingResult;
import com.humanoo.dto.BookAuditDTO;
import com.humanoo.dto.BookDTO;
import com.humanoo.mapper.BookMapper;
import com.humanoo.service.book.BookService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @GetMapping
    @ApiOperation(response = BookDTO.class, value = "all books")
    public List<BookDTO> getAllBook() {
        LOGGER.debug("start {} with URL {}", "getAllBook", "/api/book");
        List<BookDTO> bookDTOS = bookMapper.toBookDTOs(bookService.findAll());
        LOGGER.debug("executed successfully {} with URL {}", "getAllBook", "/api/book");
        return bookDTOS;
    }

    @GetMapping(value = "/{bookId}")
    @ApiOperation(response = BookDTO.class, value = "find a book By bookId")
    public BookDTO findOne(@PathVariable Long bookId) {
        LOGGER.debug("start {} with URL {}", "findOne", "/api/book/{bookId}");
        BookDTO bookDTO = bookMapper.toBookDTO(bookService.findById(bookId));
        LOGGER.debug("executed successfully {} with URL {}", "findOne", "/api/book/{bookId}");
        return bookDTO;
    }

    @GetMapping(value = "getAuditBook/{bookId}")
    @ApiOperation(response = BookDTO.class, value = "find a book with Audit By bookId")
    public BookAuditDTO getAuditBook(@PathVariable Long bookId) {
        LOGGER.debug("start {} with URL {}", "getAuditBook", "/api/book/getAuditBook/{bookId}");
        BookAuditDTO bookAuditDTO = bookMapper.toBookAuditDTO(bookService.findById(bookId));
        LOGGER.debug("start {} with URL {}", "getAuditBook", "/api/book/getAuditBook/{bookId}");
        return bookAuditDTO;
    }

    @PostMapping
    @ApiOperation(response = BookDTO.class, value = "create a book")
    @ResponseStatus(HttpStatus.CREATED)
    @CheckBindingResult
    public BookDTO createBook(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult) {
        LOGGER.debug("start {} with URL {}", "createBook", "/api/book");
        BookDTO returnBookDTO = bookMapper.toBookDTO(bookService.create(bookMapper.toBook(bookDTO)));
        LOGGER.debug("executed successfully {} with URL {}", "createBook", "/api/book");
        return returnBookDTO;
    }

    @PutMapping(value = "/{bookId}")
    @ApiOperation(response = BookDTO.class, value = "update a book by bookId")
    @CheckBindingResult
    public BookDTO updateBook(@PathVariable Long bookId, @RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult) {
        LOGGER.debug("start {} with URL {}", "updateBook", "/api/book/{bookId}");
        BookDTO returnBookDTO = bookMapper.toBookDTO(bookService.update(bookId, bookMapper.toBook(bookDTO)));
        LOGGER.debug("executed successfully {} with URL {}", "updateBook", "/api/book/{bookId}");
        return returnBookDTO;
    }

    @DeleteMapping(value = "/{bookId}")
    @ApiOperation(value = "delete a book by bookId")
    public void deleteBook(@PathVariable Long bookId) {
        LOGGER.debug("start {} with URL {}", "deleteBook", "/api/book/{bookId}");
        bookService.delete(bookId);
        LOGGER.debug("executed successfully {} with URL {}", "deleteBook", "/api/book/{bookId}");
    }

}
