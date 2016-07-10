package com.suushiemaniac.cubing.wca.util;

import java.util.Arrays;
import java.util.List;

public abstract class StringUtils {
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
