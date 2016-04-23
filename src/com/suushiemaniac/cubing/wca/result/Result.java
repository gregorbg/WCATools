package com.suushiemaniac.cubing.wca.result;

import com.suushiemaniac.cubing.wca.comp.Competition;
import com.suushiemaniac.cubing.wca.comp.Event;
import com.suushiemaniac.cubing.wca.comp.Round;
import com.suushiemaniac.cubing.wca.person.Person;
import com.suushiemaniac.cubing.wca.time.Format;

public class Result {
    private Competition competition;

    private Event event;

    private Round round;

    private int pos;

    private Solve best;
    private Solve average;

    private Person person;

    private Format format;

    private Solve[] values;

    private String singleRecord;
    private String averageRecord;

    public Result(Competition competition, Event event, Round round, int pos, Solve best, Solve average, Person person, Format format, Solve[] values, String singleRecord, String averageRecord) {
        this.competition = competition;
        this.event = event;
        this.round = round;
        this.pos = pos;
        this.best = best;
        this.average = average;
        this.person = person;
        this.format = format;
        this.values = values;
        this.singleRecord = singleRecord;
        this.averageRecord = averageRecord;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Solve getBest() {
        return best;
    }

    public void setBest(Solve best) {
        this.best = best;
    }

    public Solve getAverage() {
        return average;
    }

    public void setAverage(Solve average) {
        this.average = average;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Solve[] getValues() {
        return values;
    }

    public void setValues(Solve[] values) {
        this.values = values;
    }

    public String getSingleRecord() {
        return singleRecord;
    }

    public void setSingleRecord(String singleRecord) {
        this.singleRecord = singleRecord;
    }

    public String getAverageRecord() {
        return averageRecord;
    }

    public void setAverageRecord(String averageRecord) {
        this.averageRecord = averageRecord;
    }
}
