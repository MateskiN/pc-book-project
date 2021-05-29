package com.nikolamateski.pcbooklibrary.domain.book;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EBookDTO {

    public Integer id;
    public String title;
    public String isbn;
    public Integer yearOfPublish;
    public BookType type;
    public String format;
    public Integer size;
}
