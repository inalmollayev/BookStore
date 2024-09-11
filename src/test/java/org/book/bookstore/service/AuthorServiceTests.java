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
    public void createAuthor_returnAuthorDto(){
        when( mapper.map(authorDto, Author.class) ).thenReturn(author);
        when(authorRepository.save(author) ).thenReturn(author);
        when(mapper.map(author, AuthorDto.class) ).thenReturn(authorDto);

        Assertions.assertNotNull(authorDto);
        Assertions.assertEquals(authorDto, authorService.createAuthor(authorDto));

    }

    @Test
    public void getAllAuthors_returnListAuthorDto(){
        List<Author> authors = List.of(author);
        when(authorRepository.findAll()).thenReturn(authors);
        List<AuthorDto> authorDtos = authorService.getAllAuthors();

        Assertions.assertNotNull(authorDtos);
        Assertions.assertEquals(authorDtos.size(), authors.size());
    }

    @Test
    public void getAuthorById_returnAuthorDto(){
        Long authorId = 1L;
        when(authorRepository.findById(authorId)).thenReturn(Optional.ofNullable(author));
        when(mapper.map(author, AuthorDto.class) ).thenReturn(authorDto);

        Assertions.assertNotNull(authorDto);
        Assertions.assertEquals(authorDto, authorService.getAuthorById(authorId));

    }

    @Test
    public void deleteAuthorById_returnAuthorDto(){
        Long authorId = 1L;
        when(authorRepository.findById(authorId)).thenReturn(Optional.ofNullable(author));
        when(mapper.map(author, AuthorDto.class) ).thenReturn(authorDto);

        Assertions.assertNotNull(author);
        Assertions.assertEquals(authorDto, authorService.deleteAuthorById(authorId));
    }

    @Test
    public void updateAuthorIfNullTest(){
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());
        AuthorDto serviceAuthorDto = authorService.updateAuthor(1L, authorDto);
        Assertions.assertEquals(serviceAuthorDto,new AuthorDto());
    }





}
