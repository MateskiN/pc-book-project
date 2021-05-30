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
    @Column(name = "title", length = 250)
    public String title;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(name = "isbn", length = 100)
    public String isbn;

    @NotNull
    @Column(name = "isbn")
    public Integer yearOfPublish;

    @NotNull
    @Column(name = "type")
    public BookType type;

    @NotNull(message = "Enter format!")
    @NotBlank
    @Column(name = "format")
    public String format;

    @NotNull
    @Column(name = "size")
    public Integer size;

    public Book toEntity() {
        return new Book(title, isbn, yearOfPublish, type, format, size);
    }
}
