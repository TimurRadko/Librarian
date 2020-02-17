package com.timurradko.tools;

import com.timurradko.entity.LibBook;

import java.util.Collection;
import java.util.List;

public class LibUtil {
    public static LibBook[] getArrayFromCollection(Collection<LibBook> books) {
        LibBook[] integersArray = new LibBook[books.size()];
        Object[] objects = books.toArray();
        for (int i = 0; i < integersArray.length; i++) {
            integersArray[i] = (LibBook) objects[i];
        }
        return integersArray;
    }

    public static List<LibBook> getUnmodifiableVersion(Collection<LibBook> books) {
        LibBook[] array = getArrayFromCollection(books);
        return new UnmodifiableArrayList<>(array, array.length);
    }
}
