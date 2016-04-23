package com.suushiemaniac.cubing.wca.result;

import com.suushiemaniac.cubing.wca.comp.Competition;
import com.suushiemaniac.cubing.wca.comp.Event;
import com.suushiemaniac.cubing.wca.comp.Round;
import com.suushiemaniac.cubing.wca.person.Person;
import com.suushiemaniac.cubing.wca.time.Format;

public class Result {
    //Database values
    private String competitionId;
    private String eventId;
    private String roundId;
    private String personName;
    private String personId;
    private String personCountryId;
    private String formatId;
    private String regionalSingleRecord;
    private String regionalAverageRecord;

    private int pos;
    private int best;
    private int average;
    private int[] values;

    //Derived properties
    private Competition competition;

    private Event event;

    private Round round;

    private Person person;

    private Format format;

    private Solve bestSolve;
    private Solve averageSolve;
    private Solve[] solves;

    public Result(String competitionId, String eventId, String roundId, String personName, String personId, String personCountryId, String formatId, String regionalSingleRecord, String regionalAverageRecord, int pos, int best, int average, int[] values) {
        this.competitionId = competitionId;
        this.eventId = eventId;
        this.roundId = roundId;
        this.personName = personName;
        this.personId = personId;
        this.personCountryId = personCountryId;
        this.formatId = formatId;
        this.regionalSingleRecord = regionalSingleRecord;
        this.regionalAverageRecord = regionalAverageRecord;
        this.pos = pos;
        this.best = best;
        this.average = average;
        this.values = values;
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

    public String getPersonId() {
        return personId;
    }

    public Person getPerson() {
        if (person == null || !person.getWcaId().toFormatString().equals(personId)) {
            person = Person.fromID(personId);
        }

        return person;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonCountryId() {
        return personCountryId;
    }

    public String getFormatId() {
        return formatId;
    }

    public Format getFormat() {
        if (format == null || !format.getId().equals(formatId)) {
            format = Format.fromID(formatId);
        }

        return format;
    }

    public String getRegionalSingleRecord() {
        return regionalSingleRecord;
    }

    public String getRegionalAverageRecord() {
        return regionalAverageRecord;
    }

    public int getPos() {
        return pos;
    }

    public int getBest() {
        return best;
    }

    public Solve getBestSolve() {
        if (bestSolve == null) {
            bestSolve = new Solve(best);
        }

        return bestSolve;
    }

    public int getAverage() {
        return average;
    }

    public Solve getAverageSolve() {
        if (averageSolve == null) {
            averageSolve = new Solve(average);
        }

        return averageSolve;
    }

    public int[] getValues() {
        return values;
    }

    public Solve[] getSolves() {
        if (solves == null || solves.length <= 0) {
            solves = new Solve[values.length];

            for (int i = 0; i < values.length; i++) {
                solves[i] = new Solve(values[i]);
            }
        }

        return solves;
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

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setPersonCountryId(String personCountryId) {
        this.personCountryId = personCountryId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public void setRegionalSingleRecord(String regionalSingleRecord) {
        this.regionalSingleRecord = regionalSingleRecord;
    }

    public void setRegionalAverageRecord(String regionalAverageRecord) {
        this.regionalAverageRecord = regionalAverageRecord;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setBest(int best) {
        this.best = best;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}
