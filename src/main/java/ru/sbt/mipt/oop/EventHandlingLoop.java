package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Arrays;

public class EventHandlingLoop {

    public static void handleEvents(SmartHome smartHome, EventGenerator eventGenerator) {

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