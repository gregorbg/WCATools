package com.suushiemaniac.cubing.wca.util;

import java.sql.*;

public class WcaDatabase {
    public static WcaDatabase singleton;

    public static WcaDatabase inst() {
        if (singleton == null) singleton = new WcaDatabase();
        return singleton;
    }

    private Connection conn;

    private WcaDatabase() {
        //TODO move to separate config
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wca-export?user=root&password=localsql");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
