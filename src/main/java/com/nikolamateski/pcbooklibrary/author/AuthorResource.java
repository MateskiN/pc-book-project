package com.nikolamateski.pcbooklibrary.author;

import com.nikolamateski.pcbooklibrary.util.VoidDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorResource {

    private final AuthorService authorService;

    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public AuthorDTO create(@RequestBody final AuthorRequest request) {
        return authorService.create(request);
    }

    @PutMapping(path = "/{authorId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public AuthorDTO update(@PathVariable("authorId") final Integer authorId,
                            @RequestBody final AuthorRequest request) {
        return authorService.update(authorId, request);
    }

    @DeleteMapping(path = "/{authorId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("authorId") final Integer authorId) {
        authorService.delete(authorId);
        return ResponseEntity.status(HttpStatus.OK).body(new VoidDTO(true));
    }

    @GetMapping(path = "/{authorId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public AuthorDTO findById(@PathVariable("authorId") final Integer authorId) {
        return authorService.findById(authorId);
    }

    @GetMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<AuthorDTO> findAll() {
        return authorService.findAll();
    }
}
