package com.suushiemaniac.cubing.wca.time;

import com.suushiemaniac.cubing.wca.util.WcaDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Format {
    private static Format[] finalFormats;

    public static Format[] listInst() {
        if (finalFormats == null || finalFormats.length <= 0) {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Formats");

            ResultSet res = db.query(stat);
            List<Format> formatList = new ArrayList<>();

            try {
                while (res.next()) {
                    formatList.add(new Format(
                            res.getString("id"),
                            res.getString("name")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            finalFormats = formatList.toArray(new Format[formatList.size()]);
        }

        return finalFormats;
    }

    public static Format fromID(String id) {
        for (Format format : listInst()) {
            if (format.getId().equals(id)) {
                return format;
            }
        }

        return null;
    }

    public static Format[] fromName(String name) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT id FROM Formats WHERE name LIKE ?");
            stat.setString(1, name);

            ResultSet res = db.query(stat);
            List<Format> formatList = new ArrayList<>();

            while (res.next()) {
                formatList.add(Format.fromID(res.getString("id")));
            }

            return formatList.toArray(new Format[formatList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String id;
    private String name;

    public Format(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
