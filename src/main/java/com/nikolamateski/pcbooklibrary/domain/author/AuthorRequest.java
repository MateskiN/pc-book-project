package com.nikolamateski.pcbooklibrary.domain.author;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {

    @NotNull(message = "Please enter first name for Author!")
    @NotBlank
    public String firstName;

    @NotNull(message = "Please enter last name for Author!")
    @NotBlank
    public String lastName;

    @NotNull(message = "Please enter year of birth for Author!")
    public Integer yearOfBirth;

    public Author toEntity() {
        return new Author(firstName, lastName, yearOfBirth);
    }
}
