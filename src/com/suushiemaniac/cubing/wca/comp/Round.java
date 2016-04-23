package com.suushiemaniac.cubing.wca.comp;

import com.suushiemaniac.cubing.wca.util.WcaDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Round {
    private static Round[] finalRounds;

    public static Round[] listInst() {
        if (finalRounds.length <= 0) {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Rounds");

            ResultSet res = db.query(stat);
            List<Round> roundList = new ArrayList<>();

            try {
                while (res.next()) {
                    roundList.add(new Round(
                            res.getString("id"),
                            res.getString("name"),
                            res.getString("cellName"),
                            res.getInt("rank")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            finalRounds = roundList.toArray(new Round[roundList.size()]);
        }

        return finalRounds;
    }

    public static Round fromID(String id) {
        for (Round round : listInst()) {
            if (round.getId().equals(id)) {
                return round;
            }
        }

        return null;
    }

    public static Round[] fromName(String name) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT id FROM Rounds WHERE name LIKE ?");
            stat.setString(1, "%" + name + "%");

            ResultSet res = db.query(stat);
            List<Round> roundList = new ArrayList<>();

            while (res.next()) {
                roundList.add(Round.fromID(res.getString("id")));
            }

            return roundList.toArray(new Round[roundList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String id;
    private String name;
    private String cellName;

    private int rank;

    public Round(String id, String name, String cellName, int rank) {
        this.id = id;
        this.name = name;
        this.cellName = cellName;
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

    public int getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
