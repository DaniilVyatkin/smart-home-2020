package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Arrays;


public class Application {
    private static SmartHome smartHome;
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
        ArrayList<EventHandler> eventHandlers = new ArrayList<>(
                Arrays.asList(new DoorEventHandler(), new LightEventHandler(), new HallDoorEventHandler()));
        // начинаем цикл обработки событий
        while (true) {
            SensorEvent event = eventGenerator.getNextSensorEvent();
            if (event == null) return;
            System.out.println("Got event: " + event);

            for (EventHandler handler : eventHandlers) {
                handler.handle(event, smartHome);
            }
        }
    }
}