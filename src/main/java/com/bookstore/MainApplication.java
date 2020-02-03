package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("Persisting several authors ...");
            bookstoreService.createAuthors();
        };
    }
}

/*
 * 
 * 
 * 
 * Attributes Lazy Loading Via Subentities

Description: By default, the attributes of an entity are loaded eager (all at once). This application is an alternative to How To Use Hibernate Attribute Lazy Loading from here. This application uses a base class to isolate the attributes that should be loaded eagerly and subentities (entities that extends the base class) for isolating the attributes that should be loaded on demand.



Key points:

create the base class (this is not an entity), BaseAuthor, and annotate it with @MappedSuperclass
create AuthorShallow subentity of BaseAuthor and don't add any attribute in it (this will inherit the attributes from the superclass)
create AuthorDeep subentity of BaseAuthor and add to it the attributes that should be loaded on demand (e.g., avatar)
map both subentities to the same table via @Table(name = "author")
provide the typical repositories, AuthorShallowRepository and AuthorDeepRepository
Run the following requests (via BookstoreController):

fetch all authors shallow (without avatars): localhost:8080/authors/shallow
fetch all authors deep (with avatars): localhost:8080/authors/deep
Check as well:

Attribute Lazy Loading (basic)
Default Values For Lazy Loaded Attributes
Attribute Lazy Loading And Jackson Serialization
 * 
 * 
 */
