package es.codeurjc.springrestapiwithdb.services.impl;
import java.util.Collection;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import es.codeurjc.springrestapiwithdb.dtos.requests.BookRequestDto;
import es.codeurjc.springrestapiwithdb.dtos.requests.CommentRequestDto;
import es.codeurjc.springrestapiwithdb.dtos.responses.BookDetailsResponseDto;
import es.codeurjc.springrestapiwithdb.dtos.responses.BookResponseDto;
import es.codeurjc.springrestapiwithdb.dtos.responses.CommentResponseDto;
import es.codeurjc.springrestapiwithdb.exceptions.BookNotFoundException;
import es.codeurjc.springrestapiwithdb.exceptions.CommentNotFoundException;
import es.codeurjc.springrestapiwithdb.models.Book;
import es.codeurjc.springrestapiwithdb.models.Comment;
import es.codeurjc.springrestapiwithdb.repositories.BookRepository;
import es.codeurjc.springrestapiwithdb.repositories.CommentRepository;
import es.codeurjc.springrestapiwithdb.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    private Mapper mapper;
    private BookRepository bookRepository;
    private CommentRepository commentRepository;

    public BookServiceImpl(Mapper mapper, BookRepository bookRepository, CommentRepository commentRepository) {
        this.mapper = mapper;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    public Collection<BookResponseDto> findAll() {
        return this.bookRepository.findAll().stream()
                .map(book -> this.mapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
    }

    public BookDetailsResponseDto save(BookRequestDto bookRequestDto) {
        Book book = this.mapper.map(bookRequestDto, Book.class);
        book = this.bookRepository.save(book);
        return this.mapper.map(book, BookDetailsResponseDto.class);
    }

    public BookDetailsResponseDto findById(long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        book.setComments(this.commentRepository.findByBookId(bookId));
        return this.mapper.map(book, BookDetailsResponseDto.class);
    }

    public CommentResponseDto addComment(long bookId, CommentRequestDto commentRequestDto) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        Comment comment = this.mapper.map(commentRequestDto, Comment.class);
        comment.setBookId(book.getId());
        comment = this.commentRepository.save(comment);
        return this.mapper.map(comment, CommentResponseDto.class);
    }

    public CommentResponseDto deleteComment(long bookId, long commentId) {
        this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        Comment comment = this.commentRepository.findByBookIdAndId(bookId, commentId)
                .orElseThrow(CommentNotFoundException::new);
        comment = this.commentRepository.delete(comment);
        return this.mapper.map(comment, CommentResponseDto.class);
    }

}
