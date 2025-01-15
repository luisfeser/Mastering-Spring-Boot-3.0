package com.packt.ahmeric.bookstore.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;


@Entity
@Table(name = "authors")
@Data
@Builder
public class Author {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String biography;
    @ManyToOne
    private Publisher publisher;
}
