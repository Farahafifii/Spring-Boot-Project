package com.task.sumerge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.task.recommender.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to add a new course
    public void addCourse(Course course) {
        String sql = "INSERT INTO Course (name, description, credit) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, course.getName(), course.getDescription(), course.getCredit());
    }

    // Method to view a course by id
    public Course viewCourse(int id) {
        String sql = "SELECT * FROM Course WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CourseRowMapper(), id);
    }

    // Method to update an existing course
    public void updateCourse(Course course) {
        String sql = "UPDATE Course SET name = ?, description = ?, credit = ? WHERE id = ?";
        jdbcTemplate.update(sql, course.getName(), course.getDescription(), course.getCredit(), course.getId());
    }

    // Method to delete a course by id
    public void deleteCourse(int id) {
        String sql = "DELETE FROM Course WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // RowMapper implementation to map a row in the ResultSet to a Course object
    private static class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setDescription(rs.getString("description"));
            course.setCredit(rs.getInt("credit"));
            return course;
        }
    }
}
