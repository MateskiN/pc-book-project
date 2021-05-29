package com.nikolamateski.pcbooklibrary.author;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AuthorDTO {

    public Integer id;
    public String firstName;
    public String lastName;
    public Integer yearOfBirth;
}
