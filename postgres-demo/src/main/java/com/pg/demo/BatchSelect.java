package com.pg.demo;

import java.sql.*;
import java.util.Arrays;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description:
 * @className: BatchSelect
 * @date:2022/1/12 22:50
 */
public class BatchSelect {
    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://10.173.104.205:5432/gpadmin", "gpadmin", "Tong#2021");
             PreparedStatement psSelect = conn.prepareStatement("select * from web_sales;")) {

            // commit all or rollback all, if any errors
//            conn.setAutoCommit(false); // default true
            conn.setAutoCommit(true);

            // Run list of select commands
            for (int i = 0; i < 1000; i++) {
                psSelect.addBatch();
            }

            int[] count = psSelect.executeBatch();

            System.out.println(Arrays.toString(count));

            conn.commit();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
