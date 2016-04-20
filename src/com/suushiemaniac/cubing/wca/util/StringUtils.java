package com.suushiemaniac.cubing.wca.util;

public class StringUtils {
    public static String fillLeading(String toFill, char fillChar, int finalLength) {
        String filled = toFill;

        while (filled.length() < finalLength) {
            filled = fillChar + filled;
        }

        return filled;
    }

    public static String fillTrailing(String toFill, char fillChar, int finalLength) {
        String filled = toFill;

        while (filled.length() < finalLength) {
            filled += fillChar;
        }

        return filled;
    }
}
