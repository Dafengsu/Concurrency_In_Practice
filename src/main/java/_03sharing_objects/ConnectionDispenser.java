package _03sharing_objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/28
 */
public class ConnectionDispenser {
    static String DB_URL = "jdbc";
    private ThreadLocal<Connection> connectionHolder
            = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to acquire Connection, e");
        }
    });
    public Connection getConnection() {

        return connectionHolder.get();
    }
}
