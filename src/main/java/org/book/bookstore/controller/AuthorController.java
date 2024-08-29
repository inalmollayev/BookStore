package org.book.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.book.bookstore.service.AuthorService;
import org.book.bookstore.dto.AuthorDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/authorRoom")
@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/save")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createAuthor(authorDto);
    }


}
