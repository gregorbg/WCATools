package com.suushiemaniac.cubing.wca.util.globe;

import com.suushiemaniac.cubing.wca.util.GeoCoord;
import com.suushiemaniac.cubing.wca.util.WcaDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Continent {
    public static Continent fromID(String id) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Continents WHERE id = ?");
            stat.setString(0, id);

            ResultSet res = db.query(stat);

            return res.next() ? new Continent(
                    res.getString("id"),
                    res.getString("name"),
                    res.getString("recordName"),
                    GeoCoord.fromSQLResult(res),
                    res.getInt("zoom")
            ) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Continent fromName(String name) {
        return Continent.fromID("_" + name);
    }

    private String id;
    private String name;
    private String recordName;

    private GeoCoord coords;

    private int zoom;

    public Continent(String id, String name, String recordName, GeoCoord coords, int zoom) {
        this.id = id;

        this.name = name;
        this.recordName = recordName;
        this.coords = coords;
        this.zoom = zoom;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRecordName() {
        return recordName;
    }

    public GeoCoord getCoords() {
        return coords;
    }

    public int getZoom() {
        return zoom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public void setCoords(GeoCoord coords) {
        this.coords = coords;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}