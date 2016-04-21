package com.suushiemaniac.cubing.wca.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GeoCoord {
    public static GeoCoord fromSQLResult(ResultSet res) {
        try {
            return new GeoCoord(res.getInt("latitude"), res.getInt("longitude"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int latitude;
    private int longitude;

    public GeoCoord(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
