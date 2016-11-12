package com.suushiemaniac.cubing.wca.util;

import java.sql.*;

public class WcaDatabase {
    private static WcaDatabase singleton;
    private static String defaultJdbc = "jdbc:mysql://localhost:3306/wca-export?user=root&password=localsql&useSSL=false";

    public static WcaDatabase inst() {
        if (singleton == null || !singleton.getJdbcString().equals(defaultJdbc)) singleton = new WcaDatabase(defaultJdbc);
        return singleton;
    }

    public static void setDefaultJdbc(String defaultJdbc) {
        WcaDatabase.defaultJdbc = defaultJdbc;
    }

    private Connection conn;
    private String jdbcString;

    private WcaDatabase(String jdbcString) {
        this.jdbcString = jdbcString;

        try {
            this.conn = DriverManager.getConnection(jdbcString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getJdbcString() {
        return jdbcString;
    }

    public PreparedStatement prepareStatement(String sql) {
        try {
            return this.conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void execute(PreparedStatement stat) {
        try {
            stat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(PreparedStatement stat) {
        try {
            return stat.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}