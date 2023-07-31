package com.example.gerenciador.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRecordDto(@NotBlank String name,@NotBlank String email, @NotNull String password) {
}
