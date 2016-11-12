package com.suushiemaniac.cubing.wca.comp;

import com.suushiemaniac.cubing.wca.person.Person;
import com.suushiemaniac.cubing.wca.util.globe.GeoCoord;
import com.suushiemaniac.cubing.wca.util.JSONPair;
import com.suushiemaniac.cubing.wca.util.SmallDate;
import com.suushiemaniac.cubing.wca.util.WcaDatabase;
import com.suushiemaniac.cubing.wca.util.globe.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Competition {
    public static Competition fromId(String id) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Competitions WHERE id = ?");
            stat.setString(1, id);

            ResultSet res = db.query(stat);

            return res.next() ? new Competition(
                    res.getString("id"),
                    res.getString("name"),
                    res.getString("cityName"),
                    res.getString("countryId"),
                    res.getString("information"),
                    res.getString("eventSpecs"),
                    res.getString("wcaDelegate"),
                    res.getString("organiser"),
                    res.getString("venue"),
                    res.getString("venueAddress"),
                    res.getString("venueDetails"),
                    res.getString("external_website"),
                    res.getString("cellName"),
                    res.getInt("year"),
                    res.getInt("month"),
                    res.getInt("day"),
                    res.getInt("endMonth"),
                    res.getInt("endDay"),
                    res.getInt("latitude"),
                    res.getInt("longitude")
            ) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Competition[] fromName(String name) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT id FROM Competitions WHERE name LIKE ?");
            stat.setString(1, "%" + name + "%");

            ResultSet res = db.query(stat);
            List<Competition> competitionList = new ArrayList<>();

            while (res.next()) {
                competitionList.add(Competition.fromId(res.getString("id")));
            }

            return competitionList.toArray(new Competition[competitionList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Database values
    private String id;
    private String name;
    private String cityName;
    private String countryId;
    private String information;
    private String eventSpecs;
    private String wcaDelegate;
    private String organiser;
    private String venue;
    private String venueAddress;
    private String venueDetails;
    private String website;
    private String cellName;

    private int year;
    private int month;
    private int day;
    private int endMonth;
    private int endDay;
    private int latitude;
    private int longitude;

    //Derived properties
    private Country country;

    private Event[] events;

    private Person[] wcaDelegates;
    private Person[] organisers;

    private SmallDate startDate;
    private SmallDate endDate;

    private GeoCoord coordinates;

    public Competition(String id, String name, String cityName, String countryId, String information, String eventSpecs, String wcaDelegate, String organiser, String venue, String venueAddress, String venueDetails, String website, String cellName, int year, int month, int day, int endMonth, int endDay, int latitude, int longitude) {
        this.id = id;
        this.name = name;
        this.cityName = cityName;
        this.countryId = countryId;
        this.information = information;
        this.eventSpecs = eventSpecs;
        this.wcaDelegate = wcaDelegate;
        this.organiser = organiser;
        this.venue = venue;
        this.venueAddress = venueAddress;
        this.venueDetails = venueDetails;
        this.website = website;
        this.cellName = cellName;
        this.year = year;
        this.month = month;
        this.day = day;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getCountryId() {
        return countryId;
    }

    public Country getCountry() {
        if (country == null || !country.getId().equals(countryId)) {
            country = Country.fromID(countryId);
        }

        return country;
    }

    public String getInformation() {
        return information;
    }

    public String getEventSpecs() {
        return eventSpecs;
    }

    public Event[] getEvents() {
        if (events == null || events.length <= 0) {
            String[] eventIds = eventSpecs.contains("=///0/ ") ? eventSpecs.split("=///0/ ") : eventSpecs.split("\\s+?");
            List<Event> eventList = new ArrayList<>();

            for (String eventId : eventIds) {
                eventList.add(Event.fromID(eventId));
            }

            events = eventList.toArray(new Event[eventList.size()]);
        }

        return events;
    }

    public String getWcaDelegate() {
        return wcaDelegate;
    }

    public Person[] getWcaDelegates() {
        if (wcaDelegates == null || wcaDelegates.length <= 0) {
            String[] delegPairs = wcaDelegate.split("(?<=\\])\\s+?(?=\\[)");
            List<Person> delegPersons = new ArrayList<>();

            for (String delegJson : delegPairs) {
                JSONPair<String, String> delegPair = JSONPair.fromString(delegJson);
                Collections.addAll(delegPersons, Person.fromName(delegPair.getKey()));
            }

            wcaDelegates = delegPersons.toArray(new Person[delegPersons.size()]);
        }

        return wcaDelegates;
    }

    public String getOrganiser() {
        return organiser;
    }

    public Person[] getOrganisers() {
        if (organisers == null || organisers.length <= 0) {
            if (organiser.length() > 0 && organiser.startsWith("[") && organiser.endsWith("]") && organiser.contains("{") && organiser.contains("}")) {
                organisers = wcaDelegates;
            } else {
                String[] orgPairs = organiser.split("(?<=\\])\\s+?(?=\\[)");
                List<Person> orgPersons = new ArrayList<>();

                for (String orgJson : orgPairs) {
                    JSONPair<String, String> orgPair = JSONPair.fromString(orgJson);
                    Collections.addAll(orgPersons, Person.fromName(orgPair.getKey()));
                }

                organisers = orgPersons.toArray(new Person[orgPersons.size()]);
            }
        }

        return organisers;
    }

    public String getVenue() {
        return venue;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public String getVenueDetails() {
        return venueDetails;
    }

    public String getWebsite() {
        return website;
    }

    public String getCellName() {
        return cellName;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public SmallDate getStartDate() {
        if (startDate == null || startDate.getDay() != day || startDate.getMonth() != month || startDate.getYear() != year) {
            startDate = new SmallDate(day, month, year);
        }

        return startDate;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public int getEndDay() {
        return endDay;
    }

    public SmallDate getEndDate() {
        if (endDate == null || endDate.getDay() != endDay || endDate.getMonth() != endMonth || endDate.getYear() != (year + (endMonth < month ? 1 : 0))) {
            endDate = new SmallDate(endDay, endMonth, year + (endMonth < month ? 1 : 0));
        }

        return endDate;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setEventSpecs(String eventSpecs) {
        this.eventSpecs = eventSpecs;
    }

    public void setWcaDelegate(String wcaDelegate) {
        this.wcaDelegate = wcaDelegate;
    }

    public void setOrganiser(String organiser) {
        this.organiser = organiser;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public void setVenueDetails(String venueDetails) {
        this.venueDetails = venueDetails;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
