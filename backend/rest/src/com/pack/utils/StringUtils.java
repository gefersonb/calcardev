package com.pack.utils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public final class StringUtils {

    private StringUtils() {
    }

    public static String normalize(String str) {
        String result = "";
        if (!isNullOrEmpty(str)) {
            result = Normalizer.normalize(str.trim(), Normalizer.Form.NFD);
            result = result.replaceAll("[^\\p{ASCII}]", "");
        }
        return result.trim();
    }

    public static boolean isNullOrEmpty(String str) {
        Optional<String> value = Optional.ofNullable(str);
        return !value.isPresent() || value.get().isEmpty();
    }

    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    public static List<String> asList(String[] values) {
        List<String> lista = new LinkedList<>();
        if (values != null && values.length > 0) {
            for (String s : values) {
                lista.add(s.trim());
            }
        }
        return lista;
    }

    public static List<String> asList(String values) {
        return isNullOrEmpty(values) ? new ArrayList<>() : asList(values.split(","));
    }

    public static String lpad(String valueToPad, int size, String filler) {
        String value = isNullOrEmpty(valueToPad) ? "" : valueToPad;
        StringBuilder builder = new StringBuilder();
        while (builder.length() + value.length() < size) {
            builder.append(filler);
        }
        builder.append(value);
        return builder.toString();
    }

    public static String rpad(String valueToPad, int size, String filler) {
        String value = isNullOrEmpty(valueToPad) ? "" : valueToPad;
        StringBuilder builder = new StringBuilder(valueToPad);
        while (builder.length() + value.length() < size + value.length()) {
            builder.append(filler);
        }
        return builder.toString();
    }

    public static String onlyNumber(String value) {
        return value == null ? null : value.replaceAll("[^0-9]", "");
    }

    public static String onlyAlpha(String value) {
        return value == null ? null : value.replaceAll("[^A-Za-z]", "");
    }

}