package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonHomeWriter implements HomeWriter{
    private final String pathname;

    public JsonHomeWriter(String pathname) {
        this.pathname = pathname;
    }

    public void saveSmartHomeState(SmartHome smartHome) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
//        System.out.println(jsonString);
        Path path = Paths.get(pathname);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not write home to path " + pathname);
        }
    }
}