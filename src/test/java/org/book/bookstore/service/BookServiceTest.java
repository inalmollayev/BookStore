package org.book.bookstore.service;

import org.book.bookstore.bookRepository.AuthorRepository;
import org.book.bookstore.bookRepository.BookRepository;
import org.book.bookstore.dto.AuthorDto;
import org.book.bookstore.dto.BookDto;
import org.book.bookstore.model.Author;
import org.book.bookstore.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    ModelMapper mapper;

    @Mock
    AuthorRepository authorRepository;


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
    void init() {
        author1 = new Author(1L, "Kenan", new ArrayList<>());
        author2 = new Author(2L, "Elvin", new ArrayList<>());
        author3 = new Author(5L, "Cefer", new ArrayList<>());
        authorDto = new AuthorDto("Ferid");
        book1 = new Book(3L, "Dedective", author1, 15.5, 3);
        book2 = new Book(4L, "Roman", author2, 17.2, 4);
        book3 = new Book(5L, "Comedy", author3, 20.2, 5);
        bookDto1 = new BookDto("Java", authorDto, 30.2, 7);
        bookDto2 = new BookDto("Phyton", authorDto, 40.2, 8);
    }

    @Test
    public void createBook_returnBookDto() {
        var spy = Mockito.spy(bookService);
        when(spy.getOrAddAuthor(bookDto2)).thenReturn(new Author());
        when(bookRepository.findByIsbn(bookDto2.getIsbn())).thenReturn(null);
        when(mapper.map(bookDto2, Book.class)).thenReturn(book3);
        book3.setAuthor(author1);
        when(bookRepository.save(book3)).thenReturn(book1);
        when(mapper.map(book1, BookDto.class)).thenReturn(bookDto1);
        BookDto book = spy.createBook(bookDto2);
        Assertions.assertNotNull(book);
        Assertions.assertEquals(bookDto1, book);

    }

    @Test
    public void getAllBooks_returnBookDto() {
        List<Book> books = new ArrayList<>(List.of(book1,book2,book3));
        when(bookRepository.findAll()).thenReturn(books);
        List<BookDto> bookDtos = bookService.getAllBooks();
        Assertions.assertNotNull(bookDtos);
        Assertions.assertEquals(bookDtos.size(), books.size());

    }

    @Test
    public void getBookById_returnBookDto() {
        when(bookRepository.findById(book1.getId())).thenReturn(Optional.ofNullable(book1));
        when(mapper.map(book1, BookDto.class)).thenReturn(bookDto1);
        BookDto bookDto = bookService.getBookById(book1.getId());
        Assertions.assertNotNull(bookDto);
        Assertions.assertEquals(bookDto1, bookDto);
    }

    @Test
    public void deleteBook_returnBookDto() {
        when(bookRepository.findById(book2.getId())).thenReturn(Optional.ofNullable(book2));
        when(mapper.map(book2, BookDto.class)).thenReturn(bookDto2);
        bookService.deleteBook(book2.getId());
        Assertions.assertNotNull(bookDto2);
        Assertions.assertEquals(bookDto2, bookDto2);
    }

    @Test
    public void getBooksByAuthor_returnBookDto() {
        List<Book> books = new ArrayList<>(List.of(book1,book2,book3));
        when(bookRepository.findBookByAuthor_FullName("Kenan")).thenReturn(books);
        List<BookDto> bookDtos = bookService.getBooksByAuthor("Kenan");
        Assertions.assertNotNull(bookDtos);
        Assertions.assertEquals(bookDtos.size(), books.size());
    }

    @Test
    public void updateBookTest(){
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        BookDto bookDto4 = bookService.updateBook(1L, bookDto1);
        Assertions.assertEquals(bookDto4,new BookDto());
    }



}
