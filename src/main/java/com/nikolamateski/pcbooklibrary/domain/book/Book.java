package com.nikolamateski.pcbooklibrary.domain.book;

import com.nikolamateski.pcbooklibrary.domain.author.Author;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "book")
@ToString
public class Book {

    @Id
    @GeneratedValue
    public Integer id;

    public String title;
    public String isbn;
    public Integer yearOfPublish;

    @Enumerated(value = EnumType.STRING)
    public BookType type;

    public Integer numberOfPages;
    public BigDecimal weight;
    public String format;
    public Integer size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    public Author author;

    public Book() {
    }

    public Book(String title, String isbn, Integer yearOfPublish, BookType type) {
        this.title = title;
        this.isbn = isbn;
        this.yearOfPublish = yearOfPublish;
        this.type = type;
    }

    // Constructor for EBook
    public Book(String title, String isbn, Integer yearOfPublish, BookType type, Integer numberOfPages, BigDecimal weight) {
        this.title = title;
        this.isbn = isbn;
        this.yearOfPublish = yearOfPublish;
        this.type = type;
        this.numberOfPages = numberOfPages;
        this.weight = weight;
    }

    // Constructor for Print Copy Book
    public Book(String title, String isbn, Integer yearOfPublish, BookType type, String format, Integer size) {
        this.title = title;
        this.isbn = isbn;
        this.yearOfPublish = yearOfPublish;
        this.type = type;
        this.format = format;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book entity = (Book) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(31);
    }

    public BookDTO toDTO() {
        return new BookDTO(id, title, isbn, yearOfPublish, type, numberOfPages, weight, format, size);
    }

    public PrintCopyBookDTO toPrintCopyDTO() {
        return new PrintCopyBookDTO(id, title, isbn, yearOfPublish, type, numberOfPages, weight);
    }

    public EBookDTO toEBookDTO() {
        return new EBookDTO(id, title, isbn, yearOfPublish, type, format, size);
    }
}
