package it.aulab.progetto_blog.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.aulab.progetto_blog.models.Author;
import java.util.List;


public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findByName(String firstname);
    List<Author> findBySurname(String lastname);
    List<Author> findByNameAndSurname(String firstname, String lastname);

    // query nativa
    @Query (value = "SELECT * FROM authors a WHERE a.firstname = 'Bob'", nativeQuery = true)
    List<Author> authorsWithSameName();

    // query non nativa
    @Query ("SELECT a FROM Author a WHERE a.name = 'Bob'")
    List<Author> authorsWithSameNameNonNative();
}
