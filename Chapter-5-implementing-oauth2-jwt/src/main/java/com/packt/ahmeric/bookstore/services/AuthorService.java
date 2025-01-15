package com.packt.ahmeric.bookstore.services;

import java.util.Optional;

import com.packt.ahmeric.bookstore.data.Author;
import com.packt.ahmeric.bookstore.repositories.AuthorRepository;

public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> getAuthor(Long id) {
        return authorRepository.findById(id);
    }

}
