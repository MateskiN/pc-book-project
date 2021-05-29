package com.nikolamateski.pcbooklibrary.author;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {

    public String firstName;
    public String lastName;
    public Integer yearOfBirth;

    public Author toEntity() {
        return new Author(firstName, lastName, yearOfBirth);
    }
}
