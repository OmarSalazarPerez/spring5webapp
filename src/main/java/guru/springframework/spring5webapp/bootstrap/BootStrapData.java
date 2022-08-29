package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");
        Publisher publisher = new Publisher();
        publisher.setName("publicata");
        publisher.setAdresLine1("linea 2");
        publisher.setCity("Mexico");
        publisher.setState("CDMX");
        publisher.setZip("que es?");

        publisherRepository.save(publisher);
        System.out.println("Number of publishers "+ publisherRepository.count());

        Author omar = new Author("Omar", "curso");
        Book ddd = new Book("Domine Drive Desing", "1234");

        omar.getBooks().add(ddd);
        ddd.getAuthors().add(omar);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);
        authorRepository.save(omar);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Jhonson");
        Book noEJB = new Book("J2EE Development Whitout EJB", "12345");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of books " + bookRepository.count());
        System.out.println("Publisher numer of books "+ publisher.getBooks().size());

    }
}
