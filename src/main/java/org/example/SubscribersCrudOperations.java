package org.example;

import java.sql.*;
import java.util.List;

public class SubscribersCrudOperations implements CrudOperations<Subscribers> {
    String dbUrl = System.getenv("dbUrl");
    String user = System.getenv("PG_USER");
    String password = System.getenv("PASSWORD");
    @Override
    public List<Subscribers> findAll() {

        String query = "SELECT * FROM Subscribers";


        try (Connection conn = DriverManager.getConnection(dbUrl, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int visitorId = rs.getInt("visitorId");
                String visitorName = rs.getString("visitorName");
                String reference = rs.getString("reference");

                Subscribers subscriber = new Subscribers(visitorId, visitorName, reference);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public List<Subscribers> saveAll(List<Subscribers> toSave) {

        String query = "INSERT INTO Subscribers (visitorName, reference) VALUES (?, ?)";

        try (Connection conn =  DriverManager.getConnection(dbUrl, user, password)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                for (Subscribers subscriber : toSave) {
                    preparedStatement.setString(1, subscriber.getVisitorName());
                    preparedStatement.setString(2, subscriber.getReference());
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
    public Subscribers save(Subscribers toSave) {
        String query = "INSERT INTO Subscribers (visitorName, reference) VALUES (?, ?)";

        try (Connection conn =  DriverManager.getConnection(dbUrl, user, password)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, toSave.getVisitorName());
                preparedStatement.setString(2, toSave.getReference());
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
    public Subscribers delete(Subscribers toDelete) {
        String query = "DELETE FROM Subscribers WHERE visitorId = ?";

        try (Connection conn =  DriverManager.getConnection(dbUrl, user, password)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setInt(1, toDelete.getVisitorId());
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Subscriber deleted");
                } else {

                    System.out.println("None of the  suscribers have been deleleted");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace(); //
        }

        return toDelete;
    }
}

