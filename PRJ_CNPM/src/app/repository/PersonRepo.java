package app.repository;

import app.model.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonRepo {
    List<Person> getAll() throws SQLException;
}
