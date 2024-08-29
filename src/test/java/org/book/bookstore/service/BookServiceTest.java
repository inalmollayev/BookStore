package org.book.bookstore.service;

import org.book.bookstore.bookRepository.BookRepository;
import org.book.bookstore.dto.AuthorDto;
import org.book.bookstore.dto.BookDto;
import org.book.bookstore.model.Author;
import org.book.bookstore.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    ModelMapper mapper;

    private Author author1;
    private Author author2;
    private Author author3;
    private AuthorDto authorDto;
    private Book book1;
    private Book book2;
    private Book book3;
    private BookDto bookDto1;
    private BookDto bookDto2;


    @BeforeEach
    void init (){
        author1 = new Author(1L, "Kenan", new ArrayList<>());
        author2 = new Author(2L, "Elvin", new ArrayList<>());
        author3 = new Author(5L, "Cefer", new ArrayList<>());
        authorDto = new AuthorDto("Ferid");
        book1 = new Book(3L, "Dedective", author1, 15.5,3);
        book2 = new Book(4L, "Roman", author2, 17.2, 4);
        book3 = new Book(5L, "Comedy", author3 , 20.2, 5);
        bookDto1 = new BookDto("Java", authorDto, 30.2, 7);
        bookDto2 = new BookDto("Phyton", authorDto , 40.2, 8);
    }



}
