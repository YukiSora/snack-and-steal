package Megumin.Database;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static Database database;
    private Connection connection;

    private Database(String url, String user, String password) throws SQLException {
        connection = (Connection)DriverManager.getConnection(url, user, password);
    }

    public static void createDatabase(String url, String user, String password) throws SQLException {
        database = new Database(url, user, password);
    }

    public static Database getInstance() {
        return database;
    }

    public ResultSet query(String sql) throws SQLException {
        ResultSet result = null;

        result = connection.createStatement().executeQuery(sql);

        return result;
    }

    public int update(String sql) throws SQLException {
        int result = -1;

        result = connection.createStatement().executeUpdate(sql);

        return result;
    }
}
