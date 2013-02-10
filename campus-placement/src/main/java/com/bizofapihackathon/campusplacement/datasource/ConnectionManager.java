package com.bizofapihackathon.campusplacement.datasource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: umeshprasad
 * Date: 10/02/13
 * Time: 8:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionManager {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionManager.class);

    public void close(Connection connection) {

    }


    private static Connection getConnection() throws SQLException {
        Connection connection = PooledConnection.dataSource.getConnection();
        connection.setReadOnly(true);
        LOG.debug("Created New JDBC Connection : connection ={}", connection);
        return connection;
    }

    static class PooledConnection {
        static volatile MysqlConnectionPoolDataSource dataSource = null;

        static {
            initConnectionPool();
        }

        private static void initConnectionPool() {
            try {
                MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
                ds.setServerName("localhost");
                ds.setDatabaseName("hackathon");
                ds.setUser("root");
                ds.setPassword("");
                //Test and then assign
                Connection connection = ds.getPooledConnection().getConnection();
                connection.close();
                dataSource = ds;
            } catch (SQLException e) {
                LOG.error("e", e);
            }
        }
    }


}
