package sample.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/8/5.
 */
public class TestDao {
    private Connection connection;
    public void addTopic() throws SQLException {
        Statement statement = connection.createStatement();
    }
}
