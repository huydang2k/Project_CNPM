package app.service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BaseService<T> {
     ArrayList<T> findAll() throws SQLException;
     T findById(int maDs) throws SQLException;
     int insert(T t) throws SQLException;
     int update(T t) throws SQLException;
     int deleteById(int maDS) throws SQLException;
}
