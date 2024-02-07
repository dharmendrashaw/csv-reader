package com.test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.test.data.CSVData;
import com.test.data.CSVDataStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class CSVParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVParser.class.getName());

    public CSVData readCSVFromFile(String filePath) {
        return null;
    }

    public CSVData readCSVFromURL(String urlToRead) {
        LOGGER.info("Reading CSV file from URL {}", urlToRead);

        try {
            URL url = new URL(urlToRead);
            CSVReader reader = new CSVReader(new InputStreamReader(url.openStream()));
            CSVData csvData = new CSVData(reader.readAll());
            reader.close();
            return csvData;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public CSVDataStream readCSVFromURLToStream(String urlToRead) {
        LOGGER.info("Reading CSV file from URL {} to DataStream ", urlToRead);

        try {
            URL url = new URL(urlToRead);
            CSVReader csvReader = new CSVReader(new InputStreamReader(url.openStream()));
            return new CSVDataStream(csvReader);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
