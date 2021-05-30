package com.nikolamateski.pcbooklibrary.domain.author;

import com.nikolamateski.pcbooklibrary.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorDTO create(final AuthorRequest request) {
        var author = request.toEntity();
        return authorRepository.save(author).toDTO();
    }

    public AuthorDTO update(final Integer authorId, final AuthorRequest request) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author with " + authorId + " not found!"));

        author.firstName = request.firstName;
        author.lastName = request.lastName;
        author.yearOfBirth = request.yearOfBirth;

        return authorRepository.save(author).toDTO();
    }

    public void delete(final Integer authorId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author with " + authorId + " not found!"));
        authorRepository.delete(author);
    }

    public AuthorDTO findById(final Integer authorId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author with " + authorId + " not found!"));

        return author.toDTO();
    }

    public Page<AuthorDTO> findPage(final AuthorSearchRequest request) {
        return authorRepository.findAll(request.generateSpecification(), request.pageable)
                .map(Author::toDTO);
    }

    public List<AuthorDTO> findAuthorWithMoreThanThreeBooks() {
        return authorRepository.findAuthorWithMoreThanThreeBooks()
                .stream()
                .map(Author::toDTO)
                .collect(Collectors.toList());
    }

    public List<AuthorDTO> findAuthorsBornInTheSameDecadeAsPublishedBooks() {
        return authorRepository.findAuthorsBornInTheSameDecadeAsPublishedBooks()
                .stream()
                .map(Author::toDTO)
                .collect(Collectors.toList());
    }
}
