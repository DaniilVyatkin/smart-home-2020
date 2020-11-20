package ru.sbt.mipt.oop;

import java.util.Arrays;


public class Application {
    private SmartHome smartHome;
    private final HomeLoader homeLoader;
    private EventGenerator eventGenerator;
    private final EventHandlingLoop eventHandlingLoop;

    public Application(HomeLoader homeLoader, EventGenerator eventGenerator, EventHandlingLoop eventHandlingLoop) {
        this.homeLoader = homeLoader;
        this.eventGenerator = eventGenerator;
        this.eventHandlingLoop = eventHandlingLoop;
    }

    public static void main(String... args) {
        HomeLoader homeLoader = new JsonHomeLoader(Constants.JSON_FILE_FOR_LOADING);
        EventGenerator eventGenerator = new RandomSensorEventGenerator();
        EventHandlingLoop eventHandlingLoop = new EventHandlingLoop(Arrays.asList(
                new DoorEventHandler(),
                new LightEventHandler(),
                new HallDoorEventHandler()
        ));
        Application application = new Application(homeLoader, eventGenerator, eventHandlingLoop);
        application.run();
    }

    private void run() {
        smartHome = homeLoader.loadHome();
        eventHandlingLoop.handleEvents(smartHome, eventGenerator);
    }
}