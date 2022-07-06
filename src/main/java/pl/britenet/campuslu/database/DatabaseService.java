package pl.britenet.campuslu.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {
    private HikariDataSource hikariDataSource;

    private void configure() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/july-kiel-lub-shop");
        config.setUsername("root");
        config.setPassword("");
        this.hikariDataSource = new HikariDataSource(config);
    }

    public DatabaseService() {
        this.configure();
    }

    public void performDML(String dml) {
        try (Connection connection = hikariDataSource.getConnection() ;
             PreparedStatement statement = connection.prepareStatement(dml)) {
            statement.execute();
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    public<T> T performSQL(String sql, ResultParser<T> resultParser) {
        try (Connection connection = hikariDataSource.getConnection() ;
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return resultParser.parse(statement.executeQuery());
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
