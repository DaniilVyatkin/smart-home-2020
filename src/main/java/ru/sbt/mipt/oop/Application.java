package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.EventHandlingLoop.handleEvents;

public class Application {
    public static void main(String... args) throws IOException {
        SmartHome smartHome = SmartHomeInitializer.initializeSmartHome();
        handleEvents(smartHome);
        }
}