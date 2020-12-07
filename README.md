# Project_CNPM
#Set up JDBC to Connect DB


Project Structure -> Modules -> Dependencies -> Nhấn + bên tay phải -> Jar or directories -> Chọn đến Mysql-connecctor trong directory Lib của PRJ

#Set up DB Config

Chỉnh sửa các thông số trong common.DatabaseConfig:
     String DRIVER = "com.mysql.cj.jdbc.Driver"; // chứa đường dẫn đến thư viện jdbc // tên JDBC: để nguyên
     String URL_DATABASE = "jdbc:mysql://localhost:3306/quan_ly_pt_pq"; // đường dẫn để kết nối đến schema: localhost:<số hiệu cổng>/tên schema
     String USERNAME = "root";
     String PASSWORD = "root"; // mật khẩu của mysql // username và password khi cài đặt MySql
