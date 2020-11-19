package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.EventHandlingLoop.handleEvents;

public class Application {
    private SmartHome smartHome;
    private final HomeLoader homeLoader;
    private EventGenerator eventGenerator;

    public Application(HomeLoader homeLoader, EventGenerator eventGenerator) {
        this.homeLoader = homeLoader;
        this.eventGenerator = eventGenerator;
    }

    public static void main(String... args) {
        HomeLoader homeLoader = new JsonHomeLoader(Constants.JSON_FILE_FOR_LOADING);
        EventGenerator eventGenerator = new RandomSensorEventGenerator();
        Application application = new Application(homeLoader, eventGenerator);
        application.run();
    }

    private void run() {
        smartHome = homeLoader.loadHome();
        handleEvents(smartHome, eventGenerator);
    }
}