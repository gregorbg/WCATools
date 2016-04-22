package com.suushiemaniac.cubing.wca.comp;

import com.suushiemaniac.cubing.wca.person.Person;
import com.suushiemaniac.cubing.wca.util.GeoCoord;
import com.suushiemaniac.cubing.wca.util.JSONPair;
import com.suushiemaniac.cubing.wca.util.SmallDate;
import com.suushiemaniac.cubing.wca.util.WcaDatabase;
import com.suushiemaniac.cubing.wca.util.globe.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Competition {
    public static Competition fromId(String id) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Competitions WHERE id = ?");
            stat.setString(1, id);

            ResultSet res = db.query(stat);

            String venueString = res.getString("venue");
            JSONPair<String, String> venuePair =
                    venueString.startsWith("[") && venueString.endsWith("]") && venueString.contains("{") && venueString.contains("}")
                            ? JSONPair.fromString(venueString)
                            : new JSONPair<>(venueString, "");

            // TODO convert to list
            JSONPair<String, String> delegPair = JSONPair.fromString(res.getString("wcaDelegate"));

            String orgString = res.getString("organiser");
            JSONPair<String, String> orgPair = orgString.length() > 0 ? JSONPair.fromString(orgString) : delegPair;

            return res.next() ? new Competition(
                    res.getString("id"),
                    res.getString("name"),
                    res.getString("cityName"),
                    res.getString("information"),
                    venuePair.getKey(),
                    venuePair.getValue(),
                    res.getString("venueAddress"),
                    res.getString("venueDetails"),
                    res.getString("website"),
                    res.getString("cellName"),
                    Country.fromID(res.getString("countryId")),
                    new SmallDate(
                            res.getInt("day"),
                            res.getInt("month"),
                            res.getInt("year")
                    ),
                    new SmallDate(
                            res.getInt("endDay"),
                            res.getInt("endMonth"),
                            res.getInt("year") // Attention, may differ!!
                    ),
                    null, // TODO parse: split at either " " or "=///0/ "
                    null, // TODO parse from JSON-ish key-value pair
                    null, // TODO parse from JSON-ish key-value pair
                    GeoCoord.fromSQLResult(res)
            ) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Competition fromName(String name) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT id FROM Competitions WHERE name = ?");
            stat.setString(1, name);

            ResultSet res = db.query(stat);

            return res.next() ? Competition.fromId(res.getString("id")) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String id;
    private String name;
    private String cityName;
    private String information;
    private String venue;
    private String venueWebsite;
    private String venueAddress;
    private String venueDetails;
    private String compWebsite;
    private String cellName;

    private Country country;

    private SmallDate startDate;
    private SmallDate endDate;

    private Event[] events;

    private Person[] delegates;
    private Person[] organisers;

    private GeoCoord coords;

    public Competition(String id, String name, String cityName, String information, String venue, String venueWebsite, String venueAddress, String venueDetails, String compWebsite, String cellName, Country country, SmallDate startDate, SmallDate endDate, Event[] events, Person[] delegates, Person[] organisers, GeoCoord coords) {
        this.id = id;
        this.name = name;
        this.cityName = cityName;
        this.information = information;
        this.venue = venue;
        this.venueWebsite = venueWebsite;
        this.venueAddress = venueAddress;
        this.venueDetails = venueDetails;
        this.compWebsite = compWebsite;
        this.cellName = cellName;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.events = events;
        this.delegates = delegates;
        this.organisers = organisers;
        this.coords = coords;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCityName() {
        return cityName;
    }

    public String getInformation() {
        return information;
    }

    public String getVenue() {
        return venue;
    }

    public String getVenueWebsite() {
        return venueWebsite;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public String getVenueDetails() {
        return venueDetails;
    }

    public String getCompWebsite() {
        return compWebsite;
    }

    public String getCellName() {
        return cellName;
    }

    public Country getCountry() {
        return country;
    }

    public SmallDate getStartDate() {
        return startDate;
    }

    public SmallDate getEndDate() {
        return endDate;
    }

    public Event[] getEvents() {
        return events;
    }

    public Person[] getDelegates() {
        return delegates;
    }

    public Person[] getOrganisers() {
        return organisers;
    }

    public GeoCoord getCoords() {
        return coords;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setVenueWebsite(String venueWebsite) {
        this.venueWebsite = venueWebsite;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public void setVenueDetails(String venueDetails) {
        this.venueDetails = venueDetails;
    }

    public void setCompWebsite(String compWebsite) {
        this.compWebsite = compWebsite;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setStartDate(SmallDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(SmallDate endDate) {
        this.endDate = endDate;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public void setDelegates(Person[] delegates) {
        this.delegates = delegates;
    }

    public void setOrganisers(Person[] organisers) {
        this.organisers = organisers;
    }

    public void setCoords(GeoCoord coords) {
        this.coords = coords;
    }
}
