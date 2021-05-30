package com.nikolamateski.pcbooklibrary.book;

import com.nikolamateski.pcbooklibrary.domain.author.Author;
import com.nikolamateski.pcbooklibrary.domain.author.AuthorRepository;
import com.nikolamateski.pcbooklibrary.domain.book.*;
import com.nikolamateski.pcbooklibrary.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Test
    public void given_create_ebook_request__when_create_ebook__then_created_ebook() {
        when(authorRepository.findById(any()))
                .thenReturn(Optional.of(new Author()));
        when(bookRepository.save(any(Book.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, Book.class));

        var request = new EBookRequest("title", "isbn", 1950, BookType.EBOOK, "format", 1);
        var expectedResult = new EBookDTO(null, request.title, request.isbn, request.yearOfPublish, request.type,
                request.format, request.size);
        var result = bookService.createEBook(request, 0);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void given_invalid_author_id__when_create_ebook__then_throw_exception() {
        when(authorRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.createEBook(new EBookRequest(), 0));
    }

    @Test
    public void given_create_print_copy_book_request__when_create_print_copy__then_created_print_copy() {
        when(authorRepository.findById(any()))
                .thenReturn(Optional.of(new Author()));
        when(bookRepository.save(any(Book.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, Book.class));

        var request = new PrintCopyBookRequest("title", "isbn", 1950, BookType.EBOOK,
                100, new BigDecimal(0));
        var expectedResult = new PrintCopyBookDTO(null, request.title, request.isbn, request.yearOfPublish, request.type,
                request.numberOfPages, request.weight);
        var result = bookService.createPrintCopy(request, 0);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void given_invalid_author_id__when_create_print_copy__then_throw_exception() {
        when(authorRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.createPrintCopy(new PrintCopyBookRequest(), 0));
    }

    @Test
    public void given_valid_book_id__when_update_ebook__then_update_ebook() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.of(new Book()));
        when(bookRepository.save(any(Book.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, Book.class));

        var request = new EBookRequest("title", "isbn", 1950, BookType.EBOOK, "format", 1);
        var expectedResult = new EBookDTO(null, request.title, request.isbn, request.yearOfPublish, request.type,
                request.format, request.size);
        var result = bookService.updateEBook(0, request);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void given_invalid_book_id__when_update_ebook__then_throw_exception() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.updateEBook(0, new EBookRequest()));
    }

    @Test
    public void given_valid_book_id__when_update_print_copy__then_update_print_copy() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.of(new Book()));
        when(bookRepository.save(any(Book.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, Book.class));

        var request = new PrintCopyBookRequest("title", "isbn", 1950, BookType.EBOOK,
                0, new BigDecimal(0));
        var expectedResult = new PrintCopyBookDTO(null, request.title, request.isbn, request.yearOfPublish, request.type,
                request.numberOfPages, request.weight);
        var result = bookService.updatePrintCopy(0, request);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void given_invalid_book_id__when_update_print_copy__then_throw_exception() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.updatePrintCopy(0, new PrintCopyBookRequest()));
    }

    @Test
    public void given_valid_book_id__when_delete_book__then_delete_book() {
        var book = new Book();
        when(bookRepository.findById(any()))
                .thenReturn(Optional.of(book));

        bookService.delete(0);

        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    public void given_invalid_book_id__when_delete_book__then_throw_exception() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.delete(0));
    }

    @Test
    public void given_valid_book_id__when_find_by_id__then_return_author() {
        var book = new Book();
        when(bookRepository.findById(any()))
                .thenReturn(Optional.of(new Book()));

        var expectedResult = book.toDTO();
        var result = bookService.findById(0);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void given_invalid_book_id__when_find_by_id__then_throw_exception() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.findById(0));
    }
}
