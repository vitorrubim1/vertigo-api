package br.com.fiap.vertigo.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "O campo email não pode estar vazio.")
    @Email(message = "Endereço de e-mail inválido.")
    private String email;

    @NotBlank(message = "O campo senha não pode estar vazio.")
    private String senha;
}