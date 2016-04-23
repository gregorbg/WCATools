package com.suushiemaniac.cubing.wca.util.globe;

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

            return res.next() ? new Country(
                    res.getString("id"),
                    res.getString("name"),
                    res.getString("iso2"),
                    Continent.fromID(res.getString("continentId")),
                    GeoCoord.fromSQLResult(res),
                    res.getInt("zoom")
            ) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Country[] fromName(String name) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT id FROM Countries WHERE name LIKE ?");
            stat.setString(1, "%" + name + "%");

            ResultSet res = db.query(stat);
            List<Country> countryList = new ArrayList<>();

            while (res.next()) {
                countryList.add(Country.fromID(res.getString("id")));
            }

            return countryList.toArray(new Country[countryList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String id;
    private String name;
    private String iso2;

    private Continent continent;

    private GeoCoord coords;

    private int zoom;

    public Country(String id, String name, String iso2, Continent continent, GeoCoord coords, int zoom) {
        this.id = id;
        this.name = name;
        this.iso2 = iso2;
        this.continent = continent;
        this.coords = coords;
        this.zoom = zoom;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIso2() {
        return iso2;
    }

    public Continent getContinent() {
        return continent;
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

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public void setCoords(GeoCoord coords) {
        this.coords = coords;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}