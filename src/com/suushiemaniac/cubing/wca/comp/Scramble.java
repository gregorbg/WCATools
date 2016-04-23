package com.suushiemaniac.cubing.wca.comp;

import com.suushiemaniac.cubing.wca.util.WcaDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Scramble {
    public static Scramble fromID(int id) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Scrambles WHERE scrambleId = ?");
            stat.setInt(1, id);

            ResultSet res = db.query(stat);

            return res.next() ? new Scramble(
                    res.getInt("id"),
                    res.getInt("scrambleNum"),
                    Competition.fromId(res.getString("competitionId")),
                    Event.fromID(res.getString("eventId")),
                    res.getBoolean("isExtra"),
                    Round.fromID(res.getString("roundId")),
                    res.getString("groupId"),
                    res.getString("scramble")
            ) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int id;
    private int scrambleNum;

    private Competition comp;

    private Event event;

    private boolean isExtra;

    private Round roundId;

    private String groupId;
    private String scrambleSeq;

    public Scramble(int id, int scrambleNum, Competition comp, Event event, boolean isExtra, Round roundId, String groupId, String scrambleSeq) {
        this.id = id;
        this.scrambleNum = scrambleNum;
        this.comp = comp;
        this.event = event;
        this.roundId = roundId;
        this.groupId = groupId;
        this.isExtra = isExtra;
        this.scrambleSeq = scrambleSeq;
    }

    public int getId() {
        return id;
    }

    public int getScrambleNum() {
        return scrambleNum;
    }

    public Competition getComp() {
        return comp;
    }

    public Event getEvent() {
        return event;
    }

    public Round getRoundId() {
        return roundId;
    }

    public String getGroupId() {
        return groupId;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public String getScrambleSeq() {
        return scrambleSeq;
    }

    public void setScrambleNum(int scrambleNum) {
        this.scrambleNum = scrambleNum;
    }

    public void setComp(Competition comp) {
        this.comp = comp;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setRoundId(Round roundId) {
        this.roundId = roundId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setExtra(boolean extra) {
        isExtra = extra;
    }

    public void setScrambleSeq(String scrambleSeq) {
        this.scrambleSeq = scrambleSeq;
    }
}
