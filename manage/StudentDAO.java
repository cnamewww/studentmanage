package manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM students";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String studentId = rs.getString("student_id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String major = rs.getString("major");
                String phone = rs.getString("phone");
                Student student = new Student(id, studentId, name, gender, age, major, phone);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtils.closeConnection(conn);
        }
        return students;
    }

    public boolean addStudent(Student student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO students (student_id, name, gender, age, major, phone) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getGender());
            stmt.setInt(4, student.getAge());
            stmt.setString(5, student.getMajor());
            stmt.setString(6, student.getPhone());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtils.closeConnection(conn);
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE FROM students WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtils.closeConnection(conn);
        }
        return false;
    }

    public boolean updateStudent(Student student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE students SET student_id = ?, name = ?, gender = ?, age = ?, major = ?, phone = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getGender());
            stmt.setInt(4, student.getAge());
            stmt.setString(5, student.getMajor());
            stmt.setString(6, student.getPhone());
            stmt.setInt(7, student.getId());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtils.closeConnection(conn);
        }
        return false;
    }

    public Student getStudentByStudentId(String studentId) {
        Student student = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM students WHERE student_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String major = rs.getString("major");
                String phone = rs.getString("phone");
                student = new Student(id, studentId, name, gender, age, major, phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtils.closeConnection(conn);
        }
        return student;
    }
}
