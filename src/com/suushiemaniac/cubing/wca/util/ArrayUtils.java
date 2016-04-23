package com.suushiemaniac.cubing.wca.util;

import java.util.Arrays;

public class ArrayUtils {
    public static final String[] APLHABET = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public static <T> T[] merge(T[] first, T[] second) {
        T[] merged = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, merged, first.length, second.length);
        return merged;
    }

    public static <T> int index(T[] haystack, T needle) {
        for (int i = 0; i < haystack.length; i++) {
            if (haystack[i].equals(needle)) {
                return i;
            }
        }

        return -1;
    }
}
