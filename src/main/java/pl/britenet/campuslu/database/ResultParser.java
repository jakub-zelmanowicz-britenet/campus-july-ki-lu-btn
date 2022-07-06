package pl.britenet.campuslu.database;

import java.sql.ResultSet;

public interface ResultParser<T> {

    T parse(ResultSet resultSet);

}
