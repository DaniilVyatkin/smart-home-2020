package ru.sbt.mipt.oop;

import java.util.List;

public class EventHandlingLoop {
    private List<EventHandler> eventHandlers;

    public EventHandlingLoop(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    public void handleEvents(SmartHome smartHome, EventGenerator eventGenerator) {
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