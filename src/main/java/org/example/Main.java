package org.example;
import org.example.Book;
import org.example.BookCrudOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        testBookCrudOperations();


        testAuthorCrudOperations();


        testSubscribersCrudOperations();
    }

    private static void testBookCrudOperations() {
        BookCrudOperations bookCrudOperations = new BookCrudOperations();

        // Test findAll
        logger.info("Testing findAll for books:");
        List<Book> allBooks = bookCrudOperations.findAll();
        printBooks(allBooks);

        // Test save
        logger.info("Testing save for books:");
        List<Book> booksToSave = createDummyBooks();
        List<Book> savedBooks = bookCrudOperations.saveAll(booksToSave);
        printBooks(savedBooks);

        // Test delete
        logger.info("Testing delete for books:");
        if (!savedBooks.isEmpty()) {
            Book bookToDelete = savedBooks.get(0);
            bookCrudOperations.delete(bookToDelete);
            List<Book> remainingBooks = bookCrudOperations.findAll();
            printBooks(remainingBooks);
        }
    }
    private static List<Book> createDummyBooks() {
        List<Book> dummyBooks = new ArrayList<>();
        // Replace with actual data or use a data generation method
        return dummyBooks;
    }
    private static void printBooks(List<Book> books) {
        for (Book book : books) {
            logger.info("Book ID: {}, Name: {}, Author ID: {}, Topic: {}, Page Numbers: {}, Release Date: {}",
                    book.getId(), book.getBookName(), book.getAuthorId(), book.getTopic(), book.getPageNumbers(), book.getReleaseDate());
        }
    }

    private static void testAuthorCrudOperations() {

        AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();

        // Test findAll
        logger.info("Testing findAll for authors:");
        List<Author> allAuthors = authorCrudOperations.findAll();
        printAuthors(allAuthors);

        // Test save
        logger.info("Testing save for authors:");
        List<Author> authorsToSave = createDummyAuthors(); // Replace with actual data
        List<Author> savedAuthors = authorCrudOperations.saveAll(authorsToSave);
        printAuthors(savedAuthors);

        // Test delete            logger.info("Testing delete for authors:");
        if (!savedAuthors.isEmpty()) {
            Author authorToDelete = savedAuthors.get(0); // Delete the first saved author
            authorCrudOperations.delete(authorToDelete);
            List<Author> remainingAuthors = authorCrudOperations.findAll();
            printAuthors(remainingAuthors);
        }
    }

    private static List<Author> createDummyAuthors() {
        List<Author> dummyAuthors = new ArrayList<>();

        return dummyAuthors;
    }

    private static void printAuthors(List<Author> authors) {
        for (Author author : authors) {
            logger.info("Author ID: {}, Name: {}, Sex: {}", author.getIdAuthor(), author.getAuthorName(), author.getSex());
        }
    }




    private static void testSubscribersCrudOperations() {
        SubscribersCrudOperations subscribersCrudOperations = new SubscribersCrudOperations();

        // Test findAll
        logger.info("Testing findAll for subscribers:");
        List<Subscribers> allSubscribers = subscribersCrudOperations.findAll();
        printSubscribers(allSubscribers);

        // Test save
        logger.info("Testing save for subscribers:");
        List<Subscribers> subscribersToSave = createDummySubscribers();
        List<Subscribers> savedSubscribers = subscribersCrudOperations.saveAll(subscribersToSave);
        printSubscribers(savedSubscribers);

        // Test delete
        logger.info("Testing delete for subscribers:");
        if (!savedSubscribers.isEmpty()) {
            Subscribers subscriberToDelete = savedSubscribers.get(0);
            subscribersCrudOperations.delete(subscriberToDelete);
            List<Subscribers> remainingSubscribers = subscribersCrudOperations.findAll();
            printSubscribers(remainingSubscribers);
        }
    }

    private static List<Subscribers> createDummySubscribers() {
        // Replace this method with logic to create a list of dummy subscribers for testing
        List<Subscribers> dummySubscribers = new ArrayList<>();
        dummySubscribers.add(new Subscribers(1, "John Doe", "Reference1"));
        dummySubscribers.add(new Subscribers(2, "Jane Smith", "Reference2"));
        dummySubscribers.add(new Subscribers(3, "Alice Johnson", "Reference3"));
        return dummySubscribers;
    }

    private static void printSubscribers(List<Subscribers> subscribers) {
        if (subscribers != null) {
            for (Subscribers subscriber : subscribers) {
                logger.info("Subscriber ID: {}, Name: {}, Reference: {}",
                        subscriber.getVisitorId(), subscriber.getVisitorName(), subscriber.getReference());
            }
        } else {
            logger.warn("List of subscribers is null.");
        }
    }

}
