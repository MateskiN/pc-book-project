package com.nikolamateski.pcbooklibrary.domain.book;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookDTO {

    public Integer id;
    public String title;
    public String isbn;
    public Integer yearOfPublish;
    public BookType type;
    public Integer numberOfPages;
    public BigDecimal weight;
    public String format;
    public Integer size;
}
