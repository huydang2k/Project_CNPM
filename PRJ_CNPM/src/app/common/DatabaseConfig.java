package app.common;

public interface DatabaseConfig {
     String DRIVER = "com.mysql.cj.jdbc.Driver"; // chứa đường dẫn đến thư viện jdbc
     String URL_DATABASE = "jdbc:mysql://localhost:3306/quan_ly_pt_pq"; // đường dẫn để kết nối đến schema
     String USERNAME = "root";
     String PASSWORD = "root"; // mật khẩu của mysql
}
