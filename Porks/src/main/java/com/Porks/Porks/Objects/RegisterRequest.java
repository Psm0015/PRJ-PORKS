package com.Porks.Porks.Objects;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nome;
    private String email;
    private String cpf;
    private Date nascimento;
    private String senha;
}
