package com.nikolamateski.pcbooklibrary.domain.author;

import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecifications {

    public AuthorSpecifications() {
    }

    public static Specification<Author> byFirstNameLiteralEquals(final String firstName) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("firstName")), firstName.toLowerCase()));
    }

    public static Specification<Author> byLastNameLiteralEquals(final String lastName) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("lastName")), lastName.toLowerCase()));
    }

    public static Specification<Author> byYearOfBirthLiteralEquals(final Integer yearOfBirth) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("yearOfBirth"), yearOfBirth));
    }
}
