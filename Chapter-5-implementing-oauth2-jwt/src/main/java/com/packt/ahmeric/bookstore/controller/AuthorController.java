package com.packt.ahmeric.bookstore.controller;

import com.packt.ahmeric.bookstore.data.Author;
import com.packt.ahmeric.bookstore.repositories.AuthorRepository;
import com.packt.ahmeric.bookstore.services.AuthorService;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
// @RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.addAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthor(id);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAuthors();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        return authorService.getAuthor(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(author.getName());
                    existingAuthor.setBiography(author.getBiography());
                    existingAuthor.setPublisher(author.getPublisher());
                    Author updatedAuthor = authorService.addAuthor(existingAuthor);
                    return ResponseEntity.ok(updatedAuthor);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable Long id) {
        return authorService.getAuthor(id)
                .map(author -> {
                    authorService.deleteAuthor(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
