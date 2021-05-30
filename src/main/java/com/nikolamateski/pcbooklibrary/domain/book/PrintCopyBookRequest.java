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
public class PrintCopyBookRequest {

    @NotNull(message = "Please enter title for book!")
    @NotBlank
    @Size(max = 250)
    @Column(name = "title", length = 250, nullable = false)
    public String title;

    @NotNull(message = "Please enter ISBN for book!")
    @NotBlank
    @Size(max = 100)
    @Column(name = "isbn", length = 100, nullable = false)
    public String isbn;

    @NotNull(message = "Please enter year of publish for book!")
    @Column(name = "isbn", nullable = false)
    public Integer yearOfPublish;

    @NotNull(message = "Please enter type(EBOOK or PRINT_COPY) for book!")
    @Column(name = "type", length = 50, nullable = false)
    public BookType type;

    @NotNull(message = "Please enter number of pages for book!")
    @Column(name = "number_of_pages")
    public Integer numberOfPages;

    @NotNull(message = "Please enter weight for book!")
    @Column(name = "weight")
    public BigDecimal weight;

    public Book toEntity() {
        return new Book(title, isbn, yearOfPublish, type, numberOfPages, weight);
    }
}
