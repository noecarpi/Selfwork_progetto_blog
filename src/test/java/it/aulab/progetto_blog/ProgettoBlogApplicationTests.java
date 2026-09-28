package it.aulab.progetto_blog;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.repositories.AuthorRepository;

// @SpringBootTest
@DataJpaTest 
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProgettoBlogApplicationTests {

	@Autowired 
	AuthorRepository authorRepository;
	
	@BeforeEach 
	void load(){
		Author a1 = new Author();
		a1.setName("Bob");
		a1.setSurname("Belcher");
		a1.setEmail("burgerbob@test.it");
		authorRepository.save(a1);
	}

	@Test 
	void contextLoads(){
	}

	@Test 
	void findByName(){
		assertThat(authorRepository.findByName("Bob")).extracting("name").containsOnly("Bob");
	}

	@Test 
	void sameNameAuthor(){
		assertThat(authorRepository.authorsWithSameName()).extracting("name").containsOnly("Bob");
	}

	@Test 
	void sameNameAuthorNonNative(){
		assertThat(authorRepository.authorsWithSameName()).extracting("name").containsOnly("Bob");
	}

}
