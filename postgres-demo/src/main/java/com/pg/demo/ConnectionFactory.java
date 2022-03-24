package com.pg.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description:
 * @className: ConnectionFactory
 * @date:2022/1/12 20:37
 */
public class ConnectionFactory {
    String driverClassName = "org.postgresql.Driver";
    String connectionUrl = "jdbc:postgresql://10.173.104.205:5432/gpadmin";
    String dbUser = "gpadmin";
    String dbPwd = "Tong#2021";
    private static ConnectionFactory connectionFactory = null;
    private static Connection conn = null;
    private ConnectionFactory()
    {
        try
        {
            Class.forName(driverClassName);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException
    {
        if (conn == null)
        {
            conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        }
        return conn;
    }
    public static ConnectionFactory getInstance()
    {
        if (connectionFactory == null)
        {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
}
