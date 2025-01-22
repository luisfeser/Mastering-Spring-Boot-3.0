import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.packt.ahmeric.bookstore.data.Author;
import com.packt.ahmeric.bookstore.data.Publisher;
import com.packt.ahmeric.bookstore.repositories.AuthorRepository;
import com.packt.ahmeric.bookstore.services.AuthorService;

@ExtendWith(MockitoExtension.class) // inicializa los mocks automáticamente
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService AuthorService;

    private Author savedAuthor;

    @BeforeEach
    void setup() {
        Publisher defaultPublisher = Publisher.builder().name("Packt").build();
        savedAuthor = Author.builder()
                .id(1L)
                .name("Ahmeric")
                .biography("Author of the book")
                .publisher(defaultPublisher)
                .build();
    }

    @Test
    public void whenExistingAuthorId_whenGetAuthor_thenReturnAuthor() {
        // When
        when(authorRepository.findById(1L)).thenReturn(Optional.of(savedAuthor));
        Optional<Author> author = AuthorService.getAuthor(1L);

        // Then
        assertTrue(author.isPresent(), "Author should be found");
        assertEquals(1L, author.get().getId(), "Author id should be 1");
    }

    @Test
    public void whenGetAuthors_thenReturnAllAuthors() {
        // When
        when(authorRepository.findAll()).thenReturn(List.of(savedAuthor));
        var authors = AuthorService.getAuthors();

        // Then
        assertEquals(1, authors.size(), "Authors list should contain 1 author");
        assertEquals(1L, authors.get(0).getId(), "Author id should be 1");
    }

    @Test
    public void whenAddNewAuthor_thenResturnSavedAuthor() {
        // When
        when(authorRepository.save(savedAuthor)).thenReturn(savedAuthor);
        Author author = AuthorService.addAuthor(savedAuthor);

        // Then
        assertEquals(1L, author.getId(), "Author id should be 1");
    }

    @Test
    public void whenUpdateAuthor_thenReturnUpdatedAuthor() {
        // Given
        Author modifiedAuthor = Author.builder()
            .name("Ahmeric Updated")
            .biography("Author of the book Updated")
            .publisher(Publisher.builder().name("Packt Updated").build())
            .build();

        // Mocker comportamiento del repositorio. Recupera un objeto y luego lo actualiza
        when(authorRepository.findById(1L)).thenReturn(Optional.of(savedAuthor));
        when(authorRepository.save(savedAuthor)).thenReturn(savedAuthor); // No se actualiza realmente, pero se devuelve el objeto

        // When
        Optional<Author> updatedAuthor = AuthorService.updateAuthor(1L, modifiedAuthor);

        // Then
        assertEquals(1L, updatedAuthor.get().getId(), "Author id should be 1");
        assertEquals("Ahmeric Updated", updatedAuthor.get().getName(), "Author name should be 'Ahmeric Updated'");
        assertEquals("Author of the book Updated", updatedAuthor.get().getBiography(), "Author biography should be 'Author of the book Updated'");
        assertEquals("Packt Updated", updatedAuthor.get().getPublisher().getName(), "Publisher name should be 'Packt Updated'");

        verify(authorRepository, times(1)).findById(1L);
        verify(authorRepository, times(1)).save(savedAuthor);
    }

    @Test
    public void whenDeleteAuthor_thenVerifyDeleteIsCalled() {
        // Given
        doNothing().when(authorRepository).deleteById(1L);

        // When
        AuthorService.deleteAuthor(1L);

        // Then
        verify(authorRepository, times(1)).deleteById(savedAuthor.getId());
    }
}
