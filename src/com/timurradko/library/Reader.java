package com.timurradko.library;

import com.timurradko.tools.IDReaderGenerator;

public class Reader {
    String readerName;
    public final long IDReader = IDReaderGenerator.nextIDReader();

    public Reader (String readerName) {
        this.readerName = readerName;
    }

    public String getReaderName() {
        return readerName;
    }

    @Override
    public String toString() {
        return "Reader {" +
                " ID reader: " + IDReader + ' ' +
                " Reader name: " + readerName + ' ' +
                " }" + '\n';
    }
}
