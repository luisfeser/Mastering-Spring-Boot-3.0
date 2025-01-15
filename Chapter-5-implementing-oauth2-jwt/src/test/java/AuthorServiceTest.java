import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
