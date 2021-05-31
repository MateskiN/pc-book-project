package com.nikolamateski.pcbooklibrary.domain.book;

import com.nikolamateski.pcbooklibrary.util.VoidDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookResource {

    private final BookService bookService;

    @PostMapping(path = "/ebook",consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public EBookDTO createEBook(@RequestBody @Valid final EBookRequest eBookRequest,
                               @RequestParam("authorId") final Integer authorId) {
        return bookService.createEBook(eBookRequest, authorId);
    }

    @PostMapping(path = "/print-copy",consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public PrintCopyBookDTO createPrintCopy(@RequestBody @Valid PrintCopyBookRequest printCopyBookRequest,
                                   @RequestParam("authorId") final Integer authorId) {
        return bookService.createPrintCopy(printCopyBookRequest, authorId);
    }

    @PutMapping(path = "/ebook/{eBookId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public EBookDTO updateEBook(@PathVariable("eBookId") final Integer eBookId,
                                @RequestBody final EBookRequest eBookRequest) {
        return bookService.updateEBook(eBookId, eBookRequest);
    }

    @PutMapping(path = "/print-copy/{printCopyId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public PrintCopyBookDTO updatePrintCopy(@PathVariable("printCopyId") final Integer printCopyId,
                                @RequestBody final PrintCopyBookRequest printCopyBookRequest) {
        return bookService.updatePrintCopy(printCopyId, printCopyBookRequest);
    }

    @DeleteMapping(path = "/{bookId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("bookId") final Integer bookId) {
        bookService.delete(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(new VoidDTO(true));
    }

    @GetMapping(path = "/{bookId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public BookDTO findById(@PathVariable("bookId") final Integer bookId) {
        return bookService.findById(bookId);
    }

    @GetMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Page<BookDTO> findPage(
            @RequestParam(value = "title", required = false) final String title,
            @RequestParam(value = "isbn", required = false) final Integer isbn,
            @RequestParam(value = "yearOfPublish", required = false) final Integer yearOfPublish, Pageable pageable) {
        return bookService.findPage(new BookSearchRequest(title, isbn, yearOfPublish, pageable));
    }

    @GetMapping(path = "/orderByOldestToNewest", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<BookDTO> orderBooksFromOldestToNewest() {
        return bookService.orderAllBooksFromOldestToNewest();
    }

    @GetMapping(path = "/byFirstLetterOfAuthorsLastName", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<BookDTO> findBookByAuthorsFirstLetterOfLastName(
            @RequestParam(value = "byFirstLetterOfAuthorsLastName") final Character firstLetterOfLastName) {
        return bookService.findBookByAuthorsFirstLetterOfLastName(firstLetterOfLastName);
    }

    @GetMapping(path = "/oldest", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public BookDTO findOldestBook() {
        return bookService.findOldestBook();
    }

    @GetMapping(path = "/newest", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public BookDTO findNewestBook() {
        return bookService.findNewestBook();
    }
}