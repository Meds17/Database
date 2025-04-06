import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;
/**
 *
 * @author Medha Madhub
 */
public class UniversityDatabaseApp
{
        // Database credentials
    static final String DB_URL = "jdbc:mysql://localhost:3306/university_db";
    static final String USER = "root"; // MySQL username
    static final String PASSWORD = "medhaBest17"; // MySQL password
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Step 2: Establish connection
            System.out.println("Connecting to Database....");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            // Step 3: Create a statement object to execute SQL queries
            stmt = conn.createStatement();
            // Example: Create a new table (for demonstration)
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Students (" +
            "student_id INT PRIMARY KEY AUTO_INCREMENT, " + // Changed to student_id
            "first_name VARCHAR(255) NOT NULL, " +
            "last_name VARCHAR(255) NOT NULL, " +
            "department_id INT NOT NULL, " +
            "grade INT)";
            stmt.executeUpdate(createTableSQL); // Executes the SQL statement
            System.out.println("Table 'Students' created successfully.");
            // Example: Insert a record with first_name, last_name, department_id, and grade
            
            String insertSQL = "INSERT INTO Students(first_name,last_name,department_id,grade)" +
            "VALUES ('John', 'Doe', 1, 90)"; // Here, 1 is an example department_id
            stmt.executeUpdate(insertSQL);
            System.out.println("Inserted a new student record");
            // Example: Select and display records
            String selectSQL = "SELECT student_id, first_name, last_name, department_id, grade FROM Students"; // Changed &#39;id&#39; to &#39;student_id&#39;
            ResultSet rs = stmt.executeQuery(selectSQL);
                while (rs.next()) {
                int studentId = rs.getInt("student_id"); // Changed from &#39;id&#39; to &#39;student_id&#39;
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int departmentId = rs.getInt("department_id"); // Fetching department_id
                int grade = rs.getInt("grade");
                // Display student details
                System.out.println("Student ID: " + studentId + ", First Name: " + firstName + ", LastName: " + lastName + ", Department ID: " + departmentId + ", Grade: " + grade);
                }
            rs.close(); // Close the ResultSet
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
            } finally {
        try {
        // Close statement and connection in the finally block
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        }
    }
}
