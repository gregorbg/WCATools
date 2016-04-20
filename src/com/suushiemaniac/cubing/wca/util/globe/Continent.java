package com.suushiemaniac.cubing.wca.util.globe;

import com.suushiemaniac.cubing.wca.util.GeoCoord;

public class Continent {
    private String name;
    private String recordName;

    private GeoCoord coords;

    private int zoom;

    public Continent(String name, String recordName, GeoCoord coords, int zoom) {
        this.name = name;
        this.recordName = recordName;
        this.coords = coords;
        this.zoom = zoom;
    }

    public String getName() {
        return name;
    }

    public String getRecordName() {
        return recordName;
    }

    public GeoCoord getCoords() {
        return coords;
    }

    public int getZoom() {
        return zoom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public void setCoords(GeoCoord coords) {
        this.coords = coords;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}