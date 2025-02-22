package utility;
import java.sql.*;
public class DBUtility {

        private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
        private static final String DB_USER = "user";  // Change to your MySQL username
        private static final String DB_PASSWORD = "userpassword";  // Change to your MySQL password


    static {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found. Add it to your classpath.", e);
        }
    }

    /**
     * Establishes and returns a database connection.
     * Call this method whenever you need to connect to MySQL.
     */
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connection established successfully!");
            return conn;
        } catch (SQLException e) {
            System.err.println("Database connection failed.");
            e.printStackTrace();
            return null;
        }
    }

    }


