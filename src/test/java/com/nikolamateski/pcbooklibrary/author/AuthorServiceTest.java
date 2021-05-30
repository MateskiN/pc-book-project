package com.nikolamateski.pcbooklibrary.author;

import com.nikolamateski.pcbooklibrary.domain.author.*;
import com.nikolamateski.pcbooklibrary.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Test
    public void given_create_author_request__when_create_author__then_created_author() {
        when(authorRepository.save(any(Author.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, Author.class));

        var request = new AuthorRequest("firstName", "lastName", 1950);
        var expectedResult = new AuthorDTO(null, request.firstName, request.lastName, request.yearOfBirth);
        var result = authorService.create(request);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void given_valid_author_id__when_update_author__then_update_author() {
        when(authorRepository.findById(any()))
                .thenReturn(Optional.of(new Author()));
        when(authorRepository.save(any(Author.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, Author.class));

        var request = new AuthorRequest("firstName", "lastName", 1950);
        var expectedResult = new AuthorDTO(null, request.firstName, request.lastName, request.yearOfBirth);
        var result = authorService.update(0, request);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void given_invalid_author_id__when_update_author__then_throw_exception() {
        when(authorRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authorService.update(0, new AuthorRequest()));
    }

    @Test
    public void given_valid_author_id__when_delete_author__then_delete_author() {
        var author = new Author();
        when(authorRepository.findById(any()))
                .thenReturn(Optional.of(author));

        authorService.delete(0);

        verify(authorRepository, times(1)).delete(author);
    }
    
    @Test
    public void given_invalid_author_id__when_delete_author__then_throw_exception() {
        when(authorRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authorService.delete(0));
    }

    @Test
    public void given_valid_author_id__when_find_by_id__then_return_author() {
        var author = new Author();
        when(authorRepository.findById(any()))
                .thenReturn(Optional.of(new Author()));

        var expectedResult = author.toDTO();
        var result = authorService.findById(0);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void given_invalid_author_id__when_find_by_id__then_throw_exception() {
        when(authorRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authorService.findById(0));
    }
}
