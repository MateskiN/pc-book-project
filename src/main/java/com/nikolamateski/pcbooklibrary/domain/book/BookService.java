package com.nikolamateski.pcbooklibrary.domain.book;

import com.nikolamateski.pcbooklibrary.domain.author.AuthorRepository;
import com.nikolamateski.pcbooklibrary.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public EBookDTO createEBook(final EBookRequest eBookRequest, final Integer authorId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author with " + authorId + " not found!"));

        var eBook = eBookRequest.toEntity();
        eBook.author = author;

        return bookRepository.save(eBook).toEBookDTO();
    }

    public PrintCopyBookDTO createPrintCopy(final PrintCopyBookRequest printCopyBookRequest, final Integer authorId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author with " + authorId + " not found!"));

        var printCopyBook = printCopyBookRequest.toEntity();
        printCopyBook.author = author;

        return bookRepository.save(printCopyBook).toPrintCopyDTO();
    }

    public PrintCopyBookDTO updatePrintCopy(final Integer bookId, final PrintCopyBookRequest printCopyBookRequest) {
        var printCopyBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with " + bookId + " not found!"));

        if (printCopyBook.type.toString().equals(BookType.EBOOK.toString())) {
            throw new ResourceNotFoundException("This book is not a " + printCopyBookRequest.type +
                    ". Please enter id of a Print Copy Book!");
        }

        printCopyBook.title = printCopyBookRequest.title;
        printCopyBook.isbn = printCopyBookRequest.isbn;
        printCopyBook.yearOfPublish = printCopyBookRequest.yearOfPublish;
        printCopyBook.numberOfPages = printCopyBookRequest.numberOfPages;
        printCopyBook.weight = printCopyBookRequest.weight;

        return bookRepository.save(printCopyBook).toPrintCopyDTO();
    }

    public EBookDTO updateEBook(final Integer bookId, final EBookRequest eBookRequest) {
        var eBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with " + bookId + " not found!"));

        if (eBook.type.toString().equals(BookType.PRINT_COPY.toString())) {
            throw new ResourceNotFoundException("This book is not a " + eBookRequest.type +
                    ". Please enter id of a EBook!");
        }

        eBook.title = eBookRequest.title;
        eBook.isbn = eBookRequest.isbn;
        eBook.yearOfPublish = eBookRequest.yearOfPublish;
        eBook.format = eBookRequest.format;
        eBook.size = eBookRequest.size;

        return bookRepository.save(eBook).toEBookDTO();
    }

    public void delete(final Integer bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with " + bookId + " not found!"));
        bookRepository.delete(book);
    }

    public BookDTO findById(final Integer bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with " + bookId + " not found!"));

        return book.toDTO();
    }

    public Page<BookDTO> findPage(final BookSearchRequest request) {
        return bookRepository.findAll(request.generateSpecification(), request.pageable)
                .map(Book::toDTO);
    }

    public List<BookDTO> findBookByAuthorsFirstLetterOfLastName(final Character firstLetterOfLastName) {
        return bookRepository.findBookByAuthorsFirstLetterOfLastName(firstLetterOfLastName)
                .stream()
                .map(Book::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO findOldestBook() {
        return bookRepository.findOldestBook().toDTO();
    }

    public BookDTO findNewestBook() {
        return bookRepository.findNewestBook().toDTO();
    }
}