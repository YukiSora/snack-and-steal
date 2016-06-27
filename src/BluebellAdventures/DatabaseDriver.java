package BluebellAdventures;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.text.SimpleDateFormat;

import Megumin.Database.Database;

public class DatabaseDriver {
    

    public static void main(String[] args) {
        Date dt = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);
        try {
            //Init database
            Database.createDatabase("jdbc:mysql://localhost:3306/BluebellAdventuresRecord", "root", "root");

            //INSERT UPDATE DELETE
            Database.getInstance().update("INSERT INTO Records (Score, Date_Time) VALUE('"+ 1 +"','"+ currentTime +"')");

            //SELECT
            ResultSet result = Database.getInstance().query("SELECT * FROM Records");
            while (result.next()) {
                System.out.println(result.getString("Date_Time"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
