package com.senai.eventsmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
         @NotBlank(message = "Senha não pode ser vazia")
         String senha;
         @NotBlank(message = "Email não pode ser vazio")
         String email;
}
