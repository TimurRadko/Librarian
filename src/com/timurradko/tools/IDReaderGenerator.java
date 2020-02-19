package com.timurradko.tools;

public class IDReaderGenerator {
    private static long IDReader = 1110;

    public static long nextIDReader() {
        return ++IDReader;
    }
}
