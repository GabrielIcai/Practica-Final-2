package edu.comillas.icai.gitt.pat.spring.jpa.Model;

import jakarta.validation.constraints.Pattern;

public record ProfileRequest(
    String name,
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$")
    String password
) {}
