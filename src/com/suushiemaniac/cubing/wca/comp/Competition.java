package com.suushiemaniac.cubing.wca.comp;

import com.suushiemaniac.cubing.wca.person.Person;
import com.suushiemaniac.cubing.wca.util.GeoCoord;
import com.suushiemaniac.cubing.wca.util.SmallDate;
import com.suushiemaniac.cubing.wca.util.globe.Country;

public class Competition {
    private int id;

    private String name;
    private String cityName;
    private String information;
    private String venue;
    private String venueWebsite;
    private String venueAddress;
    private String venueDetails;
    private String compWebsite;
    private String cellName;

    private Country country;

    private SmallDate startDate;
    private SmallDate endDate;

    private Event[] events;

    private Person[] delegates;
    private Person[] organisers;

    private GeoCoord coords;
}
