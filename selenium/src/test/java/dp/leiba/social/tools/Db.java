package dp.leiba.social.tools;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Db.
 */
public class Db
{

    /**
     * Config.
     */
    public static final String FILE     = "jdbc:sqlite:X:\\social\\social.db";
    public static final String DRIVER   = "org.sqlite.JDBC";

    /**
     * Connection.
     */
    private static Connection _connection;

    /**
     * Get single connection.
     *
     * @return Connection.
     *
     * @throws Exception
     */
    public static Connection con() throws Exception
    {
        if (_connection == null) {
            Class.forName(DRIVER);
            _connection = DriverManager.getConnection(FILE);
        }

        return _connection;
    }
}
