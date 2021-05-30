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

    @NotNull(message = "Please enter title for book!")
    @NotBlank
    @Size(max = 250)
    @Column(name = "title", length = 250)
    public String title;

    @NotNull(message = "Please enter ISBN for book!")
    @NotBlank
    @Size(max = 100)
    @Column(name = "isbn", length = 100)
    public String isbn;

    @NotNull(message = "Please enter year of publish for book!")
    @Column(name = "isbn")
    public Integer yearOfPublish;

    @NotNull(message = "Please enter type(EBOOK or PRINT_COPY) for book!")
    @Column(name = "type")
    public BookType type;

    @NotNull(message = "Please enter format for book!")
    @NotBlank
    @Column(name = "format")
    public String format;

    @NotNull(message = "Please enter size for book!")
    @Column(name = "size")
    public Integer size;

    public Book toEntity() {
        return new Book(title, isbn, yearOfPublish, type, format, size);
    }
}
