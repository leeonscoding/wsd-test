package com.leeonscoding.wsdtest.payloads;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record RegisterUserDTO(@Nonnull
                              @Email
                              String email,
                              @Nonnull
                              @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\n")
                              String password) {
}
