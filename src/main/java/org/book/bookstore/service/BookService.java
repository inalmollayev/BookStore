package org.book.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.book.bookstore.bookRepository.AuthorRepository;
import org.book.bookstore.bookRepository.BookRepository;
import org.book.bookstore.model.Author;
import org.book.bookstore.model.Book;
import org.book.bookstore.dto.BookDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final ModelMapper mapper;

    public BookDto createBook(BookDto bookDto) {
        Author author = getOrAddAuthor(bookDto);
        Book book = bookRepository.findByIsbn(bookDto.getIsbn());
        if (book == null) {
            book = mapper.map(bookDto, Book.class);
            book.setAuthor(author);
            book = bookRepository.save(book);
        }

        return mapper.map(book, BookDto.class);
    }


    public Author getOrAddAuthor(BookDto bookDto) {
        String name = bookDto.getAuthor().getFullName();
        Author author = authorRepository.findByFullName(name);
        if (author == null) {
            author = mapper.map(bookDto.getAuthor(), Author.class);
            author = authorRepository.save(author);
        }
        return author;
    }

    public List<BookDto> getAllBooks() {
        List<BookDto> bookDtos = new ArrayList<>();
        bookRepository.findAll().forEach(book -> {
            bookDtos.add(mapper.map(book, BookDto.class));
            log.info("Book found: {}", book);
        });
        return bookDtos;
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return mapper.map(book, BookDto.class);
    }


    public List<BookDto> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findBookByAuthor_FullName(author);
        List<BookDto> bookDtos = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            bookDtos.add(mapper.map(books.get(i), BookDto.class));

        }
        return bookDtos;
    }


    public BookDto deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        bookRepository.delete(book);

        return mapper.map(book, BookDto.class);
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Author author = getOrAddAuthor(bookDto);
            Book mapped = mapper.map(bookDto, Book.class);
            mapped.setId(id);
            mapped.setAuthor(author);
            log.info("Update book {} {}", id, bookDto);
            return mapper.map(bookRepository.save(mapped), BookDto.class);
        } else {
            log.info("Not found");
            return new BookDto();
        }

    }
}
