package design_patterns.creational.singleton.db_example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private record Student(int id, String name, int age) {}

    public static void main(String[] args) {
        DBSingleton dbSingleton = DBSingleton.getInstance();
        Connection conn = dbSingleton.getConnection();

        try {
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE students (ID int primary key, name varchar(255), age int)");

            System.out.println("Table created");

            int rows = statement.executeUpdate("INSERT INTO students VALUES (1, 'John', 17)");

            if (rows > 0) {
                System.out.println("Student inserted");
            }

            Thread.sleep(2000);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            if (resultSet.first()) {
                Student student = new Student(resultSet.getInt("ID"), resultSet.getString("name"), resultSet.getInt("age"));
                System.out.println(student);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
