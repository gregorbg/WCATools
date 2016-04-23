package com.suushiemaniac.cubing.wca.util.globe;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GeoCoord {
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
