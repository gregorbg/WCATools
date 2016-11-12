package com.suushiemaniac.cubing.wca.result;

import com.suushiemaniac.cubing.wca.comp.Competition;
import com.suushiemaniac.cubing.wca.comp.Event;
import com.suushiemaniac.cubing.wca.comp.Round;
import com.suushiemaniac.cubing.wca.util.ArrayUtils;
import com.suushiemaniac.cubing.wca.util.WcaDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Scramble {
    public static Scramble fromID(int id) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Scrambles WHERE scrambleId = ?");
            stat.setInt(1, id);

            ResultSet res = db.query(stat);

            return res.next() ? Scramble.fromPointedResult(res) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Scramble fromPointedResult(ResultSet res) {
		try {
			return new Scramble(
					res.getInt("scrambleId"),
					res.getInt("scrambleNum"),
					res.getString("competitionId"),
					res.getString("eventId"),
					res.getString("roundId"),
					res.getString("groupId"),
					res.getString("scramble"),
					res.getBoolean("isExtra")
			);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

    public static Scramble[] forResult(Result result) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat;
            ResultSet res;
            //Guess group
            stat = db.prepareStatement("SELECT DISTINCT personName FROM Results WHERE competitionId = ? AND eventId = ? AND roundId = ? ORDER BY personName ASC");
            stat.setString(1, result.getCompetitionId());
            stat.setString(2, result.getEventId());
            stat.setString(3, result.getRoundId());

            res = db.query(stat);
            List<String> nameList = new ArrayList<>();

            while (res.next()) {
                nameList.add(res.getString("personName"));
            }

            stat = db.prepareStatement("SELECT count(DISTINCT groupId) AS count FROM Scrambles WHERE competitionId = ? AND eventId = ? AND roundId = ?");
            stat.setString(1, result.getCompetitionId());
            stat.setString(2, result.getEventId());
            stat.setString(3, result.getRoundId());

            res = db.query(stat);

            int groupCount = res.next() ? res.getInt("count") : 1;
            int groupPos = nameList.indexOf(result.getPersonName());

            int perGroup = nameList.size() / groupCount;

            int guessedGroup = groupPos / perGroup;
            //n * perGroup = groupPos

            stat = db.prepareStatement("SELECT * FROM Scrambles WHERE competitionId = ? AND eventId = ? AND roundId = ? AND groupId = ?");
            stat.setString(1, result.getCompetitionId());
            stat.setString(2, result.getEventId());
            stat.setString(3, result.getRoundId());
            stat.setString(4, ArrayUtils.APLHABET[guessedGroup]);

            res = db.query(stat);
            List<Scramble> scrambleList = new ArrayList<>();

            while (res.next()) {
                if (!res.getBoolean("isExtra")) {
                    scrambleList.add(Scramble.fromPointedResult(res));
                }
            }

            return scrambleList.toArray(new Scramble[scrambleList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Scramble[] allForEvent(Event event) {
        try {
            WcaDatabase db = WcaDatabase.inst();

            PreparedStatement stat = db.prepareStatement("" +
                    "SELECT scr.* FROM Scrambles AS scr " +
					"INNER JOIN Competitions AS comp ON scr.competitionId = comp.id " +
					"WHERE scr.eventId = ?");

            stat.setString(1, event.getId());

            ResultSet res = stat.executeQuery();
            List<Scramble> scrambleList = new ArrayList<>();

            while (res.next()) {
                scrambleList.add(Scramble.fromPointedResult(res));
            }

            return scrambleList.toArray(new Scramble[scrambleList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Scramble filterForSingle(Scramble[] scrambles, Result result) {
        if (scrambles.length != ArrayUtils.trim(ArrayUtils.autobox(result.getValues()), 0).length) return null;

        int solveIndex = ArrayUtils.index(ArrayUtils.autobox(result.getValues()), result.getBest());
        return scrambles[solveIndex];
    }

    //Database values
    private int id;
    private int scrambleNum;

    private String competitionId;
    private String eventId;
    private String roundId;
    private String groupId;
    private String scramble;

    private boolean isExtra;

    //Derived properties
    private Competition competition;

    private Event event;

    private Round round;

    public Scramble(int id, int scrambleNum, String competitionId, String eventId, String roundId, String groupId, String scramble, boolean isExtra) {
        this.id = id;
        this.scrambleNum = scrambleNum;
        this.competitionId = competitionId;
        this.eventId = eventId;
        this.roundId = roundId;
        this.groupId = groupId;
        this.scramble = scramble;
        this.isExtra = isExtra;
    }

    public int getId() {
        return id;
    }

    public int getScrambleNum() {
        return scrambleNum;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public Competition getCompetition() {
        if (competition == null || !competition.getId().equals(competitionId)) {
            competition = Competition.fromId(competitionId);
        }

        return competition;
    }

    public String getEventId() {
        return eventId;
    }

    public Event getEvent() {
        if (event == null || !event.getId().equals(eventId)) {
            event = Event.fromID(eventId);
        }

        return event;
    }

    public String getRoundId() {
        return roundId;
    }

    public Round getRound() {
        if (round == null || !round.getId().equals(roundId)) {
            round = Round.fromID(roundId);
        }

        return round;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getScramble() {
        return scramble;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public void setScrambleNum(int scrambleNum) {
        this.scrambleNum = scrambleNum;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    public void setExtra(boolean extra) {
        isExtra = extra;
    }
}
