package org.book.bookstore.controller;

import lombok.RequiredArgsConstructor;

import org.book.bookstore.service.BookService;
import org.book.bookstore.dto.BookDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookRoom")
public class BookController {
    private final BookService bookService;

    @PostMapping("/createB")
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }


}
