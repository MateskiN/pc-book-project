package com.nikolamateski.pcbooklibrary.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    @Query("SELECT book FROM Book book ORDER BY book.yearOfPublish")
    List<Book> orderAllBooksFromOldestToNewest();

    @Query("SELECT book FROM Book book " +
            " INNER JOIN book.author author " +
            " WHERE author.lastName LIKE :firstLetterOfLastName%")
    List<Book> findBookByAuthorsFirstLetterOfLastName(@Param("firstLetterOfLastName") Character firstLetterOfLastName);

    @Query("SELECT book FROM Book book " +
            " WHERE book.yearOfPublish = " +
            " (SELECT MIN(book.yearOfPublish) FROM Book book)")
    Book findOldestBook();

    @Query("SELECT book FROM Book book " +
            " WHERE book.yearOfPublish = " +
            " (SELECT MAX(book.yearOfPublish) FROM Book book)")
    Book findNewestBook();
}
