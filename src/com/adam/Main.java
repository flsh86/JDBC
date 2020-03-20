package com.adam;

import java.awt.event.PaintEvent;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/javaconnection";
        String user = "postgres";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = createPreparedStatement(connection,
                     "CREATE TABLE IF NOT EXISTS contacts" +
                     " (" +
                     "id SERIAL PRIMARY KEY," +
                     "name VARCHAR(50) NOT NULL," +
                     "phone VARCHAR(30) UNIQUE," +
                     "email VARCHAR(250) UNIQUE" +
                     ")"
             );
             PreparedStatement ps2 = createPreparedStatement(connection,
                     "INSERT INTO contacts " +
                     "VALUES " + "(1,'Adam', '11234678', 'adam@sadsa.2dsa'), " +
                             " (4,'Dominik', '1232132', 'domi@uber.eats') " +
                     "ON CONFLICT (id) DO NOTHING");
             PreparedStatement ps3 = createPreparedStatement(connection,
                     "SELECT * FROM contacts ORDER BY id");
             PreparedStatement ps4 = createPreparedStatement(connection,
                     "UPDATE contacts SET name = 'Roman' WHERE name = 'Adam'");
        )
        {   ps.execute();
            ps2.execute();
            ps4.execute();

            try( ResultSet resultSet = ps3.executeQuery();) {
                while (resultSet.next()) {
                    System.out.println(
                            resultSet.getInt(1) + " " +
                                    resultSet.getString(2) + " " +
                                    resultSet.getString(3) + " " +
                                    resultSet.getString(4)
                    );
                }
            } catch (SQLException e){
                System.out.println("Something went wrong with retrieving data from database ");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with connection to DataBase: " + e.getMessage());
        }


    }

    private static PreparedStatement createPreparedStatement(Connection con, String querry) throws SQLException {
        return con.prepareStatement(querry);
    }

//    public static PreparedStatement insertInto(Connection con, String table, String name) throws SQLException {
//        PreparedStatement preparedStatement = con.prepareStatement(
//                "INSERT INTO " + table + " VALUES ('" + name  + "')"
//        );
//        return preparedStatement;
//    }
}
