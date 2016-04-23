package com.suushiemaniac.cubing.wca.person;

import com.suushiemaniac.cubing.wca.util.WcaDatabase;
import com.suushiemaniac.cubing.wca.util.globe.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Person {
    public static Person fromID(String wcaId) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Persons WHERE id = ?");
            stat.setString(1, wcaId);

            ResultSet res = db.query(stat);

            return res.next() ? new Person(
                    WcaId.fromString(res.getString("id")),
                    res.getInt("subid"),
                    res.getString("name"),
                    Country.fromID(res.getString("countryId")),
                    Gender.fromIdentifier(res.getString("gender"))
            ) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Person[] fromName(String name) {
        try {
            WcaDatabase db = WcaDatabase.inst();
            PreparedStatement stat = db.prepareStatement("SELECT * FROM Persons WHERE name LIKE ?");
            stat.setString(1, "%" + name + "%");

            ResultSet res = db.query(stat);
            List<Person> pList = new ArrayList<>();

            while (res.next()) {
                pList.add(Person.fromID(res.getString("id")));
            }

            return pList.toArray(new Person[pList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private WcaId wcaId;

    private int subId;

    private String name;

    private Country country;

    private Gender gender;

    public Person(WcaId wcaId, int subId, String name, Country country, Gender gender) {
        this.wcaId = wcaId;
        this.subId = subId;
        this.name = name;
        this.country = country;
        this.gender = gender;
    }

    public WcaId getWcaId() {
        return wcaId;
    }

    public int getSubId() {
        return subId;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public Gender getGender() {
        return gender;
    }

    public void setWcaId(WcaId wcaId) {
        this.wcaId = wcaId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
