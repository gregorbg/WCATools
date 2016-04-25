package com.suushiemaniac.cubing.wca.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

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

    public static <T> T[] trim(T[] array, T element) {
        List<T> trimList = new ArrayList<>();

        int i, j;

        for (i = 0; i < array.length; i++) if (!array[i].equals(element)) break;
        for (j = array.length - 1; j >= 0; j--) if (!array[j].equals(element)) break;

        trimList.addAll(Arrays.asList(array).subList(i, j + 1));

        return trimList.toArray(Arrays.copyOf(array, trimList.size()));
    }

    public static Integer[] autobox(int[] in) {
        Integer[] out = new Integer[in.length];

        for (int i = 0; i < in.length; i++) out[i] = in[i];

        return out;
    }

    public static int[] autobox(Integer[] in) {
        int[] out = new int[in.length];

        for (int i = 0; i < in.length; i++) out[i] = in[i];

        return out;
    }
}
