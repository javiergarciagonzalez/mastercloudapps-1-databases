package es.codeurjc.springrestapiwithdb.models;

import java.util.Collection;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String summary;
    private String author;
    private String publisher;
    private int publicationYear;
    private @Builder.Default Collection<Comment> comments = List.of();

    public float getScore() {
        float score = 0;
        for (Comment comment : this.comments) {
            score += comment.getScore();
        }
        return this.comments.isEmpty() ? score : score / this.comments.size();
    }

}
