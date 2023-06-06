package manage;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/teacher_management_system";
    private static final String username = "root";
    private static final String password = "123456";

    public static Connection getConnection() throws Exception {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
}