package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations<Author> {
    String dbUrl = System.getenv("dbUrl");
    String user = System.getenv("PG_USER");
    String password = System.getenv("PASSWORD");

    @Override
    public List<Author> findAll() {
        String query = "SELECT * FROM Author";
        List<Author> authors = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int authorId = rs.getInt("id_author");
                String authorName = rs.getString("authorName");
                char sex = rs.getString("sex").charAt(0);

                Author author = new Author(authorId, authorName, sex);
                authors.add(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return authors;
    }


    @Override
    public List<Author> saveAll(List<Author> toSave) {
        String query = "INSERT INTO Author (authorName, sex) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                for (Author author : toSave) {
                    preparedStatement.setString(1, author.getAuthorName());
                    preparedStatement.setString(2, String.valueOf(author.getSex()));
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
    public Author save(Author toSave) {

        String query = "INSERT INTO Author (authorName, sex) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, toSave.getAuthorName());
                preparedStatement.setString(2, String.valueOf(toSave.getSex()));
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toSave;
    }


    @Override
    public Author delete(Author toDelete) {
        String query = "DELETE FROM Author WHERE id_author = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setInt(1, toDelete.getIdAuthor());
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Author deleted");
                } else {
                    System.out.println("None of the authors have been deleted");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toDelete;
    }
}

