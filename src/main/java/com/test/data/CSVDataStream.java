package com.test.data;

import com.opencsv.CSVReader;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class CSVDataStream implements Closeable {

    private CSVReader reader;
    public CSVDataStream(CSVReader reader) {
        reader = reader;
    }

    public String[] nextRow() throws IOException {
        return reader.readNextSilently();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
