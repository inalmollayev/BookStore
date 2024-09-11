package org.book.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.book.bookstore.bookRepository.AuthorRepository;
import org.book.bookstore.dto.AuthorDto;
import org.book.bookstore.model.Author;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class AuthorService {
    private static final Logger log = LoggerFactory.getLogger(AuthorService.class);
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        author = authorRepository.save(author);
        return modelMapper.map(author, AuthorDto.class);
    }


    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> authorDtos = new ArrayList<>();
        for (Author author : authors) {
            authorDtos.add(modelMapper.map(author, AuthorDto.class));
        }
        return authorDtos;
    }


    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        log.info("Author found: {}", author);
        return modelMapper.map(author, AuthorDto.class);
    }


    public AuthorDto deleteAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        authorRepository.delete(author);
        log.info("Author deleted: {}", author);
        return modelMapper.map(author, AuthorDto.class);
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        Optional<Author> byId = authorRepository.findById(id);
        if (byId.isPresent()) {
            Author author = modelMapper.map(authorDto, Author.class);
            author.setId(id);
            log.info("Update author {} {}", id, authorDto);
            return modelMapper.map(authorRepository.save(author), AuthorDto.class);

        }
        return new AuthorDto();
    }



}