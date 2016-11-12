package com.suushiemaniac.cubing.wca.util.globe;

import com.suushiemaniac.cubing.wca.result.Result;
import com.suushiemaniac.cubing.wca.util.WcaDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Country {
    public static Country fromID(String id) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Countries WHERE id = ?");
            stat.setString(1, id);

            ResultSet res = db.query(stat);

            return res.next() ? Country.fromPointedResult(res) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Country fromPointedResult(ResultSet res) {
        try {
            return new Country(
				res.getString("id"),
				res.getString("name"),
				res.getString("continentId"),
				res.getString("iso2"),
				res.getInt("latitude"),
				res.getInt("longitude"),
				res.getInt("zoom")
			);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Country[] fromName(String name) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Countries WHERE name LIKE ?");
            stat.setString(1, "%" + name + "%");

            ResultSet res = db.query(stat);
            List<Country> countryList = new ArrayList<>();

            while (res.next()) {
                countryList.add(Country.fromPointedResult(res));
            }

            return countryList.toArray(new Country[countryList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Database values
    private String id;
    private String name;
    private String continentId;
    private String iso2;

    private int latitude;
    private int longitude;
    private int zoom;

    //Derived properties
    private Continent continent;

    private GeoCoord coordinates;

    public Country(String id, String name, String continentId, String iso2, int latitude, int longitude, int zoom) {
        this.id = id;
        this.name = name;
        this.continentId = continentId;
        this.iso2 = iso2;
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

    public String getContinentId() {
        return continentId;
    }

    public Continent getContinent() {
        if (continent == null || !continent.getId().equals(continentId)) {
            continent = Continent.fromID(continentId);
        }

        return continent;
    }

    public String getIso2() {
        return iso2;
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

    public void setContinentId(String continentId) {
        this.continentId = continentId;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
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