package com.atharva.detailfinder.reader;

import com.atharva.detailfinder.util.ArrayClassConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader<T> {

    ArrayClassConverter<T> converter = new ArrayClassConverter<>();

    public List<T> readCSV(String filePath, Class<T> classType) throws IOException {
        File csvFile = new File(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile)));

        List<List<String>> input = new ArrayList<>();

        String line = reader.readLine();
        while (line != null) {
            String[] splits = line.split(",");
            input.add(Arrays.asList(splits));
            line = reader.readLine();
        }

        return converter.arrayToClass(input, classType);
    }
}
