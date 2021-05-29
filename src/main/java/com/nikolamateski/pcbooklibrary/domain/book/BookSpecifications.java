package com.nikolamateski.pcbooklibrary.domain.book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public BookSpecifications() {
    }

    public static Specification<Book> byTitleLiteralEquals(final String title) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("title")), title.toLowerCase()));
    }

    public static Specification<Book> byISBNLiteralEquals(final Integer isbn) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isbn"), isbn));
    }

    public static Specification<Book> byYearOfPublishLiteralEquals(final Integer yearOfPublish) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("yearOfPublish"), yearOfPublish));
    }

    public static Specification<Book> byTypeLiteralEquals(final BookType type) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("type")), type.toString().toLowerCase()));
    }
}
