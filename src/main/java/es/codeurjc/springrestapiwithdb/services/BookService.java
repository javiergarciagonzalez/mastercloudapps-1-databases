package es.codeurjc.springrestapiwithdb.services;

import java.util.Collection;

import es.codeurjc.springrestapiwithdb.dtos.requests.BookRequestDto;
import es.codeurjc.springrestapiwithdb.dtos.requests.CommentRequestDto;
import es.codeurjc.springrestapiwithdb.dtos.responses.BookDetailsResponseDto;
import es.codeurjc.springrestapiwithdb.dtos.responses.BookResponseDto;
import es.codeurjc.springrestapiwithdb.dtos.responses.CommentResponseDto;

public interface BookService {

    Collection<BookResponseDto> findAll();

    BookDetailsResponseDto save(BookRequestDto bookRequestDto);

    BookDetailsResponseDto findById(long bookId);

    CommentResponseDto addComment(long bookId, CommentRequestDto commentRequestDto);

    CommentResponseDto deleteComment(long bookId, long commentId);
}
