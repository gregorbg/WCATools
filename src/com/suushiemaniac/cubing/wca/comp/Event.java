package com.suushiemaniac.cubing.wca.comp;

import com.suushiemaniac.cubing.wca.util.WcaDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private static Event[] finalEvents;

    public static Event[] listInst() {
        if (finalEvents == null || finalEvents.length <= 0) {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Events");

            ResultSet res = db.query(stat);
            List<Event> eventList = new ArrayList<>();

            try {
                while (res.next()) {
                    eventList.add(new Event(
                            res.getString("id"),
                            res.getString("name"),
                            res.getString("cellName"),
                            res.getString("format"),
                            res.getInt("rank")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            finalEvents = eventList.toArray(new Event[eventList.size()]);
        }

        return finalEvents;
    }

    public static Event fromID(String id) {
        for (Event event : listInst()) {
            if (event.getId().equals(id)) {
                return event;
            }
        }

        return null;
    }

    public static Event[] fromName(String name) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT id FROM Events WHERE name LIKE ?");
            stat.setString(1, "%" + name + "%");

            ResultSet res = db.query(stat);
            List<Event> eventList = new ArrayList<>();

            while (res.next()) {
                eventList.add(Event.fromID(res.getString("id")));
            }

            return eventList.toArray(new Event[eventList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String id;
    private String name;
    private String cellName;
    private String timeFormat;

    private int rank;

    public Event(String id, String name, String cellName, String timeFormat, int rank) {
        this.id = id;
        this.name = name;
        this.cellName = cellName;
        this.timeFormat = timeFormat;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCellName() {
        return cellName;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public int getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String toString() {
        return this.cellName;
    }
}