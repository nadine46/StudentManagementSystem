package org.example.repository;

import org.example.model.Student;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private final DataSource dataSource;

    public StudentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Student student) {
        String sql = "INSERT INTO students (full_name, age, email) VALUES (?, ?, ?)";

        String fullName = student.getFirstName() + " " + student.getLastName();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, fullName);
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving student", e);
        }
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, full_name, age, email FROM students ORDER BY id";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                students.add(mapStudent(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving students", e);
        }

        return students;
    }

    public Student findById(int id) {
        String sql = "SELECT id, full_name, age, email FROM students WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapStudent(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving student", e);
        }

        return null;
    }

    private Student mapStudent(ResultSet resultSet) throws SQLException {
        String fullName = resultSet.getString("full_name");
        String[] names = fullName.split(" ", 2);

        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setFirstName(names[0]);
        student.setLastName(names.length > 1 ? names[1] : "");
        student.setAge(resultSet.getInt("age"));
        student.setEmail(resultSet.getString("email"));

        return student;
    }
}