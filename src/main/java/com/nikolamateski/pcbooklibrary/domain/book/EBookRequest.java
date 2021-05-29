package com.nikolamateski.pcbooklibrary.domain.book;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
public class EBookRequest {

    @NotNull
    @NotBlank
    @Size(max = 250)
    @Column(name = "title", length = 250, nullable = false)
    public String title;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(name = "isbn", length = 100, nullable = false)
    public String isbn;

    @NotNull
    @NotBlank
    @Column(name = "isbn", nullable = false)
    public Integer yearOfPublish;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(name = "type", length = 50, nullable = false)
    public BookType type;

    @NotNull
    @NotBlank
    @Column(name = "format", nullable = false)
    public String format;

    @NotNull
    @NotBlank
    @Column(name = "size", nullable = false)
    public Integer size;

    public Book toEntity() {
        return new Book(title, isbn, yearOfPublish, type, format, size);
    }
}
