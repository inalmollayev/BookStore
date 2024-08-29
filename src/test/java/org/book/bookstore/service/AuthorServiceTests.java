package org.book.bookstore.service;

import org.book.bookstore.bookRepository.AuthorRepository;
import org.book.bookstore.dto.AuthorDto;
import org.book.bookstore.model.Author;
import org.book.bookstore.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {

    @InjectMocks
    AuthorService authorService;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    ModelMapper mapper;


    private Author author;
    private AuthorDto authorDto;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        author = new Author(1L, "Eden", null);
        authorDto = new AuthorDto("Martin Eden");
        book1 = new Book(1L, "ROMAN", author, 15.50, 3);
        book2 = new Book(2L, "Detective", author, 20.5, 2);
        author.setBooks(List.of(book1, book2));
    }


    @Test
    public void createAuthor_ReturnAuthorDto() {
        when(mapper.map(authorDto, Author.class)).thenReturn(author);
        when(authorRepository.save(author)).thenReturn(author);
        when(mapper.map(author, AuthorDto.class)).thenReturn(authorDto);
        AuthorDto result = authorService.createAuthor(authorDto);
        Assertions.assertNotNull(authorDto);
        assertEquals(authorDto, result);
    }

    @Test
    public void getAllAuthors_returnListAuthorDto() {
        List<Author> authors = List.of(author);
        when(authorRepository.findAll()).thenReturn(authors);
        List<AuthorDto> authorDtos = authorService.getAllAuthor();

        Assertions.assertNotNull(authorDtos);
        assertEquals(authorDtos.size(), authors.size());
    }


    @Test
    public void getAuthorFullById(){
        when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));
        when(mapper.map(author, AuthorDto.class)).thenReturn(authorDto);
        AuthorDto result = authorService.getAuthorById(author.getId());

        Assertions.assertNotNull(authorDto);
        Assertions.assertEquals(authorDto,result);

    }

    @Test
    public void deletedAuthor(){
        when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));
        when(mapper.map(author, AuthorDto.class)).thenReturn(authorDto);
        AuthorDto result = authorService.deleteAuthorById(author.getId());

        Assertions.assertNotNull(authorDto);
        Assertions.assertEquals(authorDto, result);
    }






}
