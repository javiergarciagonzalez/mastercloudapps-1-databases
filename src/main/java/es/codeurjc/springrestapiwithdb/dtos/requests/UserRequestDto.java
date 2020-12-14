package es.codeurjc.springrestapiwithdb.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "Nickname is mandatory")
    private String nickname;
    @NotBlank(message = "Email is mandatory")
    private String email;
}
