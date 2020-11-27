package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHomeLoader implements HomeLoader {
    private final String filename;

    public JsonHomeLoader (String filename) {
        this.filename = filename;
    }

    public SmartHome loadHome() {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json;
        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
        }
        catch (IOException e) {
            throw new RuntimeException("Could not read home from file " + filename);
        }
        return gson.fromJson(json, SmartHome.class);
    }
}