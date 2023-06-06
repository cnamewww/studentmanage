package manage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO {
    private Connection connection;

    public TeacherDAO() {
        try {
            connection = DBHelper.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 添加教师信息
    public boolean addTeacher(Teacher teacher) {
        String sql = "INSERT INTO teachers (name, gender, age, major, phone) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getGender());
            statement.setInt(3, teacher.getAge());
            statement.setString(4, teacher.getMajor());
            statement.setString(5, teacher.getPhone());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 根据教师信息id更新教师信息
    public boolean updateTeacher(Teacher teacher) {
        String sql = "UPDATE teachers SET name = ?, gender = ?, age = ?, major = ?, phone = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getGender());
            statement.setInt(3, teacher.getAge());
            statement.setString(4, teacher.getMajor());
            statement.setString(5, teacher.getPhone());
            statement.setInt(6, teacher.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 根据教师信息id删除教师信息
    public boolean deleteTeacherById(int id) {
        String sql = "DELETE FROM teachers WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取所有教师信息的列表
    public ResultSet getAllTeachers() {
        String sql = "SELECT * FROM teachers";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 获取所有教师信息的表格模型
    public DefaultTableModel getAllTeachersModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("name");
        model.addColumn("gender");
        model.addColumn("age");
        model.addColumn("major");
        model.addColumn("phone");
        ResultSet resultSet = getAllTeachers();
        try {
            while (resultSet.next()) {
                Object[] rowData = new Object[6];
                rowData[0] = resultSet.getInt("id");
                rowData[1] = resultSet.getString("name");
                rowData[2] = resultSet.getString("gender");
                rowData[3] = resultSet.getInt("age");
                rowData[4] = resultSet.getString("major");
                rowData[5] = resultSet.getString("phone");
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
}