package com.suushiemaniac.cubing.wca.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class ArrayUtils {
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
        return trimBack(trimFront(array, element), element);
    }

    public static <T> T[] trimFront(T[] array, T element) {
        int i;

        for (i = 0; i < array.length; i++) if (!array[i].equals(element)) break;

        return subArray(array, i);
    }

    public static <T> T[] trimBack(T[] array, T element) {
        int i;

        for (i = array.length - 1; i >= 0; i--) if (!array[i].equals(element)) break;

        return subArray(array, 0, i + 1);
    }

    public static <T> T[] subArray(T[] array, int from, int to) {
        return Arrays.asList(array).subList(from, to).toArray(Arrays.copyOf(array, to - from));
    }

    public static <T> T[] subArray(T[] array, int from) {
        return subArray(array, from, array.length);
    }

    public static <T> List<T> toTypedList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    public static Integer[] autobox(int[] in) {
        Integer[] out = new Integer[in.length];

        for (int i = 0; i < in.length; i++) out[i] = in[i];

        return out;
    }

    public static int[] autobox(Integer[] in) {
        int[] out = new int[in.length];

        for (int i = 0; i < in.length; i++)
            out[i] = in[i];

        return out;
    }

    public static <T> String[] toStringArray(T[] in) {
        String[] toReturn = new String[in.length];

        for (int i = 0; i < (in.length & toReturn.length); i++) {
            toReturn[i] = in[i].toString();
        }

        return toReturn;
    }

    public static <T> T[] reverse(T[] in) {
        List<T> revList = new ArrayList<>();

        Collections.addAll(revList, in);
        Collections.reverse(revList);

        return revList.toArray(Arrays.copyOf(in, in.length));
    }
}
