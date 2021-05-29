package com.nikolamateski.pcbooklibrary.domain.book;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BookSearchRequest {

    public String title;
    public Integer isbn;
    public Integer yearOfPublish;
    public Pageable pageable;

    public BookSearchRequest(String title, Integer isbn, Integer yearOfPublish, Pageable pageable) {
        this.title = title;
        this.isbn = isbn;
        this.yearOfPublish = yearOfPublish;
        this.pageable = pageable;
    }

    public Specification<Book> generateSpecification() {
        Specification<Book> categorySpecification = Specification.where(null);

        if (StringUtils.hasText(title)) {
            categorySpecification = categorySpecification.and(BookSpecifications.byTitleLiteralEquals(title));
        }

        if (isbn != null) {
            categorySpecification = categorySpecification.and(BookSpecifications.byISBNLiteralEquals(isbn));
        }

        if (yearOfPublish != null) {
            categorySpecification = categorySpecification.and(BookSpecifications.byYearOfPublishLiteralEquals(yearOfPublish));
        }

        return categorySpecification;
    }
}
