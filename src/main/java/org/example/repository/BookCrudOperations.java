package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookCrudOperations implements CrudOperations<Book> {
    String dbUrl = System.getenv("dbUrl");
    String user = System.getenv("PG_USER");
    String password = System.getenv("PASSWORD");

    @Override
    public List<Book> findAll() {
        String query = "SELECT * FROM Book";
        List<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                int authorId = rs.getInt("author_id");
                String topicString = rs.getString("topic");
                int pageNumbers = rs.getInt("pageNumbers");
                String releaseDate = rs.getString("releaseDate");
                Topic topic = Topic.fromString(topicString);
                Book book = new Book(id, bookName, authorId, topic, pageNumbers, releaseDate);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    @Override
    public List<Book> saveAll(List<Book> toSave) {
        String query = "INSERT INTO Book (bookName, author_id, topic, pageNumbers, releaseDate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                for (Book book : toSave) {
                    preparedStatement.setString(1, book.getBookName());
                    preparedStatement.setInt(2, book.getAuthorId());
                    preparedStatement.setString(3, book.getTopic().name());
                    preparedStatement.setInt(4, book.getPageNumbers());
                    preparedStatement.setString(5, book.getReleaseDate());
                    preparedStatement.addBatch();
                }

                int[] batchResults = preparedStatement.executeBatch();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toSave;
    }

    @Override
    public Book save(Book toSave) {
        String query = "INSERT INTO Book (bookName, author_id, topic, pageNumbers, releaseDate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, toSave.getBookName());
                preparedStatement.setInt(2, toSave.getAuthorId());
                preparedStatement.setString(3, toSave.getTopic().name());
                preparedStatement.setInt(4, toSave.getPageNumbers());
                preparedStatement.setString(5, toSave.getReleaseDate());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    toSave.setId(generatedKeys.getInt(1));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toSave;
    }

    @Override
    public Book delete(Book toDelete) {
        String query = "DELETE FROM Book WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setInt(1, toDelete.getId());
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Book deleted");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toDelete;
    }
}

