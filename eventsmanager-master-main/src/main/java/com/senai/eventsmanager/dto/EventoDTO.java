package com.senai.eventsmanager.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.senai.eventsmanager.Enums.EventoEnum;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {
     
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "O nome deve ser preenchido")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres")
    private String nome;

    @NotBlank(message = "A descrição deve ser preenchida")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "O tipo deve ser preenchido")
    private EventoEnum tipo;

    @NotBlank(message = "O local deve ser preenchido")
    @Size(max = 150, message = "O local deve ter no maximo 150 caracteres")
    private String local;

    @NotNull(message = "A data deve ser preenchida")
    private String dataInicio;

    @NotNull(message = "A data de finalização deve ser preenchida")
    private String dataFinal;


    private String linkEvento;
    private String linkImagem;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;

}
