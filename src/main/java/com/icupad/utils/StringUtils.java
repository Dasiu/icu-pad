package com.icupad.utils;

public class StringUtils {
    /**
     *
     * @param size of generated string
     * @return string of given size
     */
    public static String of(int size) {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append("x");
        }
        return sb.toString();
    }
}