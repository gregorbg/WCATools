package com.suushiemaniac.cubing.wca.util;

public class WcaId {
    private int regYear;
    private int num;

    private String nameTag;

    public WcaId(int regYear, int num, String nameTag) {
        this.regYear = regYear;
        this.num = num;
        this.nameTag = nameTag;
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
