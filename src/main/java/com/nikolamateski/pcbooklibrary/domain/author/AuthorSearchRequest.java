package com.nikolamateski.pcbooklibrary.domain.author;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class AuthorSearchRequest {

    public String firstName;
    public String lastName;
    public Integer yearOfBirth;
    public Pageable pageable;

    public AuthorSearchRequest(String firstName, String lastName, Integer yearOfBirth, Pageable pageable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.pageable = pageable;
    }

    public Specification<Author> generateSpecification() {
        Specification<Author> categorySpecification = Specification.where(null);

        if (StringUtils.hasText(firstName)) {
            categorySpecification = categorySpecification.and(AuthorSpecifications.byFirstNameLiteralEquals(firstName));
        }

        if (StringUtils.hasText(lastName)) {
            categorySpecification = categorySpecification.and(AuthorSpecifications.byLastNameLiteralEquals(firstName));
        }

        if (yearOfBirth != null) {
            categorySpecification = categorySpecification.and(AuthorSpecifications.byYearOfBirthLiteralEquals(yearOfBirth));
        }

        return categorySpecification;
    }
}
