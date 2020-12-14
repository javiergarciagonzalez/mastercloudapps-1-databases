package es.codeurjc.springrestapiwithdb.repositories;

import java.util.Collection;
import java.util.Optional;

import es.codeurjc.springrestapiwithdb.models.Comment;

public interface CommentRepository {

    Collection<Comment> findByBookId(long bookId);

    Comment save(Comment comment);

    Optional<Comment> findByBookIdAndId(long bookId, long commentId);

    Comment delete(Comment comment);

}
