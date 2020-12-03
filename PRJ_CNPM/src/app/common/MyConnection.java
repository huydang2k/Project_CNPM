package app.common;

import java.sql.*;

public class MyConnection{
    /**
     * Các bước để connect tới db:
     * Bước 1: kiểm tra driver jdbc với hàm driverTest();
     * Bước 2: thực hiện connect với Db bằng hàm connectDB cần có url db, username, password
     * Bước 3: parepar và pareparUpdate dùng để lấy ra đối tượng dùng để thực thi các lệnh query trên db
     * Bước 4: đóng kết nối bằng hàm closeConnection();
     */

    public static Connection connection;

    //kiểm tra driver jdbc đã tồn tại trong project dedekeet nối
    public void driverTest() throws ClassNotFoundException {
        try {
            Class.forName(DatabaseConfig.DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("JDBC Driver not found" + ex.getMessage());
        }
    }

    // hàm để kết nối database
    public Connection connectDB() throws ClassNotFoundException, SQLException {
        driverTest();
        try {
            //cú pháp kết nối datanase

            connection = DriverManager.getConnection(DatabaseConfig.URL_DATABASE, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
            if (connection != null) System.out.println("Connect DataBase successfully");
        } catch (Exception e) {
            throw new SQLException("Connect DB fail " + e.getMessage());
        }
        return connection;
    }

    //hàm dùng để lấy ra prepareStatment để thực hiện các câu lệnh query


    //dùng để lấy ra prepare cho các thao tác thêm sửa xóa query Update


    // đóng kết nối database
    public void closeConnection() throws SQLException {
        if(connection != null) {
            connection.close();
            System.out.println("Connection is closed");
        }
    }
}
