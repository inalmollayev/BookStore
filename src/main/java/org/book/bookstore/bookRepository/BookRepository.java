package org.book.bookstore.bookRepository;

import org.book.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByIsbn(int isbn);
    List<Book> findBookByAuthor_FullName(String fullName);
}
