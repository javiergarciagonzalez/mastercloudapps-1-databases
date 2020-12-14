package es.codeurjc.springrestapiwithdb.repositories.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import es.codeurjc.springrestapiwithdb.models.Comment;
import es.codeurjc.springrestapiwithdb.repositories.CommentRepository;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private ConcurrentHashMap<Long, ConcurrentHashMap<Long, Comment>> comments = new ConcurrentHashMap<>();
    private AtomicLong commentsSequence = new AtomicLong();

    public CommentRepositoryImpl() {
        this.save(new Comment(1L, null, "User 1", "Book 2 comment 1", 3));
        this.save(new Comment(1L, null, "User 2", "Book 2 comment 2", 4));
    }

    public Collection<Comment> findByBookId(long bookId) {
        ConcurrentHashMap<Long, Comment> bookComments = this.comments.get(bookId);
        if (bookComments == null) {
            return List.of();
        }
        return this.comments.get(bookId).values();
    }

    public Comment save(Comment comment) {
        comment.setId(this.commentsSequence.getAndIncrement());
        ConcurrentHashMap<Long, Comment> bookComments = this.comments.get(comment.getBookId());
        if (bookComments == null) {
            bookComments = new ConcurrentHashMap<>();
            this.comments.put(comment.getBookId(), bookComments);
        }
        bookComments.put(comment.getId(), comment);
        return comment;
    }

    public Optional<Comment> findByBookIdAndId(long bookId, long commentId) {
        Comment comment = null;
        ConcurrentHashMap<Long, Comment> bookComments = this.comments.get(bookId);
        if (bookComments != null) {
            comment = bookComments.get(commentId);
        }
        return Optional.ofNullable(comment);
    }

    public Comment delete(Comment comment) {
        return comments.get(comment.getBookId()).remove(comment.getId());
    }

}
