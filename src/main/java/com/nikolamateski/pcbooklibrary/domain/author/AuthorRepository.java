package com.nikolamateski.pcbooklibrary.domain.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer>, JpaSpecificationExecutor<Author> {

    @Query("SELECT author" +
            " FROM Author author, Book book " +
            " WHERE author = book.author " +
            " GROUP BY author " +
            " HAVING COUNT(book) > 3")
    List<Author> findAuthorWithMoreThanThreeBooks();

    @Query("SELECT author " +
            " FROM Author author, Book book " +
            " WHERE author.yearOfBirth " +
            " BETWEEN (book.yearOfPublish / 10) * 10 AND ((book.yearOfPublish / 10) * 10) + 10")
    List<Author> findAuthorsBornInTheSameDecadeAsPublishedBooks();

//    SELECT a.*
//    FROM author a, book b
//    WHERE a.year_of_birth BETWEEN (b.year_of_publish/10) * 10 AND ((b.year_of_publish/10) * 10) + 10;
}
