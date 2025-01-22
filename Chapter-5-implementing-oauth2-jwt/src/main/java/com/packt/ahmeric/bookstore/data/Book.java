package com.packt.ahmeric.bookstore.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String isbn;
    @ManyToMany
    private List<Author> authors;
    private Boolean published;
}
