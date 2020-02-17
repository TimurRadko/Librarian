package com.timurradko.tools;

public class IdGenerator {
    private static long lastId = 1111110;

    public static long nextId() {
        return ++lastId;
    }
}
