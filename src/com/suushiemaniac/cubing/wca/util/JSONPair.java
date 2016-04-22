package com.suushiemaniac.cubing.wca.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONPair<K, V> {
    public static JSONPair<String, String> fromString(String json) {
        Matcher matcher = Pattern.compile("\\[\\{(.*?)\\}\\{(.*?)\\}\\]").matcher(json);
        return matcher.find() ? new JSONPair<>(matcher.group(1), matcher.group(2)) : null;
    }

    private K key;
    private V value;

    public JSONPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
