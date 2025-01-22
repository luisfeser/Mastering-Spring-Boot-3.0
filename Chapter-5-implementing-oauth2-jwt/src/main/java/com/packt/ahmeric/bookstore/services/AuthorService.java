package com.packt.ahmeric.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.packt.ahmeric.bookstore.data.Author;
import com.packt.ahmeric.bookstore.repositories.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthor(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> updateAuthor(Long id, Author author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(author.getName());
                    existingAuthor.setBiography(author.getBiography());
                    existingAuthor.setPublisher(author.getPublisher());
                    return authorRepository.save(existingAuthor);
                });
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

}
