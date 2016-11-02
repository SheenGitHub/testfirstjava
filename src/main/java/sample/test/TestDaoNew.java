package sample.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/8/5.
 */
public class TestDaoNew {
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    public static Connection getConnection() {
        if (connectionThreadLocal.get() == null) {
            Connection connection = getConnection();
            connectionThreadLocal.set(connection);
            return connection;
        }else {
            return connectionThreadLocal.get();
        }
    }

    public void addTopic() throws SQLException {
        Statement stat = getConnection().createStatement();
    }


}
