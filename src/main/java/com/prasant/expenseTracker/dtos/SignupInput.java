package com.prasant.expenseTracker.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupInput {
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Name must start with an uppercase letter")
    @NotEmpty
    private String firstName;
    private String lastName;
    @Email
    @NotEmpty
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\S]{8,20}$", message = "Password length between 8 and 20 characters." +
            " At least one uppercase letter." +
            " At least one lowercase letter." +
            " At least one digit." +
            " Can include special characters.")
    @NotEmpty
    private String password;
}
