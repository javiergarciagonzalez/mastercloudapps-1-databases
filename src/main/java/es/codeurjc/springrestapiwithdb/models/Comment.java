package es.codeurjc.springrestapiwithdb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Long bookId;
    private Long id;
    private String user;
    private String comment;
    private float score;
}
