package BluebellAdventures;

import java.sql.ResultSet;
import java.sql.SQLException;

import Megumin.Database.Database;

public class DatabaseDriver {
    public static void main(String[] args) {
        try {
            //Init database
            Database.createDatabase("jdbc:mysql://localhost:3306/Database", "username", "password");

            //INSERT UPDATE DELETE
            Database.getInstance().update("INSERT INTO Table VALUE('value')");

            //SELECT
            ResultSet result = Database.getInstance().query("SELECT * FROM Table");
            while (result.next()) {
                System.out.println(result.getString("Column"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
