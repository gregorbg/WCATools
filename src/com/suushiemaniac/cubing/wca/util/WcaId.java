package com.suushiemaniac.cubing.wca.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WcaId {
    public static WcaId fromString(String wcaIdString) {
        if (wcaIdString.length() != 10) return null;
        Matcher idMatcher = Pattern.compile("([0-9]{4})([A-Z]{4})([0-9]{2})").matcher(wcaIdString);

        return idMatcher.find() ? new WcaId(
                Integer.parseInt(idMatcher.group(1)),
                idMatcher.group(2),
                Integer.parseInt(idMatcher.group(3))
        ) : null;
    }

    private int regYear;
    private int num;

    private String nameTag;

    public WcaId(int regYear, String nameTag, int num) {
        this.regYear = regYear;
        this.nameTag = nameTag;
        this.num = num;
    }

    public String toFormatString() {
        return "" + this.regYear + this.nameTag + StringUtils.fillLeading("" + this.num, '0', 2);
    }

    public int getRegYear() {
        return regYear;
    }

    public int getNum() {
        return num;
    }

    public String getNameTag() {
        return nameTag;
    }
}
