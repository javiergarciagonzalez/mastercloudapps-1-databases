package es.codeurjc.springrestapiwithdb.repositories;

import java.util.Collection;
import java.util.Optional;

import es.codeurjc.springrestapiwithdb.models.Book;

public interface BookRepository {


    Collection<Book> findAll();

    Book save(Book book);

    Optional<Book> findById(Long bookId);

}
