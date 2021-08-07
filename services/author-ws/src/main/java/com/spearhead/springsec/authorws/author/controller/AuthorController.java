package com.spearhead.springsec.authorws.author.controller;

import com.spearhead.springsec.authorws.author.model.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {
    private Map<String, Author> authorsMap = new ConcurrentHashMap<>();

    public AuthorController() {
        Author author = new Author("dummy", "Dummy Name", "dummy@dummy.org");
        authorsMap.put(author.getAuthorId(), author);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Collection<Author>> getAllAuthors() {
        if (authorsMap.size() > 0) {
            return new ResponseEntity<>(authorsMap.values(), HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/author-id", produces = "application/json")
    public ResponseEntity<Author> getAuthorById(@PathVariable("author-id") String authorId) {
        Author author = authorsMap.get(authorId);
        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Author> creatAuthor(@RequestBody Author author) {
        author.setAuthorId(UUID.randomUUID().toString());
        authorsMap.put(author.getAuthorId(), author);

        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }
}
