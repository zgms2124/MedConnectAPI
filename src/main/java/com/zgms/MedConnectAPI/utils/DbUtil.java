package com.zgms.MedConnectAPI.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {
    private static DruidDataSource ds;
    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        try (InputStream inputStream = DbUtil.class.getResourceAsStream("/database.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("Failed to load database configuration file.");
            }
            properties.load(inputStream);
            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            throw new RuntimeException("Error reading database configuration file.", e);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing DruidDataSource.", e);
        }
    }

    public static Connection getConnection() {
        Connection connection = THREAD_LOCAL.get();
        try {
            if (connection == null || connection.isClosed()) {
                connection = ds.getConnection();
                THREAD_LOCAL.set(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to obtain database connection.", e);
        }
        return connection;
    }

    public static void begin() {
        Connection connection = getConnection();
        try {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to begin transaction.", e);
        }
    }

    public static void commit() {
        Connection connection = getConnection();
        try {
            if (connection != null && !connection.getAutoCommit()) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to commit transaction.", e);
        } finally {
            closeAll(connection, null, null);
        }
    }

    public static void rollback() {
        Connection connection = getConnection();
        try {
            if (connection != null && !connection.getAutoCommit()) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to rollback transaction.", e);
        } finally {
            closeAll(connection, null, null);
        }
    }

    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                THREAD_LOCAL.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
