package com.example.gerenciador.dtos;

import com.example.gerenciador.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TarefaRecordDto(@NotBlank String descrtiption, @NotNull Usuario usuario) {
}
