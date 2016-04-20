package com.suushiemaniac.cubing.wca.util;

public class SmallDate implements Comparable<SmallDate> {
    public static int compare(SmallDate dateOne, SmallDate dateTwo) {
        return dateOne.compareTo(dateTwo);
    }

    private int day;
    private int month;
    private int year;

    public SmallDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(SmallDate o) {
        int yearComp = Integer.compare(this.year, o.getYear());

        if (yearComp == 0) {
            int monthComp = Integer.compare(this.month, o.getMonth());

            if (monthComp == 0) {
                return Integer.compare(this.day, o.getDay());
            } else return monthComp;
        } else return yearComp;
    }
}