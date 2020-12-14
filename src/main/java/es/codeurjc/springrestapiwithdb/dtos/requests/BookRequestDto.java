package es.codeurjc.springrestapiwithdb.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDto {

    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Summary is mandatory")
    private String summary;
    @NotBlank(message = "Author is mandatory")
    private String author;
    @NotBlank(message = "Publisher is mandatory")
    private String publisher;
    @NotBlank(message = "PublicationYear is mandatory")
    private int publicationYear;

}
