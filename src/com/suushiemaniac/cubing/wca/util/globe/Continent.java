package com.suushiemaniac.cubing.wca.util.globe;

import com.suushiemaniac.cubing.wca.util.WcaDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.M;

public class Continent {
    public static Continent fromID(String id) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Continents WHERE id = ?");
            stat.setString(1, id);

            ResultSet res = db.query(stat);

            return res.next() ? new Continent(
                    res.getString("id"),
                    res.getString("name"),
                    res.getString("recordName"),
                    res.getInt("latitude"),
                    res.getInt("longitude"),
                    res.getInt("zoom")
            ) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Continent[] fromName(String name) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT id FROM Continents WHERE name LIKE ?");
            stat.setString(1, "%" + name + "%");

            ResultSet res = db.query(stat);
            List<Continent> continentList = new ArrayList<>();

            while (res.next()) {
                continentList.add(Continent.fromID(res.getString("id")));
            }

            return continentList.toArray(new Continent[continentList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Database values
    private String id;
    private String name;
    private String recordName;

    private int latitude;
    private int longitude;
    private int zoom;

    //Derived properties
    private GeoCoord coordinates;

    public Continent(String id, String name, String recordName, int latitude, int longitude, int zoom) {
        this.id = id;
        this.name = name;
        this.recordName = recordName;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public GeoCoord getCoordinates() {
        if (coordinates == null || coordinates.getLatitude() != latitude || coordinates.getLongitude() != longitude) {
            coordinates = new GeoCoord(latitude, longitude);
        }

        return coordinates;
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

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}