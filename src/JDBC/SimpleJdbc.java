package JDBC;

import java.sql.*;

public class SimpleJdbc {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Connection connection = null;
        Statement statement = null;
//    PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String host = "localhost";
        String username = "root";
        String rootPassword = "q1w2e3r4";

        // Load the JDBC driver
//    Class.forName("com.mysql.jdbc.Driver"); //Deprecated
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        // Establish a connection
        connection = DriverManager
                .getConnection("jdbc:mysql://" + host + "/Users?"
                        + "user=" + username + "&password=" + rootPassword);

//    connection = DriverManager.getConnection
//      ("jdbc:mysql://localhost/Company");
        System.out.println("Database connected");

        // Create a statement
        statement = connection.createStatement();

        // Execute a statement
        resultSet = statement.executeQuery("SELECT name, password FROM users");

        System.out.println("\n Query Results:");
        // Iterate through the result and print the student names
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            String password = resultSet.getString(2);
            System.out.println(name + "\t" + password);
        }

        // Close the connection
        connection.close();
    }
}
