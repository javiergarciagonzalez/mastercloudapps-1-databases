package es.codeurjc.springrestapiwithdb.dtos.responses;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String nickname;
    private String email;
}
