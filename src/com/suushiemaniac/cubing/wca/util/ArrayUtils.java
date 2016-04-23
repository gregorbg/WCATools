package com.suushiemaniac.cubing.wca.util;

import java.util.Arrays;

public class ArrayUtils {
    public static <T> T[] merge(T[] first, T[] second) {
        T[] merged = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, merged, first.length, second.length);
        return merged;
    }
}
