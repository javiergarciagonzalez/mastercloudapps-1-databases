package es.codeurjc.springrestapiwithdb.repositories.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import es.codeurjc.springrestapiwithdb.models.Book;
import es.codeurjc.springrestapiwithdb.repositories.BookRepository;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private ConcurrentHashMap<Long, Book> books = new ConcurrentHashMap<>();
    private AtomicLong booksSequence = new AtomicLong();

    public BookRepositoryImpl() {
        this.save(Book.builder()
                .title("Book 1 title")
                .summary("Book 1 summary")
                .author("Book 1 author")
                .publisher("Book 1 publisher")
                .publicationYear(1999)
                .build());

        this.save(Book.builder()
                .title("Book 2 title")
                .summary("Book 2 summary")
                .author("Book 2 author")
                .publisher("Book 2 publisher")
                .publicationYear(2012)
                .build());
    }

    public Collection<Book> findAll() {
        return this.books.values();
    }

    public Book save(Book book) {
        book.setId(this.booksSequence.getAndIncrement());
        this.books.put(book.getId(), book);
        return book;
    }

    public Optional<Book> findById(Long bookId) {
        return Optional.ofNullable(this.books.get(bookId));
    }

}
