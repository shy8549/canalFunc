package com.pg.demo;

import java.sql.*;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description:
 * @className: PgSqlDemo
 * @date:2022/1/12 20:17
 */
public class PgSqlDemo {
    private String url = "jdbc:postgresql://10.173.104.205:5432/gpadmin";
    private String username = "gpadmin";
    private String password = "Tong#2021";
    private Connection connection = null;

    public Connection getConn() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public ResultSet query(Connection conn, String sql) {
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        try {
            pStatement = conn.prepareStatement(sql);
            rs = pStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean queryUpdate(Connection conn, String sql) {
        PreparedStatement pStatement = null;
        int rs = 0;
        try {
            pStatement = conn.prepareStatement(sql);
            rs = pStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (rs > 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws SQLException{
//
        PgSqlDemo pgtool = new PgSqlDemo();
        Connection myconn = pgtool.getConn();
        String sqlstr = "select * from web_sales;";
        myconn.prepareStatement(sqlstr).executeQuery();
//        myconn.close();
    }
}
