package org.book.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.book.bookstore.service.AuthorService;
import org.book.bookstore.dto.AuthorDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/authorRoom")
@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/save")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.createAuthor(authorDto);
    }

    @GetMapping("getAllAuthors")
    public List<AuthorDto> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("getAuthorById")
    public AuthorDto getAuthorById(@RequestParam Long id){
        return authorService.getAuthorById(id);
    }

    @DeleteMapping("deleteAuthorById")
    public AuthorDto deleteAuthorById(@RequestParam Long id){
        return authorService.deleteAuthorById(id);
    }

    @PutMapping("updateAuthor")
    public AuthorDto updateAuthor(Long id, AuthorDto authorDto){
        return authorService.updateAuthor(id, authorDto);
    }



}
