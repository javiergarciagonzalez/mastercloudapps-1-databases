package es.codeurjc.springrestapiwithdb.dtos.responses;

import lombok.Data;

@Data
public class CommentResponseDto {

    private Long id;
    private String user;
    private String comment;
    private float score;

}
