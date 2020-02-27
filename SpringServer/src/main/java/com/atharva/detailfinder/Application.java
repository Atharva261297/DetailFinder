package com.atharva.detailfinder;

import com.atharva.detailfinder.model.data.User;
import com.atharva.detailfinder.reader.CSVReader;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        CSVReader<User> userCSVReader = new CSVReader<>();
        String filePath = "F:\\Projects\\DetailFinder\\DetailFinder\\src\\main\\resources\\data\\users.csv";
        System.out.println(userCSVReader.readCSV(filePath, User.class));
    }
}
