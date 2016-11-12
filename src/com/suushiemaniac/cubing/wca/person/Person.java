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
                    res.getString("id"),
                    res.getString("name"),
                    res.getString("countryId"),
                    res.getString("gender"),
                    res.getInt("subid")

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

    //Database values
    private String id;
    private String name;
    private String countryId;
    private String genderId;

    private int subId;

    //Derived properties;
    private WcaId wcaId;

    private Country country;

    private Gender gender;

    public Person(String id, String name, String countryId, String genderId, int subId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.genderId = genderId;
        this.subId = subId;
    }

    public String getId() {
        return id;
    }

    public WcaId getWcaId() {
        if (wcaId == null || !wcaId.toFormatString().equals(id)) {
            wcaId = WcaId.fromString(id);
        }

        return wcaId;
    }

    public String getName() {
        return name;
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

    public String getGenderId() {
        return genderId;
    }

    public Gender getGender() {
        if (gender == null || !gender.getId().equals(genderId)) {
            gender = Gender.fromId(genderId);
        }

        return gender;
    }

    public int getSubId() {
        return subId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }
}