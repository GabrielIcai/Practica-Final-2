package edu.comillas.icai.gitt.pat.spring.jpa.Model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterRequest(
    @NotBlank
    String name,
    @NotBlank @Email
    String email,
    @NotNull
    Integer piso,
    String letra,
    // Patrón: al menos una mayúscula, una minúscula, y un número, y de longitud más de 7
    @NotBlank @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$")
    String password
) {}
