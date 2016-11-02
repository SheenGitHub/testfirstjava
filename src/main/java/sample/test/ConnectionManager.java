package sample.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/8/5.
 */
public class ConnectionManager {
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>(){
        @Override
        protected Connection initialValue() {
            Connection conn = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test"
                        , "sheen", "zxj987147");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return conn;
        }
    };

    public static Connection getConnection() {
        return connectionThreadLocal.get();
    }

    public static void setConnection(Connection conn) {
        connectionThreadLocal.set(conn);
    }

    public static void main(String[] args) {
        Connection connection = ConnectionManager.getConnection();

    }

}
