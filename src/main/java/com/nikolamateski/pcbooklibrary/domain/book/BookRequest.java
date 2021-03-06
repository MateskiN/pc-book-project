package com.nikolamateski.pcbooklibrary.domain.book;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

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
    @Column(name = "isbn", nullable = false)
    public Integer yearOfPublish;

    @NotNull
    @Column(name = "type", length = 50, nullable = false)
    public BookType type;

    @Column(name = "number_of_pages")
    public Integer numberOfPages;

    @Column(name = "weight")
    public BigDecimal weight;

    @Column(name = "format")
    public String format;

    @Column(name = "size")
    public Integer size;

    public Book toEntity() {
        return new Book(title, isbn, yearOfPublish, type);
    }
}
