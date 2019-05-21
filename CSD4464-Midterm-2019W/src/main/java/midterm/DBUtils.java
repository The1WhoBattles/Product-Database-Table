package midterm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Database Utility Class
 *
 * @author <ENTER YOUR NAME HERE>
 */
public class DBUtils {

    /**
     * Utility method used to create a Database Connection
     *
     * @return the Connection object
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        String server = "localhost";
        String username = "app";
        String password = "app";
        String database = "sample";
        String jdbc = String.format("jdbc:derby://%s/%s", server, database);
        return DriverManager.getConnection(jdbc, username, password);
    }
}
