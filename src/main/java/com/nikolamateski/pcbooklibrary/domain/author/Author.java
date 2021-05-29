package com.nikolamateski.pcbooklibrary.domain.author;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "author")
@ToString
public class Author {

    @Id
    @GeneratedValue
    public Integer id;

    public String firstName;
    public String lastName;
    public Integer yearOfBirth;

    public Author() {
    }

    public Author(String firstName, String lastName, Integer yearOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author entity = (Author) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(31);
    }

    public AuthorDTO toDTO() {
        return new AuthorDTO(id, firstName, lastName, yearOfBirth);
    }
}
